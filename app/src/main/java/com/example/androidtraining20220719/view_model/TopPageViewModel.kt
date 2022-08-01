package com.example.androidtraining20220719.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidtraining20220719.model.CharacterHeaderData
import com.example.androidtraining20220719.model.MockData


// To use context in ViewModel, AndroidViewModel is used
class TopPageViewModel(application: Application) : AndroidViewModel(application) {

    var characters = MutableLiveData<List<CharacterHeaderData>>()


    init {
        characters.value = listOf()
        val context = getApplication<Application>().applicationContext
        //TODO do in async
        characters.value = MockData(context).getCharactersData()
    }

}