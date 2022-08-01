package com.example.androidtraining20220719.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.androidtraining20220719.model.CharacterHeaderData
import com.example.androidtraining20220719.model.MockData
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

    init {
        characters.value = localCharactersData
        fetchData()
    }

    private fun fetchData() {
        val context = getApplication<Application>().applicationContext
        viewModelScope.launch(Dispatchers.Main) {
            val data = withContext(Dispatchers.IO) {
                MockData(context).getCharactersData()
            }
            localCharactersData.clear()
            for (character in data) {
                delay(200)
                localCharactersData.add(character)
                // to be observed, the data need to be set after local data is updated
                characters.value = localCharactersData
            }
        }
    }
}