package com.example.androidtraining20220719.view_model

import android.app.Application
import androidx.lifecycle.*
import com.example.androidtraining20220719.model.CharacterHeaderData
import com.example.androidtraining20220719.model.MockData
import com.example.androidtraining20220719.model.repositories.CharactersRepository
import com.example.androidtraining20220719.model.repositories.CharactersUrlRepository
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
    private val charactersUrlRepo = CharactersUrlRepository()

    var statusMessage: MutableLiveData<String> = MutableLiveData()

    init {
        statusMessage.value = "default"
        characters.value = localCharactersData
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
            characters.value = localCharactersData
            updateStatusMessage("finish fetching data")
        }
    }

    private fun updateStatusMessage(message: String) {
        statusMessage.value = message
    }
}