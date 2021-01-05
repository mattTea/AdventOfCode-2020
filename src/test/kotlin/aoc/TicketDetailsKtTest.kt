package aoc

import assertk.assertThat
import assertk.assertions.containsExactly
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

        val result = errorRate(input)

        assertThat(result).isEqualTo(4)
    }

    @Test
    fun `should return invalid ticket scanning error rate of 71 for full example string input`() {
        val input = exampleTicketDetails

        val result = errorRate(input)

        assertThat(result).isEqualTo(71)
    }

    @Test
    fun `should return invalid ticket scanning error rate for full input`() {
        val input = ticketDetails

        val result = errorRate(input)
        println(result)

        assertThat(result).isEqualTo(22073)
    }

    // Part 2

    @Test
    fun `should discard invalid tickets`() {
        val input = exampleTicketDetails

        val result = validTickets(input)

        assertThat(result).isEqualTo(listOf("7,3,47"))
    }

    @Test
    fun `should return field order for simple example`() {
        val input = """
            class: 0-1 or 4-19
            row: 0-5 or 8-19
            seat: 0-13 or 16-19

            your ticket:
            11,12,13

            nearby tickets:
            3,9,18
            15,1,5
            5,14,9
        """.trimIndent()

        val result = fieldOrder(input)

        assertThat(result).isEqualTo(listOf("row","class","seat"))
    }

    @Test
    fun `should multiply values of fields at indexes which start with c in my ticket`() {
        val input = """
            class: 0-1 or 4-19
            row: 0-5 or 8-19
            cushion: 0-13 or 16-19

            your ticket:
            11,12,13

            nearby tickets:
            3,9,18
            15,1,5
            5,14,9
        """.trimIndent()

        val expected = 12*13L
        val result = multiplyDepartures(input, "c")

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should multiply values of fields at indexes which start with departure in my full input`() {
        val input = ticketDetails

        val result = multiplyDepartures(input, "departure")
        println(result)

        assertThat(result).isEqualTo(1346570764607)
    }
}