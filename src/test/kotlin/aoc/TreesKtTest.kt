package aoc

import assertk.assertThat
import assertk.assertions.containsExactly
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

        val result = makeMapCorrectWidth(input, 3.0)

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

        val result = encounters(input, 3.0)

        assertThat(result).containsExactly(".", "#", ".")
    }

    @Test
    fun `should return 7 trees encountered`() {
        val input = exampleInput

        val result = treesEncountered(input, 3.0)

        assertThat(result).isEqualTo(7)
    }

    @Test
    fun `should return all trees encountered for full input`() {
        val result = treesEncountered(treeMapInput, 3.0)

        println(result)

        assertThat(result).isEqualTo(252)
    }

    // Part 2

    @Test
    fun `should return trees encountered for various gradients for example input`() {
        val input = exampleInput

        val resultForGradientOf1 = treesEncountered(input, 1.0)
        val resultForGradientOf3 = treesEncountered(input, 3.0)
        val resultForGradientOf5 = treesEncountered(input, 5.0)
        val resultForGradientOf7 = treesEncountered(input, 7.0)
        val resultForGradientOfHalf = treesEncountered(input, 0.5)

        assertThat(resultForGradientOf1).isEqualTo(2)
        assertThat(resultForGradientOf3).isEqualTo(7)
        assertThat(resultForGradientOf5).isEqualTo(3)
        assertThat(resultForGradientOf7).isEqualTo(4)
        assertThat(resultForGradientOfHalf).isEqualTo(2)
    }

    @Test
    fun `should return trees encountered for various gradients for full input and multiply together`() {
        val input = treeMapInput

        val resultForGradientOf1 = treesEncountered(input, 1.0)
        val resultForGradientOf3 = treesEncountered(input, 3.0)
        val resultForGradientOf5 = treesEncountered(input, 5.0)
        val resultForGradientOf7 = treesEncountered(input, 7.0)
        val resultForGradientOfHalf = treesEncountered(input, 0.5)

        val resultList = listOf(
            resultForGradientOf1.toLong(),
            resultForGradientOf3.toLong(),
            resultForGradientOf5.toLong(),
            resultForGradientOf7.toLong(),
            resultForGradientOfHalf.toLong()
        )

        println("list: $resultList")

        assertThat(resultForGradientOf1).isEqualTo(57)
        assertThat(resultForGradientOf3).isEqualTo(252)
        assertThat(resultForGradientOf5).isEqualTo(64)
        assertThat(resultForGradientOf7).isEqualTo(66)
        assertThat(resultForGradientOfHalf).isEqualTo(43)

        val productOfResults = resultList.reduce { total, next -> total * next }

        println("product: $productOfResults")

        assertThat(productOfResults).isEqualTo(2608962048)
    }
}