package aoc

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class MemoryGameKtTest {
    @Test
    fun `should add 0 to starting list for previously unspoken value`() {
        val startingNumbers = listOf(0,3,6)

        val result = determineAge(startingNumbers, 4)

        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `should add age to starting list for previously spoken value up to 5th turn`() {
        val startingNumbers = listOf(0,3,6)

        val result = determineAge(startingNumbers, 5)

        assertThat(result).isEqualTo(3)
    }

    @Test
    fun `should add age to starting list for previously spoken values up to 9th turn`() {
        val startingNumbers = listOf(0,3,6)

        val result = determineAge(startingNumbers, 9)

        assertThat(result).isEqualTo(4)
    }

    @Test
    fun `should add age to starting list for previously spoken values up to 2020th turn`() {
        val startingNumbers = listOf(0,3,6)

        val result = determineAge(startingNumbers, 2020)

        assertThat(result).isEqualTo(436)
    }

    @Test
    fun `should add age to starting list for different input for previously spoken values up to 2020th turn`() {
        val startingNumbers = listOf(3,1,2)

        val result = determineAge(startingNumbers, 2020)

        assertThat(result).isEqualTo(1836)
    }

    @Test
    fun `should determine 2020th turn for full puzzle input`() {
        val startingNumbers = listOf(13,16,0,12,15,1)

        val result = determineAge(startingNumbers, 2020)
        println(result)

        assertThat(result).isEqualTo(319)
    }

    // Part 2

    @Test
    fun `should return age after 30000000 turns for 1st example`() {
        val startingStore = exampleLastSpokenStore

        val result = lastSpoken(30000000, startingStore, 4)

        assertThat(result).isEqualTo(175594)
    }

    @Test
    fun `should determine 30000000th turn for full puzzle input`() {
        val startingStore = fullLastSpokenStore

        val result = lastSpoken(30000000, startingStore, 7)
        println(result)

        assertThat(result).isEqualTo(2424)
    }
}