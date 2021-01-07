package aoc

import assertk.assertThat
import assertk.assertions.containsOnly
import org.junit.jupiter.api.Test

class PocketDimensionKtTest {
    @Test
    fun `should return neighbouring cubes with status`() {
        val input = listOf(
            ".#.", // (0,1,0) - self, so not a neighbour
            "..#", // (1,2,0)
            "###"  // (2,0,0), (2,1,0), (2,2,0) - all outside of neighbouring cubes, so not returned
        )
        val cube = CubeCoord(0,1,0)

        val result = getNeighboursWithState(cube, input)

        assertThat(result).containsOnly(
            Neighbour('.', CubeCoord(-1, 0, -1)),
            Neighbour('.', CubeCoord(-1, 1, -1)),
            Neighbour('.', CubeCoord(-1, 2, -1)),
            Neighbour('.', CubeCoord(0, 0, -1)),
            Neighbour('.', CubeCoord(0, 1, -1)),
            Neighbour('.', CubeCoord(0, 2, -1)),
            Neighbour('.', CubeCoord(1, 0, -1)),
            Neighbour('.', CubeCoord(1, 1, -1)),
            Neighbour('.', CubeCoord(1, 2, -1)),
            Neighbour('.', CubeCoord(-1, 0, 0)),
            Neighbour('.', CubeCoord(-1, 1, 0)),
            Neighbour('.', CubeCoord(-1, 2, 0)),
            Neighbour('.', CubeCoord(0, 0, 0)),
            Neighbour('.', CubeCoord(0, 2, 0)),
            Neighbour('.', CubeCoord(1, 0, 0)),
            Neighbour('.', CubeCoord(1, 1, 0)),
            Neighbour('#', CubeCoord(1, 2, 0)),
            Neighbour('.', CubeCoord(-1, 0, 1)),
            Neighbour('.', CubeCoord(-1, 1, 1)),
            Neighbour('.', CubeCoord(-1, 2, 1)),
            Neighbour('.', CubeCoord(0, 0, 1)),
            Neighbour('.', CubeCoord(0, 1, 1)),
            Neighbour('.', CubeCoord(0, 2, 1)),
            Neighbour('.', CubeCoord(1, 0, 1)),
            Neighbour('.', CubeCoord(1, 1, 1)),
            Neighbour('.', CubeCoord(1, 2, 1))
        )
    }
}