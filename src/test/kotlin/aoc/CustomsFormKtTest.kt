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

        val result = sumUniqueCounts(input)

        assertThat(result).isEqualTo(11)
    }

    @Test
    fun `should sum together unique letter from each group for full input`() {
        val input = customsFormInput

        val result = sumUniqueCounts(input)

        println(result)

        assertThat(result).isEqualTo(6947)
    }

    // part 2
    // separateGroups remains the same

    @Test
    fun `should calculate commonly answered letter in a group`() {
        val input = """
            ab
            ac
        """.trimIndent()

        val result = commonLetters(input)

        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `should sum together common letters from each group`() {
        val input = exampleCustomsInput

        val result = sumCommonCounts(input)

        assertThat(result).isEqualTo(6)
    }

    @Test
    fun `should sum together common letters from each group for full input`() {
        val input = customsFormInput

        val result = sumCommonCounts(input)

        println(result)

        assertThat(result).isEqualTo(3398)
    }
}