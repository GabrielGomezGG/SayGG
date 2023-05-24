package com.example.saygg.tournament.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.saygg.tournament.data.ITournamentRepository
import com.example.saygg.tournament.fake.FakeTournamentRepository
import com.example.saygg.tournament.fake.FakeTournamentSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TournamentViewModelTest {


    private lateinit var tournamentRepository: ITournamentRepository

    private lateinit var tournamentViewModel : TournamentViewModel

    @get:Rule
    var rule:InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        tournamentViewModel = TournamentViewModel(tournamentRepository)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getTournamentList() = runTest {
        //given
        coEvery { tournamentRepository.getTournaments(any(),any()) } returns TournamentUiState.Success(FakeTournamentSource.fakeTournamentList)
        //when
        tournamentViewModel.getTournamentList("AR",20)
        //then
        Assert.assertEquals(tournamentViewModel.tournamentList.value, TournamentUiState.Success(FakeTournamentSource.fakeTournamentList))
    }

    @Test
    fun getTournamentById() = runTest {
        //given
        coEvery { tournamentRepository.getTournamentById(any()) } returns TournamentUiState.Success(FakeTournamentSource.fakeTournamentList[0])
        //when
        tournamentViewModel.getTournamentById("1")
        //then
        Assert.assertEquals(tournamentViewModel.tournament.value, TournamentUiState.Success(FakeTournamentSource.fakeTournamentList[0]))

    }
}