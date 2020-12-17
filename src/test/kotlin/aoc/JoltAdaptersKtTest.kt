package aoc

import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class JoltAdaptersKtTest {
    @Test
    fun `should return pair list of volume of rating differences for example input 1`() {
        val input = exampleAdapterRatings1

        val result = ratingJumps(input)

        assertThat(result).containsOnly(
            Pair(7, 1),
            Pair(5, 3)
        )
    }

    @Test
    fun `should return pair list of volume of rating differences for example input 2`() {
        val input = exampleAdapterRatings2

        val result = ratingJumps(input)

        assertThat(result).containsOnly(
            Pair(22, 1), // 31 length
            Pair(10, 3)
        )
    }

    @Test
    fun `should calculate jolt difference product for example 1`() {
        val input = exampleAdapterRatings1

        val result = joltDiffProduct(input)

        assertThat(result).isEqualTo(35)
    }

    @Test
    fun `should calculate jolt difference product for example 2`() {
        val input = exampleAdapterRatings2

        val result = joltDiffProduct(input)

        assertThat(result).isEqualTo(220)
    }

    @Test
    fun `should calculate jolt difference product for full input`() {
        val input = adapterRatings

        val result = joltDiffProduct(input)

        println(result)

        assertThat(result).isEqualTo(2346)
    }

    // part 2

    @Test
    fun `should calculate 8 paths`() {
        val input = exampleAdapterRatings1

        val result = getPaths(input)

        assertThat(result).isEqualTo(8)
    }

    @Test
    fun `should calculate 19208 paths`() {
        val input = exampleAdapterRatings2

        val result = getPaths(input)

        assertThat(result).isEqualTo(19208)
    }

    @Test
    fun `should calculate paths for full input`() {
        val input = adapterRatings

        val result = getPaths(input)

        println(result)

        assertThat(result).isEqualTo(6044831973376)
    }
}