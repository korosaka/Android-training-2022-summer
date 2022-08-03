package com.example.androidtraining20220719.model


class MockData {

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
//        val image = myContext.getDrawable(R.drawable.test_character_image)?.toBitmap()
        return CharacterHeaderData(
            id,
            "(ID: $id) Test Character",
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            null
        )
    }

}