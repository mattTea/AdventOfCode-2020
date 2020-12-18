package aoc

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class SeatingSystemKtTest {
    @Test
    fun `should return adjacent seats`() {
        val seatingPlan = listOf(
            "LLL",
            "LLL",
            "LLL"
        )

        val seatCoordinates = Coordinate(1,1)

        val result = adjacentSeats(seatCoordinates, seatingPlan)

        val expected = listOf(
            Coordinate(0,0),
            Coordinate(0,1),
            Coordinate(0,2),
            Coordinate(1,0),
            Coordinate(1,2),
            Coordinate(2,0),
            Coordinate(2,1),
            Coordinate(2,2)
        )

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should return adjacent seats when seat is at end of row`() {
        val seatingPlan = listOf(
            "LLL",
            "LLL",
            "LLL"
        )

        val seatCoordinates = Coordinate(1,0)

        val result = adjacentSeats(seatCoordinates, seatingPlan)

        val expected = listOf(
            Coordinate(0,0),
            Coordinate(0,1),
            Coordinate(1,1),
            Coordinate(2,0),
            Coordinate(2,1),
        )

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should return adjacent seats when seat is at end of row and column`() {
        val seatingPlan = listOf(
            "LLL",
            "LLL",
            "LLL"
        )

        val seatCoordinates = Coordinate(2,2)

        val result = adjacentSeats(seatCoordinates, seatingPlan)

        val expected = listOf(
            Coordinate(1,1),
            Coordinate(1,2),
            Coordinate(2,1),
        )

        assertThat(result).isEqualTo(expected)
    }
}