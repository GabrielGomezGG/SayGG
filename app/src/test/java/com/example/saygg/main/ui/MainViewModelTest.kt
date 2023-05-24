package com.example.saygg.main.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel()
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun setTitle() {
        //given
        val title = "title"
        //when
        mainViewModel.setTitle(title)
        //then
        assert(mainViewModel.title.value == title)
    }

    @Test
    fun setImage(){
        //given
        val image = "image"
        //when
        mainViewModel.setImage(image)
        //then
        assert(mainViewModel.imageTitle.value == image)
    }
}