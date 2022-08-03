package com.example.androidtraining20220719.model

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.media.Image

data class CharacterHeaderData(val id: String, val name: String, val imageUrl: String, var image: Bitmap?)