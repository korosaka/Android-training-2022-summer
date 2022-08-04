package com.example.androidtraining20220719.repositories

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.URL

class ImageRepository {

    fun fetchImage(imageUrlStr: String): Bitmap {
        URL(imageUrlStr).openStream().use {
            return BitmapFactory.decodeStream(it)
        }
    }
}