package aoc

import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.containsOnly
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class TreesKtTest {
    @Test
    fun `should check tree map strings are correct length`() {
        val input = listOf(
            ".#..",
            "..#.",
            "...#"
        )

        val expected = listOf(
            ".#...#..",
            "..#...#.",
            "...#...#"
        )

        val result = makeMapCorrectWidth(input)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should return the 3 things encountered at trajectory locations`() {
        val input = listOf(
            "..##........",
            "#...#...#..#",
            ".#....#..#..",
            "..#.#...#.#."
        )

        val result = encounters(input)

        assertThat(result).containsExactly(".", "#", ".")
    }

    @Test
    fun `should return 7 trees encountered`() {
        val input = listOf(
            "..##.........##.........##.........##.........##.........##.......",
            "#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..",
            ".#....#..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.",
            "..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#",
            ".#...##..#..#...##..#..#...##..#..#...##..#..#...##..#..#...##..#.",
            "..#.##.......#.##.......#.##.......#.##.......#.##.......#.##.....",
            ".#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#",
            ".#........#.#........#.#........#.#........#.#........#.#........#",
            "#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...",
            "#...##....##...##....##...##....##...##....##...##....##...##....#",
            ".#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#"
        )

        val result = treesEncountered(input)

        assertThat(result).isEqualTo(7)
    }

    @Test
    fun `should return all trees encountered for full input`() {
        val result = treesEncountered(treeMapInput)

        println(result)

        assertThat(result).isEqualTo(252)
    }
}