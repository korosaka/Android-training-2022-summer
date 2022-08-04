package com.example.androidtraining20220719.view_model

import androidx.lifecycle.*
import com.example.androidtraining20220719.model.CharacterHeaderData
import com.example.androidtraining20220719.repositories.CharactersRepository
import com.example.androidtraining20220719.repositories.ImageRepository
import kotlinx.coroutines.*


// if you wanna use context in ViewModel, AndroidViewModel should be used
class TopPageViewModel(
    private val charactersRepo: CharactersRepository = CharactersRepository.newInstance()
) : ViewModel() {

    val characters = MutableLiveData<List<CharacterHeaderData>>()

    /**
     * To keep same instance for List, localCharactersData is used
     * because LiveData.value can't use clear(), for example.
     * The reason to keep the List instance is to avoid recreate Adapter for ListView and RecyclerView.
     * If the different instance is set to the value, notify method never work.
     */
    private val localCharactersData: MutableList<CharacterHeaderData> = mutableListOf()

    var statusMessage: MutableLiveData<String> = MutableLiveData()

    init {
        statusMessage.value = "default"
        updateLiveCharacters()
    }

    fun fetchCharactersData() {
        updateStatusMessage("start fetching data")
        viewModelScope.launch(Dispatchers.Main) {
            fetchCharactersExceptImage()
            //it waits for the previous function's completion
            fetchCharactersImage()
        }
    }

    suspend fun fetchCharactersExceptImage() {
        val charactersList = withContext(Dispatchers.IO) {
            fetchCharactersList()
        }
        localCharactersData.clear()
        localCharactersData.addAll(charactersList)
        // to be observed, the data need to be set after local data is updated
        updateLiveCharacters()
        updateStatusMessage("finish fetching data except images")
    }

    private fun fetchCharactersList(): List<CharacterHeaderData> {
        return charactersRepo.fetchCharactersExceptImage()
        //MockData().getCharactersData()
    }


    private fun updateStatusMessage(message: String) {
        statusMessage.value = message
    }

    private fun updateLiveCharacters() {
        characters.value = localCharactersData
    }

    private suspend fun fetchCharactersImage() {
        val imageRepo = ImageRepository()
        for (characterData in localCharactersData) {
            characterData.image = withContext(Dispatchers.IO) {
                imageRepo.fetchImage(characterData.imageUrl)
            }
            updateLiveCharacters()
        }
        updateStatusMessage("finish fetching data!!!!!!")
    }
}