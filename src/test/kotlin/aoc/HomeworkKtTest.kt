package aoc

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class HomeworkKtTest {
    @Test
    fun `should return left to right operator precedence for 3 values`() {
        val input = "1 + 2 * 3"

        val result = newOrder(input)

        assertThat(result).isEqualTo(9)
    }

    @Test
    fun `should calculate expression between braces`() {
        val input = "1 + (2 * 3)"

        val result = calculateExpressionWithBraces(input)

        assertThat(result).isEqualTo(7)
    }

    @Test
    fun `should return left to right operator precedence for 3 values using higher wrapper function`() {
        val input = "1 + 2 * 3"

        val result = calculateExpressionWithBraces(input)

        assertThat(result).isEqualTo(9)
    }

    @Test
    fun `should calculate 51 for more complex braced expression`() {
        val input = "1 + (2 * 3) + (4 * (5 + 6))"

        val result = calculateExpressionWithBraces(input)

        assertThat(result).isEqualTo(51)
    }

    @Test
    fun `should calculate 36 from unbraced expression`() {
        val input = "8 * 3 + 9 + 3"

        val result = newOrder(input)

        assertThat(result).isEqualTo(36)
    }

    @Test
    fun `should calculate 432 from more complex unbraced expression`() {
        val input = "8 * 3 + 9 + 3 * 4 * 3"

        val result = newOrder(input)

        assertThat(result).isEqualTo(432)
    }

    @Test
    fun `should calculate 437 from more complex braced expression`() {
        val input = "5 + (8 * 3 + 9 + 3 * 4 * 3)"

        val result = calculateExpressionWithBraces(input)

        assertThat(result).isEqualTo(437)
    }

    @Test
    fun `should calculate 12240 from complex braced expression`() {
        val input = "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"

        val result = calculateExpressionWithBraces(input)

        assertThat(result).isEqualTo(12240)
    }

    @Test
    fun `should calculate 13632 from complex braced expression`() {
        val input = "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"

        val result = calculateExpressionWithBraces(input)

        assertThat(result).isEqualTo(13632)
    }

    @Test
    fun `should calculate result from full input`() {
        val input = homeworkLines

        val result = runAllHomeworkLines(input)
        println(result)

        assertThat(result).isEqualTo(15285807527593)
    }
}