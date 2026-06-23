package com.example.data.ocr

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.googlecode.tesseract.android.TessBaseAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class TesseractEngine(
    private val context: Context
) {
    private val mutex = Mutex()
    private var tessBaseApi: TessBaseAPI? = null
    private var isInitialized = false

    private suspend fun ensureInitialized() {
        if (isInitialized) return

        mutex.withLock {
            if (isInitialized) return@withLock

            withContext(Dispatchers.IO) {
                try {
                    val tessDataParentPath = context.filesDir.absolutePath
                    val tessDataPath = File(tessDataParentPath, "tessdata")
                    
                    if (!tessDataPath.exists()) {
                        tessDataPath.mkdirs()
                    }

                    // Copy all traineddata files from assets
                    context.assets.list("tessdata")?.forEach { fileName ->
                        if (fileName.endsWith(".traineddata")) {
                            val destFile = File(tessDataPath, fileName)
                            if (!destFile.exists()) {
                                Log.d("TesseractEngine", "Copying $fileName to internal storage")
                                context.assets.open("tessdata/$fileName").use { input ->
                                    FileOutputStream(destFile).use { output ->
                                        input.copyTo(output)
                                    }
                                }
                            }
                        }
                    }

                    val api = TessBaseAPI()
                    // Initialize with both Russian and English
                    val success = api.init(tessDataParentPath, "rus+eng")
                    if (success) {
                        tessBaseApi = api
                        isInitialized = true
                        Log.d("TesseractEngine", "Tesseract initialized successfully with rus+eng")
                    } else {
                        Log.e("TesseractEngine", "Tesseract initialization failed")
                    }
                } catch (e: Exception) {
                    Log.e("TesseractEngine", "Error during initialization", e)
                }
            }
        }
    }

    suspend fun extractText(imageData: ByteArray): String = withContext(Dispatchers.IO) {
        ensureInitialized()
        
        val api = tessBaseApi ?: return@withContext ""

        mutex.withLock {
            try {
                // Higher resolution for better OCR quality on receipts
                val bitmap = decodeSampledBitmap(imageData, 2048)
                    ?: return@withContext ""

                api.setImage(bitmap)
                val result = api.utF8Text?.trim().orEmpty()
                bitmap.recycle()
                result
            } catch (e: Exception) {
                Log.e("TesseractEngine", "Error during text extraction", e)
                ""
            } finally {
                api.clear()
            }
        }
    }

    private fun decodeSampledBitmap(data: ByteArray, maxSize: Int): Bitmap? {
        val options = BitmapFactory.Options().apply {
            inJustDecodeBounds = true
        }
        BitmapFactory.decodeByteArray(data, 0, data.size, options)

        var sampleSize = 1
        while (options.outWidth / sampleSize > maxSize || options.outHeight / sampleSize > maxSize) {
            sampleSize *= 2
        }

        return BitmapFactory.decodeByteArray(data, 0, data.size, BitmapFactory.Options().apply {
            inSampleSize = sampleSize
            inPreferredConfig = Bitmap.Config.ARGB_8888
        })
    }

    fun release() {
        tessBaseApi?.recycle()
        tessBaseApi = null
        isInitialized = false
    }
}
