package com.example.saygg.tournament.utils

import org.junit.Assert
import org.junit.Test

class TournamentUtilsTest {

    @Test
    fun timeStampToDate_CorrectTimeStamp_ReturnsCorrectDate() {
        // Arrange
        val timeStampJune = 1686970800
        val timeStampJuly = 1658620799


        val expectedDateJune = "June 17 2023"
        val expectedDateJuly = "July 23 2022"

        // Act
        val actualDateJune = timeStampToDate(timeStampJune)
        val actualDateJuly = timeStampToDate(timeStampJuly)

        // Assert
        Assert.assertEquals(expectedDateJune,actualDateJune)
        Assert.assertEquals(expectedDateJuly,actualDateJuly)
    }
}