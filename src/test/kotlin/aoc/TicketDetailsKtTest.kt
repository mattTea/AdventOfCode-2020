package aoc

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class TicketDetailsKtTest {
    @Test
    fun `should return invalid ticket scanning error rate of 4`() {
        val validation = listOf(
            "class: 1-3 or 5-7",
            "row: 6-11 or 33-44",
            "seat: 13-40 or 45-50"
        )

        val tickets = listOf("40,4,50")

        val result = invalidValues(validation, tickets)

        assertThat(result).isEqualTo(4)
    }

    @Test
    fun `should return invalid ticket scanning error rate of 71`() {
        val validation = listOf(
            "class: 1-3 or 5-7",
            "row: 6-11 or 33-44",
            "seat: 13-40 or 45-50"
        )

        val tickets = listOf(
            "7,3,47",
            "40,4,50",
            "55,2,20",
            "38,6,12"
        )

        val result = invalidValues(validation, tickets)

        assertThat(result).isEqualTo(71)
    }

    @Test
    fun `should return invalid ticket scanning error rate of 71 for full string input`() {
        val input = """
            class: 1-3 or 5-7
            row: 6-11 or 33-44
            seat: 13-40 or 45-50

            your ticket:
            7,1,14

            nearby tickets:
            7,3,47
            40,4,50
            55,2,20
            38,6,12
        """.trimIndent()

        val validation = input.substringBefore("\n\n").split("\n")
        val tickets = input.substringAfter("nearby tickets:\n").split("\n")

        val result = invalidValues(validation, tickets)

        assertThat(result).isEqualTo(71)
    }
}