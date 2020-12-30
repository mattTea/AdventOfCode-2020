package aoc

import assertk.assertThat
import assertk.assertions.containsOnly
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

    // part 2

//    @Test
//    fun `should calculate timestamp for 2 buses`() {
//        val input = "7,17"
//
//        val result = firstTimestamp(input)
//
//        assertThat(result).isEqualTo(84)
//    }

//    @Test
//    fun `should create remainder list for number of list of 7,17`() {
//        val input = "7,17,x"
//
//        val result = remainders(input)
//
//        assertThat(result).containsOnly(0,16)
//    }
//
//    @Test
//    fun `should create numbers list for example input`() {
//        val input = exampleBusTimes.second
//
//        val result = numbers(input)
//
//        assertThat(result).containsOnly(7,13,59,31,19)
//    }

    @Test
    fun `should calculate runtime for list of length 2`() {
        val input = listOf(7,13)

        val result = runTime(input)

        assertThat(result).isEqualTo(77)
    }

    @Test
    fun `should calculate runtime for list of length 3`() {
        val input = listOf(7,13,59)

        val result = runTime(input)

        assertThat(result).isEqualTo(350)
    }
}