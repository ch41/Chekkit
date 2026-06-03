package com.example.data.ocr

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import java.io.File
import java.io.FileOutputStream

class TesseractDataInitializer(
    private val context: Context
) {

    fun initialize(): Boolean {
        return try {
            val tessDataDir = File(context.filesDir, "tessdata")

            if (!tessDataDir.exists()) {
                Log.d("TesseractDataInitializer", "Creating tessdata directory")
                tessDataDir.mkdirs()
            }

            copyAssetFile("tessdata/rus.traineddata", File(tessDataDir, "rus.traineddata"))

            true
        } catch (e: Exception) {
            Log.e("TesseractDataInitializer", "Failed to initialize", e)
            false
        }
    }

    private fun copyAssetFile(assetPath: String, destinationFile: File) {
        if (destinationFile.exists()) {
            Log.d("TesseractDataInitializer", "File already exists: ${destinationFile.absolutePath}")
            return
        }

        Log.d("TesseractDataInitializer", "Copying $assetPath to ${destinationFile.absolutePath}")
        context.assets.open(assetPath).use { input ->
            FileOutputStream(destinationFile).use { output ->
                input.copyTo(output)
            }
        }
        Log.d("TesseractDataInitializer", "Copied, size: ${destinationFile.length()} bytes")
    }
}