package com.example.androidtraining20220719.model

import android.content.Context
import android.media.Image
import androidx.core.graphics.drawable.toBitmap
import com.example.androidtraining20220719.R

class MockData(private val myContext: Context) {

    fun getCharactersData(): List<CharacterHeaderData> {
        val mutableList = mutableListOf<CharacterHeaderData>()
        for (i in 0..25) {
            mutableList.add(createRandomCharacterHeader())
        }
        Thread.sleep(2000)
        return mutableList.toList()
    }

    private fun createRandomCharacterHeader(): CharacterHeaderData {
        val id = (1..20).random().toString()
        val image = myContext.getDrawable(R.drawable.test_character_image)?.toBitmap()
        return CharacterHeaderData(
            id,
            "(ID: $id) Test Character",
            "null",
            image
        )
    }

}