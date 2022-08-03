package com.example.androidtraining20220719.model.repositories

class CharactersUrlRepository: BaseRepository() {

    fun fetchCharactersApiUrl(): String? {
        val response =
            createCharacterService()
                .fetchCharactersURL()
                .execute()
        return response.body()?.characters
    }
}