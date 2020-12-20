package aoc

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class NavigationKtTest {
    @Test
    fun `should return manhattan distance of 25`() {
        val input = exampleShipDirections

        val result = calculatePosition(input)

        assertThat(result).isEqualTo(25)
    }

    @Test
    fun `should return manhattan distance for full input`() {
        val input = shipDirections

        val result = calculatePosition(input)

        println(result)

        assertThat(result).isEqualTo(820)
    }

    // part 2

    @Test
    fun `should move waypoint N by 10 for instruction of N10`() {
        val startingWaypoint = Waypoint(10,1)
        val instruction = "N10"

        val result = moveWaypoint(startingWaypoint, instruction)

        assertThat(result).isEqualTo(Waypoint(10,11))
    }

    @Test
    fun `should move waypoint E by 4 for instruction of E4`() {
        val startingWaypoint = Waypoint(10,1)
        val instruction = "E4"

        val result = moveWaypoint(startingWaypoint, instruction)

        assertThat(result).isEqualTo(Waypoint(14,1))
    }

    @Test
    fun `should move waypoint S by 12 for instruction of S12`() {
        val startingWaypoint = Waypoint(10,1)
        val instruction = "S12"

        val result = moveWaypoint(startingWaypoint, instruction)

        assertThat(result).isEqualTo(Waypoint(10,-11))
    }

    @Test
    fun `should move waypoint W by 18 for instruction of W18`() {
        val startingWaypoint = Waypoint(10,1)
        val instruction = "W18"

        val result = moveWaypoint(startingWaypoint, instruction)

        assertThat(result).isEqualTo(Waypoint(-8,1))
    }

    @Test
    fun `should rotate waypoint clockwise by 90 degrees for instruction of R90`() {
        val startingWaypoint = Waypoint(10,1)
        val instruction = "R90"

        val result = rotateWaypoint(startingWaypoint, instruction)

        assertThat(result).isEqualTo(Waypoint(1,-10))
    }

    @Test
    fun `should rotate waypoint clockwise by 180 degrees for instruction of R180`() {
        val startingWaypoint = Waypoint(10,1)
        val instruction = "R180"

        val result = rotateWaypoint(startingWaypoint, instruction)

        assertThat(result).isEqualTo(Waypoint(-10,-1))
    }

    @Test
    fun `should rotate waypoint clockwise by 270 degrees for instruction of R270`() {
        val startingWaypoint = Waypoint(10,1)
        val instruction = "R270"

        val result = rotateWaypoint(startingWaypoint, instruction)

        assertThat(result).isEqualTo(Waypoint(-1,10))
    }

    @Test
    fun `should move towards waypoint`() {
        val position = ShipCoordinates(0,0)
        val waypoint = Waypoint(10,1)
        val instruction = "F10"

        val result = moveForward(PositionAndWaypoint(position, waypoint), instruction)

        assertThat(result).isEqualTo(ShipCoordinates(100,10))
    }

    @Test
    fun `should move towards negative waypoint`() {
        val position = ShipCoordinates(0,0)
        val waypoint = Waypoint(10,-1)
        val instruction = "F10"

        val result = moveForward(PositionAndWaypoint(position, waypoint), instruction)

        assertThat(result).isEqualTo(ShipCoordinates(100,-10))
    }

    @Test
    fun `should move towards waypoint when both coordinates are negative`() {
        val position = ShipCoordinates(0,0)
        val waypoint = Waypoint(-10,-1)
        val instruction = "F10"

        val result = moveForward(PositionAndWaypoint(position, waypoint), instruction)

        assertThat(result).isEqualTo(ShipCoordinates(-100,-10))
    }

    @Test
    fun `should return manhattan distance of 286 from example input`() {
        val input = exampleShipDirections

        val result = newCalculatePosition(input)

        assertThat(result).isEqualTo(286)
    }

    @Test
    fun `should return manhattan distance from full input`() {
        val input = shipDirections

        val result = newCalculatePosition(input)

        println(result)

        assertThat(result).isEqualTo(66614)
    }
}