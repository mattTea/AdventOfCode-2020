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
            SeatDetails('L', Coordinate(0,0)),
            SeatDetails('L', Coordinate(0,1)),
            SeatDetails('L', Coordinate(0,2)),
            SeatDetails('L', Coordinate(1,0)),
            SeatDetails('L', Coordinate(1,2)),
            SeatDetails('L', Coordinate(2,0)),
            SeatDetails('L', Coordinate(2,1)),
            SeatDetails('L', Coordinate(2,2))
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
            SeatDetails('L', Coordinate(0,0)),
            SeatDetails('L', Coordinate(0,1)),
            SeatDetails('L', Coordinate(1,1)),
            SeatDetails('L', Coordinate(2,0)),
            SeatDetails('L', Coordinate(2,1))
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
            SeatDetails('L', Coordinate(1,1)),
            SeatDetails('L', Coordinate(1,2)),
            SeatDetails('L', Coordinate(2,1))
        )

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should change empty seat ('L') to occupied ('#') when no seats adjacent to it are occupied`() {
        val seatingPlan = listOf(
            "LLL",
            "LLL",
            "LLL"
        )

        val seatDetails = SeatDetails('L', Coordinate(1,1))

        val result = checkSeat(seatDetails, seatingPlan)

        assertThat(result).isEqualTo('#')
    }

    @Test
    fun `should not change empty seat ('L') to occupied when one seat adjacent to it is occupied ('#')`() {
        val seatingPlan = listOf(
            "LLL",
            "LL#",
            "LLL"
        )

        val seatDetails = SeatDetails('L', Coordinate(1,1))

        val result = checkSeat(seatDetails, seatingPlan)

        assertThat(result).isEqualTo('L')
    }

    @Test
    fun `should change occupied seat ('#') to empty ('L') when 4 seats adjacent to it are also occupied`() {
        val seatingPlan = listOf(
            "L#L",
            "L##",
            "#L#"
        )

        val seatDetails = SeatDetails('#', Coordinate(1,1))

        val result = checkSeat(seatDetails, seatingPlan)

        assertThat(result).isEqualTo('L')
    }

    @Test
    fun `should change seat status for input`() {
        val seatingPlan = exampleSeatLayout

        val result = musicalChairs(seatingPlan)

        assertThat(result).isEqualTo(exampleSeatLayoutChangeAfterOneRound)
    }

    @Test
    fun `should change seat status for input on second run`() {
        val seatingPlan = exampleSeatLayoutChangeAfterOneRound

        val result = musicalChairs(seatingPlan)

        assertThat(result).isEqualTo(exampleSeatLayoutChangeAfterTwoRounds)
    }

    @Test
    fun `should run seat status changes until output becomes static`() {
        val seatingPlan = exampleSeatLayout

        val result = rotateSeating(seatingPlan)

        assertThat(result).isEqualTo(exampleSeatLayoutAfterBecomingStatic)
    }

    @Test
    fun `should return 37 occupied seats from example input`() {
        val seatingPlan = exampleSeatLayout

        val result = countOccupiedSeats(seatingPlan)

        assertThat(result).isEqualTo(37)
    }

    @Test
    fun `should return count of occupied seats from full input`() {
        val seatingPlan = seatLayout

        val result = countOccupiedSeats(seatingPlan)

        println(result)

        assertThat(result).isEqualTo(2303)
    }
}