package com.example.data.ocr

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.googlecode.tesseract.android.TessBaseAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class TesseractEngine(
    private val context: Context
) {
    private val lock = Any()
    private val tessBaseApi: TessBaseAPI by lazy {
        val tessDataParentPath = context.filesDir.absolutePath // Путь к internal storage
        val tessDataPath = "$tessDataParentPath/tessdata/"   // Путь к папке tessdata

        val assetFile = "tessdata/rus.traineddata"
        val destFile = File(tessDataPath, "rus.traineddata")

        if (!destFile.exists()) {
            Log.d("TesseractEngine", "Copying $assetFile to ${destFile.absolutePath}")
            context.assets.open(assetFile).use { input ->
                FileOutputStream(destFile).use { output ->
                    input.copyTo(output)
                }
            }
            Log.d("TesseractEngine", "File copied, size: ${destFile.length()} bytes")
        }

        Log.d("TesseractEngine", "Init Tesseract. Parent path: $tessDataParentPath")
        TessBaseAPI().apply {
            val success = init(tessDataParentPath, "rus") // ВАЖНО: путь к ПАПКЕ, содержащей tessdata
            if (!success) {
                Log.e("TesseractEngine", "Init failed. Language file not loaded.")
                throw IllegalStateException("Tesseract initialization failed")
            }
            Log.d("TesseractEngine", "Init successful.")
        }
    }

    suspend fun extractText(imageData: ByteArray): String = withContext(Dispatchers.IO) {
        synchronized(lock) {
            try {
                val bitmap = decodeSampledBitmap(imageData, 1024)
                    ?: return@withContext ""

                tessBaseApi.setImage(bitmap)
                val result = tessBaseApi.utF8Text?.trim().orEmpty()
                bitmap.recycle()
                result
            } catch (e: Exception) {
                Log.e("TesseractEngine", "OCR failed", e)
                ""
            } finally {
                tessBaseApi.clear()
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
        })
    }

    fun release() {
        if (::tessBaseApi.isOpen) {
            tessBaseApi.recycle()
        }
    }
}