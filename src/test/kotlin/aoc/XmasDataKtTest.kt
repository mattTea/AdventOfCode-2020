package aoc

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test

class XmasDataKtTest {
    @Test
    fun `should return invalid for value that is not the sum of any 2 values in previous 5 values`() {
        val input = listOf(1L,2L,3L,4L,5L)
        val valueToCheck = 10L

        val result = isValid(valueToCheck, input)

        assertThat(result).isFalse()
    }

    @Test
    fun `should return valid for value that is the sum of any 2 values in previous 5 values`() {
        val input = listOf(1L,2L,3L,4L,5L)
        val valueToCheck = 9L

        val result = isValid(valueToCheck, input)

        assertThat(result).isTrue()
    }

    @Test
    fun `should create new input list of preamble length`() {
        val input = listOf(1L,2L,3L,4L,5L,10L)
        val preambleSize = 5

        val result = getNewInput(5, input, preambleSize)

        assertThat(result).isEqualTo(listOf(1L,2L,3L,4L,5L))
    }

    @Test
    fun `should create new input list of preamble length from longer original input`() {
        val input = listOf<Long>(1,2,3,4,5,6,7,8,9,10)
        val preambleSize = 5

        val result = getNewInput(7, input, preambleSize)

        assertThat(result).isEqualTo(listOf<Long>(3,4,5,6,7))
    }

    @Test
    fun `should return first number that is not the sum of any 2 preceding values in the previous 5 values`() {
        val input = listOf(1L,2L,3L,4L,5L,10L)
        val preambleSize = 5

        val result = firstInvalidValue(input, preambleSize)

        assertThat(result).isEqualTo(10)
    }

    @Test
    fun `should return first number that is not the sum of any 2 preceding values in the previous 5 values for example input`() {
        val input = exampleXmasData
        val preambleSize = 5

        val result = firstInvalidValue(input, preambleSize)

        assertThat(result).isEqualTo(127)
    }

    @Test
    fun `should return first number that is not the sum of any 2 preceding values in the previous 25 values for full input`() {
        val input = xmasData
        val preambleSize = 25

        val result = firstInvalidValue(input, preambleSize)

        println(result)

        assertThat(result).isEqualTo(1398413738)
    }

    // part 2

    @Test
    fun `should find contiguous list of 2 numbers that add up to 3`() {
        val input = listOf<Long>(1,2)

        val result = findContiguousValues(3, input)

        assertThat(result).isEqualTo(listOf<Long>(1,2))
    }

    @Test
    fun `should find contiguous list of 3 numbers that add up to 6`() {
        val input = listOf<Long>(1,2,3)

        val result = findContiguousValues(6, input)

        assertThat(result).isEqualTo(listOf<Long>(1,2,3))
    }

    @Test
    fun `should find contiguous list of 3 numbers that add up to 6 after increasing start index`() {
        val input = listOf<Long>(15,1,2,3)

        val result = findContiguousValues(6, input)

        assertThat(result).isEqualTo(listOf<Long>(1,2,3))
    }

    @Test
    fun `should find contiguous list of 4 numbers that add up to 127 for example input`() {
        val input = exampleXmasData

        val result = findContiguousValues(127, input)

        assertThat(result).isEqualTo(listOf<Long>(15,25,47,40))
    }

    @Test
    fun `should extract 15 and 47 as smallest and largest values in input and add together`() {
        val input = listOf<Long>(15,25,47,40)

        val result = findWeakness(input)

        assertThat(result).isEqualTo(62)
    }

    @Test
    fun `should find weakness value of 62 for example input`() {
        val input = exampleXmasData

        val result = findWeakness(findContiguousValues(127, input))

        assertThat(result).isEqualTo(62)
    }

    @Test
    fun `should find weakness value for full input`() {
        val input = xmasData

        val result = findWeakness(findContiguousValues(1398413738, input))

        println(result)

        assertThat(result).isEqualTo(169521051)
    }
}