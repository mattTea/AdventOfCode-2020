package aoc

import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
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

//    @Test
//    fun `should return count of occupied seats from full input`() {
//        val seatingPlan = seatLayout
//
//        val result = countOccupiedSeats(seatingPlan)
//
//        println(result)
//
//        assertThat(result).isEqualTo(2303)
//    }

    // part 2

    @Test
    fun `should return seat coordinates for nearest seat`() {
        val seatingPlan = listOf(
            "LLL",
            "LL.",
            "LLL"
        )

        val seatCoordinates = Coordinate(2,2)
        val emptySpaceCoordinates = Coordinate(1,2)

        val result = getNearestSeat(
            seatCoordinates,
            emptySpaceCoordinates,
            seatingPlan
        )

        assertThat(result).isEqualTo(Coordinate(0,2))
    }

    @Test
    fun `should return seat coordinates for nearest seat after 2 empty locations`() {
        val seatingPlan = listOf(
            "LLLL",
            "LLL.",
            "LLL.",
            "LLLL"
        )

        val seatCoordinates = Coordinate(3,3)
        val emptySpaceCoordinates = Coordinate(2,3)

        val result = getNearestSeat(
            seatCoordinates,
            emptySpaceCoordinates,
            seatingPlan
        )

        assertThat(result).isEqualTo(Coordinate(0,3))
    }

    @Test
    fun `should return no seat coordinates when all locations in direction are floor`() {
        val seatingPlan = listOf(
            "LL.",
            "LL.",
            "LLL"
        )

        val seatCoordinates = Coordinate(2,2)
        val emptySpaceCoordinates = Coordinate(1,2)

        val result = getNearestSeat(
            seatCoordinates,
            emptySpaceCoordinates,
            seatingPlan
        )

        assertThat(result).isNull()
    }

    @Test
    fun `should return seat coordinates for nearest seat after 2 diagonal empty locations`() {
        val seatingPlan = listOf(
            "LLLL",
            "L.LL",
            "LL.L",
            "LLLL"
        )

        val seatCoordinates = Coordinate(3,3)
        val emptySpaceCoordinates = Coordinate(2,2)

        val result = getNearestSeat(
            seatCoordinates,
            emptySpaceCoordinates,
            seatingPlan
        )

        assertThat(result).isEqualTo(Coordinate(0,0))
    }

    @Test
    fun `should return adjacent visible seats with one a row further away`() {
        val seatingPlan = listOf(
            "LLL",
            "LL.",
            "LLL"
        )

        val seatCoordinates = Coordinate(2,2)

        val result = part2AdjacentSeats(seatCoordinates, seatingPlan)

        assertThat(result).containsOnly(
            SeatDetails('L', Coordinate(0,2)),
            SeatDetails('L', Coordinate(1,1)),
            SeatDetails('L', Coordinate(2,1))
        )
    }

    @Test
    fun `should return 26 occupied seats from example input`() {
        val seatingPlan = exampleSeatLayout

        val result = countOccupiedSeats(seatingPlan)

        assertThat(result).isEqualTo(26)
    }

    @Test
    fun `should return count of occupied seats from full input for part 2`() {
        val seatingPlan = seatLayout

        val result = countOccupiedSeats(seatingPlan)

        println(result)

        assertThat(result).isEqualTo(2057)
    }
}