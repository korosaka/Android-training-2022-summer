package com.example.androidtraining20220719.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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

        viewModel = TopPageViewModel()
    }

    @Test
    fun statusMessageTest() {
        assertEquals("default", viewModel.statusMessage.value)
        runBlocking {
            viewModel.fetchCharactersExcludeImage()
        }

//        latch.await()
//        latch.countDown()
        assertEquals("finish fetching data except images", viewModel.statusMessage.value)
    }

}