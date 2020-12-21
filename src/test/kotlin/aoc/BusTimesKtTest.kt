package aoc

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class BusTimesKtTest {
    @Test
    fun `should return 295 from example input`() {
        val input = exampleBusTimes

        val result = busAndWait(input)

        assertThat(result).isEqualTo(295)
    }

    @Test
    fun `should return value from full input`() {
        val input = busTimes

        val result = busAndWait(input)

        println(result)

        assertThat(result).isEqualTo(2045)
    }
}