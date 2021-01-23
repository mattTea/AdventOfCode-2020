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

//    @Test
//    fun `should return left to right operator precedence for 3 values with brackets`() {
//        val input = "1 + (2 * 3)"
//
//        val result = newOrder(input)
//
//        assertThat(result).isEqualTo(7)
//    }
}