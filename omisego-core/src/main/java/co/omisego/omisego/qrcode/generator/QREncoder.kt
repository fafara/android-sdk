package co.omisego.omisego.qrcode.generator

/*
 * OmiseGO
 *
 * Created by Phuchit Sirimongkolsathien on 1/4/2018 AD.
 * Copyright © 2017-2018 OmiseGO. All rights reserved.
 */

import android.graphics.Bitmap
import com.google.zxing.common.BitMatrix

class QREncoder {

    /**
     * Generate a [Bitmap] from [BitMatrix]
     *
     * @param matrix A matrix containing the QR code data
     * @return A bitmap represents the QR code
     */
    fun createBitmap(matrix: BitMatrix): Bitmap {
        val width = matrix.width
        val height = matrix.height
        val pixels = IntArray(matrix.width * matrix.height)
        for (y in 0 until height) {
            val offset = y * width
            for (x in 0 until width) {
                pixels[offset + x] = if (matrix.get(x, y)) BLACK else WHITE
            }
        }

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmap
    }

    companion object {
        const val WHITE = -0x1
        const val BLACK = -0x1000000
    }
}
