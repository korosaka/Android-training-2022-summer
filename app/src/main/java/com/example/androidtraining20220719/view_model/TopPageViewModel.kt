package com.example.androidtraining20220719.view_model

import android.app.Application
import androidx.lifecycle.*
import com.example.androidtraining20220719.model.CharacterHeaderData
import com.example.androidtraining20220719.model.MockData
import com.example.androidtraining20220719.model.repositories.CharactersRepository
import com.example.androidtraining20220719.model.repositories.CharactersUrlRepository
import com.example.androidtraining20220719.model.repositories.ImageRepository
import kotlinx.coroutines.*


// To use context in ViewModel, AndroidViewModel is used
class TopPageViewModel(application: Application) : AndroidViewModel(application) {

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
        fetchData()
    }

    private fun fetchData() {
        updateStatusMessage("start fetching data")
        viewModelScope.launch(Dispatchers.Main) {
            val data = withContext(Dispatchers.IO) {
                CharactersRepository().fetchCharactersExcludeImage()
                //MockData(getApplication<Application>().applicationContext).getCharactersData()
            }
            localCharactersData.clear()
            localCharactersData.addAll(data)
            // to be observed, the data need to be set after local data is updated
            updateLiveCharacters()
            updateStatusMessage("finish fetching data except images")

            // start fetching each characters' image
            val imageRepo = ImageRepository()
            for (characterData in localCharactersData) {
                val characterImage = withContext(Dispatchers.IO) {
                    imageRepo.fetchImage(characterData.imageUrl)
                }
                characterData.image = characterImage
                updateLiveCharacters()
            }
            updateStatusMessage("finish fetching data!!!!!!")

        }
    }

    private fun updateStatusMessage(message: String) {
        statusMessage.value = message
    }

    private fun updateLiveCharacters() {
        characters.value = localCharactersData
    }
}