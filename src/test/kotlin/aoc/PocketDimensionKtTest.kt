package aoc

import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class PocketDimensionKtTest {
    @Test
    fun `should return neighbouring cubes`() {
        val cube = CubeCoord(0,1,0)

        val result = getNeighbours(cube)

        assertThat(result).containsOnly(
            CubeCoord(-1, 0, -1),
            CubeCoord(-1, 1, -1),
            CubeCoord(-1, 2, -1),
            CubeCoord(0, 0, -1),
            CubeCoord(0, 1, -1),
            CubeCoord(0, 2, -1),
            CubeCoord(1, 0, -1),
            CubeCoord(1, 1, -1),
            CubeCoord(1, 2, -1),
            CubeCoord(-1, 0, 0),
            CubeCoord(-1, 1, 0),
            CubeCoord(-1, 2, 0),
            CubeCoord(0, 0, 0),
            CubeCoord(0, 2, 0),
            CubeCoord(1, 0, 0),
            CubeCoord(1, 1, 0),
            CubeCoord(1, 2, 0),
            CubeCoord(-1, 0, 1),
            CubeCoord(-1, 1, 1),
            CubeCoord(-1, 2, 1),
            CubeCoord(0, 0, 1),
            CubeCoord(0, 1, 1),
            CubeCoord(0, 2, 1),
            CubeCoord(1, 0, 1),
            CubeCoord(1, 1, 1),
            CubeCoord(1, 2, 1)
        )
    }
}