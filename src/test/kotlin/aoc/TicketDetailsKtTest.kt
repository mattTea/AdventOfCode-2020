package aoc

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class TicketDetailsKtTest {
    @Test
    fun `should return invalid ticket scanning error rate of 4`() {
        val input = """
            class: 1-3 or 5-7
            row: 6-11 or 33-44
            seat: 13-40 or 45-50

            your ticket:
            7,1,14

            nearby tickets:
            40,4,50
        """.trimIndent()

        val result = invalidValues(input)

        assertThat(result).isEqualTo(4)
    }

    @Test
    fun `should return invalid ticket scanning error rate of 71 for full example string input`() {
        val input = exampleTicketDetails

        val result = invalidValues(input)

        assertThat(result).isEqualTo(71)
    }

    @Test
    fun `should return invalid ticket scanning error rate for full input`() {
        val input = ticketDetails

        val result = invalidValues(input)
        println(result)

        assertThat(result).isEqualTo(22073)
    }
}