package aoc

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class CustomsFormKtTest {
    @Test
    fun `should return 5 separate groups answers`() {
        val input = exampleCustomsInput

        val result = separateGroups(input)

        assertThat(result.size).isEqualTo(5)
    }

    @Test
    fun `should calculate unique letter responses in a group`() {
        val input = """
            ab
            ac
        """.trimIndent()

        val result = uniqueLetters(input)

        assertThat(result).isEqualTo(3)
    }

    @Test
    fun `should sum together unique letter from each group`() {
        val input = exampleCustomsInput

        val result = sumCounts(input)

        assertThat(result).isEqualTo(11)
    }

    @Test
    fun `should sum together unique letter from each group for full input`() {
        val input = customsFormInput

        val result = sumCounts(input)

        println(result)

        assertThat(result).isEqualTo(6947)
    }
}