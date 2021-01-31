package aoc

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class MatchMessagesKtTest {
    @Test
    fun `should return messages that meet one rule`() {
        val input = listOf(
            """0: 1""",
            """1: "a"""",
        )

        val rulesMap = convertRules(input)
        val result = followRule(rulesMap)

        assertThat(result).isEqualTo("a")
    }

    @Test
    fun `should return messages that meet two rules`() {
        val input = listOf(
            """0: 1""",
            """1: 2""",
            """2: "a"""",
        )

        val rulesMap = convertRules(input)
        val result = followRule(rulesMap)

        assertThat(result).isEqualTo("a")
    }

    @Test
    fun `should return message for two letters through two rules`() {
        val input = listOf(
            """0: 1 2""",
            """1: "a"""",
            """2: "b"""",
        )

        val rulesMap = convertRules(input)
        val result = followRule(rulesMap)

        assertThat(result).isEqualTo("ab")
    }
}