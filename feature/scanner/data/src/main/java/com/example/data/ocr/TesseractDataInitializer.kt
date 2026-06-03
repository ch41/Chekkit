package com.example.data.ocr

import android.content.Context
import java.io.File
import java.io.FileOutputStream

class TesseractDataInitializer(
    private val context: Context
) {

    fun initialize() {
        val tessDataDir = File(
            context.filesDir,
            "tessdata"
        )

        if (!tessDataDir.exists()) {
            tessDataDir.mkdirs()
        }

        val trainedDataFile = File(
            tessDataDir,
            "rus.traineddata"
        )

        if (trainedDataFile.exists()) return

        context.assets.open("tessdata/rus.traineddata").use { input ->
            FileOutputStream(trainedDataFile).use { output ->
                input.copyTo(output)
            }
        }
    }
}