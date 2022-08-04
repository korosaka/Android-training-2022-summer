package com.example.androidtraining20220719.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.androidtraining20220719.model.CharacterHeaderData
import com.example.androidtraining20220719.repositories.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TopPageViewModelTest {

//    private lateinit var latch: CountDownLatch
    private lateinit var viewModel: TopPageViewModel

    //for LiveData
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        //for async task
//        latch = CountDownLatch(1)

        //Avoid Main
        Dispatchers.setMain(Dispatchers.Unconfined)

        viewModel = TopPageViewModel(createMockCharactersRepo())
    }

    @Test
    fun statusMessageTest() {
        assertEquals("default", viewModel.statusMessage.value)
        runBlocking {
            viewModel.fetchCharactersExceptImage()
        }

//        latch.await()
//        latch.countDown()
        assertEquals("finish fetching data except images", viewModel.statusMessage.value)
    }

    @Test
    fun liveCharactersDataTest() {
        assertTrue(viewModel.characters.value!!.isEmpty())
        runBlocking {
            viewModel.fetchCharactersExceptImage()
        }
        assertEquals(EXPECTED_CHARACTERS_SIZE, viewModel.characters.value!!.size)
        for (character in viewModel.characters.value!!) {
            assertTrue(character.name.isNotEmpty())
            assertEquals(null, character.image)
        }
    }

    companion object {
        const val EXPECTED_CHARACTERS_SIZE = 30
    }


    private fun createMockCharactersRepo(): CharactersRepository {
        return object : CharactersRepository {
            override fun fetchCharactersExceptImage(): List<CharacterHeaderData> {
                val mutableList = mutableListOf<CharacterHeaderData>()
                for (i in 0 until EXPECTED_CHARACTERS_SIZE) {
                    mutableList.add(createRandomCharacterHeader())
                }
                return mutableList.toList()
            }

            private fun createRandomCharacterHeader(): CharacterHeaderData {
                val id = (1..20).random().toString()
                return CharacterHeaderData(
                    id,
                    "(ID: $id) Test Character",
                    "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                    null
                )
            }
        }
    }

}