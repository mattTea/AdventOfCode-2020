package aoc

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class SeatNumbersKtTest {
    @Test
    fun `should return first half of list for input of 'F'`() {
        val input = (0..127).toList()

        val result = input.takeHalf('F')

        assertThat(result).isEqualTo((0..63).toList())
    }

    @Test
    fun `should return second half of list for input of 'B'`() {
        val input = (0..127).toList()

        val result = input.takeHalf('B')

        assertThat(result).isEqualTo((64..127).toList())
    }

    @Test
    fun `should return row 70`() {
        val input = "BFFFBBFRRR"

        val result = findRow(input)

        assertThat(result).isEqualTo(70)
    }

    @Test
    fun `should return first half of columns for input of 'L'`() {
        val input = (0..7).toList()

        val result = input.takeHalf('L')

        assertThat(result).isEqualTo(listOf(0,1,2,3))
    }

    @Test
    fun `should return second half of columns for input of 'R'`() {
        val input = (0..7).toList()

        val result = input.takeHalf('R')

        assertThat(result).isEqualTo(listOf(4,5,6,7))
    }

    @Test
    fun `should return column 7`() {
        val input = "BFFFBBFRRR"

        val result = findColumn(input)

        assertThat(result).isEqualTo(7)
    }

    @Test
    fun `should return seat ID of 567`() {
        val input1 = "BFFFBBFRRR"
        val input2 = "FFFBBBFRRR"
        val input3 = "BBFFBBFRLL"

        val result1 = calculateSeatId(input1)
        val result2 = calculateSeatId(input2)
        val result3 = calculateSeatId(input3)

        assertThat(result1).isEqualTo(567)
        assertThat(result2).isEqualTo(119)
        assertThat(result3).isEqualTo(820)
    }

    @Test
    fun `should return 820 as highest seat ID`() {
        val input = exampleSeatNumberInput

        val result = highestSeatId(input)

        assertThat(result).isEqualTo(820)
    }

    @Test
    fun `should return highest seat ID from full input list`() {
        val input = seatNumberInput

        val result = highestSeatId(input)

        println(result)

        assertThat(result).isEqualTo(880)
    }

    // part 2 - seat number is 731

    @Test
    fun `should return list of 3 seat numbers`() {
        val input = seatNumberInput

        val result = possibleSeatNumbers(input)

        println(result.sorted()) // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 731]

        assertThat(result.size).isEqualTo(13)
    }

    @Test
    fun `should return single seat ID`() {
        val input = seatNumberInput

        val result = findSeatNumber(input)

        println(result)

        assertThat(result).isEqualTo(731)
    }
}