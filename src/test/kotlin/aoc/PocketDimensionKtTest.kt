package aoc

import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class PocketDimensionKtTest {
    @Test
    fun `should return neighbouring cubes with status`() {
        val input = listOf(
            listOf(
                ".#.", // (0,0,1) - self, so not a neighbour
                "..#", // (0,1,2)
                "###"  // (0,2,0), (0,2,1), (0,2,2) - all outside of neighbouring cubes, so not returned
            )
        )

        val cube = Cube('#', CubeCoord(0, 0, 1))

        val result = getNeighboursWithState(cube, input)

        assertThat(result).containsOnly(
            Cube('.', CubeCoord(-1, -1, 0)),
            Cube('.', CubeCoord(-1, -1, 1)),
            Cube('.', CubeCoord(-1, -1, 2)),
            Cube('.', CubeCoord(-1, 0, 0)),
            Cube('.', CubeCoord(-1, 0, 1)),
            Cube('.', CubeCoord(-1, 0, 2)),
            Cube('.', CubeCoord(-1, 1, 0)),
            Cube('.', CubeCoord(-1, 1, 1)),
            Cube('.', CubeCoord(-1, 1, 2)),
            Cube('.', CubeCoord(0, -1, 0)),
            Cube('.', CubeCoord(0, -1, 1)),
            Cube('.', CubeCoord(0, -1, 2)),
            Cube('.', CubeCoord(0, 0, 0)),
            Cube('.', CubeCoord(0, 0, 2)),
            Cube('.', CubeCoord(0, 1, 0)),
            Cube('.', CubeCoord(0, 1, 1)),
            Cube('#', CubeCoord(0, 1, 2)),
            Cube('.', CubeCoord(1, -1, 0)),
            Cube('.', CubeCoord(1, -1, 1)),
            Cube('.', CubeCoord(1, -1, 2)),
            Cube('.', CubeCoord(1, 0, 0)),
            Cube('.', CubeCoord(1, 0, 1)),
            Cube('.', CubeCoord(1, 0, 2)),
            Cube('.', CubeCoord(1, 1, 0)),
            Cube('.', CubeCoord(1, 1, 1)),
            Cube('.', CubeCoord(1, 1, 2))
        )
    }

    @Test
    fun `should change cube state from active to inactive`() {
        val cube = Cube('#', CubeCoord(0,0,1))

        val input = listOf(
            listOf(
                ".#.", // (0,0,1) - self, so not a neighbour
                "..#", // (0,1,2)
                "###"  // (0,2,0), (0,2,1), (0,2,2) - all outside of neighbouring cubes, so not returned
            )
        )

        val result = manageCubeState(cube, input)
        val expected = Cube('.', CubeCoord(0,0,1))

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should return 11 active cubes after single state cycle`() {
        val input = listOf(
            listOf(
                ".#.",
                "..#",
                "###"
            )
        )

        val result = cycleState(input, 1)

        assertThat(result).isEqualTo(11)
    }

    @Test
    fun `should return 112 active cubes after six state cycles`() {
        val input = listOf(
            listOf(
                ".#.",
                "..#",
                "###"
            )
        )

        val result = cycleState(input)

        assertThat(result).isEqualTo(112)
    }

    @Test
    fun `should return count of active cubes after six state cycles for full input`() {
        val input = initialState

        val result = cycleState(input)
        println(result)

        assertThat(result).isEqualTo(284)
    }

    // Part 2

    @Test
    fun `should return 29 active cubes after single state cycle`() {
        val input = listOf(
            listOf(
                ".#.",
                "..#",
                "###"
            )
        )

        val result = part2CycleState(input, 1)

        assertThat(result).isEqualTo(29)
    }

    @Test
    fun `should return 848 active cubes after six state cycles`() {
        val input = listOf(
            listOf(
                ".#.",
                "..#",
                "###"
            )
        )

        val result = part2CycleState(input)

        assertThat(result).isEqualTo(848)
    }

    @Test
    fun `should return count of active cubes after six state cycles for full input part 2`() {
        val input = initialState

        val result = part2CycleState(input)
        println(result)

        assertThat(result).isEqualTo(2240)
    }
}