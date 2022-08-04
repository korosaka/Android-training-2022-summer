package com.example.androidtraining20220719.repositories

import com.example.androidtraining20220719.model.CharacterHeaderData


interface CharactersRepository {

    companion object {
        fun newInstance(): CharactersRepository = CharactersRepositoryImpl()
    }

    fun fetchCharactersExceptImage(): List<CharacterHeaderData>
}

class CharactersRepositoryImpl : BaseRepository(), CharactersRepository {

    override fun fetchCharactersExceptImage(): List<CharacterHeaderData> {
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