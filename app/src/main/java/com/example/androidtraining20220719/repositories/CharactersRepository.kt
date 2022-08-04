package com.example.androidtraining20220719.repositories

import com.example.androidtraining20220719.model.CharacterHeaderData

class CharactersRepository : BaseRepository() {

    fun fetchCharactersExcludeImage(): List<CharacterHeaderData> {
        val charactersUrl = CharactersUrlRepository().fetchCharactersApiUrl()
        charactersUrl?.let {
            val charactersEntity =
                createCharacterService()
                    .fetchCharacters(charactersUrl)
                    .execute()
                    .body()?.results ?: listOf()

            val charaList: MutableList<CharacterHeaderData> = mutableListOf()
            for (characterEntity in charactersEntity) {
                val characterData = characterEntity.let {
                    CharacterHeaderData(it.id, it.name, it.image, null)
                }
                charaList.add(characterData)
            }
            return charaList
        }

        return listOf()
    }

}