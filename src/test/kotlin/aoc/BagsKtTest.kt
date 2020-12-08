package aoc

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import org.junit.jupiter.api.Test

class BagsKtTest {
    @Test
    fun `should extract bags containing shiny gold bag`() {
        val input = "bright white bags contain 1 shiny gold bag"

        val result = extractContainingBagColours(listOf("shiny gold"), input)

        assertThat(result).isEqualTo("bright white")
    }

    @Test
    fun `should extract no bags when rule does not contain desired bag colour`() {
        val input = "bright white bags contain 1 shiny gold bag"

        val result = extractContainingBagColours(listOf("muted yellow"), input)

        assertThat(result).isNull()
    }

    @Test
    fun `should return 1 bag colour that contains one shiny gold bag`() {
        val input = listOf("bright white bags contain 1 shiny gold bag")

        val result = bagsContaining(listOf("shiny gold"), input)

        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `should return 4 bag colours that can contain shiny gold bags`() {
        val input = exampleBagRules

        val result = bagsContaining(listOf("shiny gold"), input)

        assertThat(result).isEqualTo(4)
    }

    @Test
    fun `should return all bag colours that can contain shiny gold bags from full list`() {
        val input = bagRules

        val result = bagsContaining(listOf("shiny gold"), input)

        println(result)

        assertThat(result).isEqualTo(287)
    }
}