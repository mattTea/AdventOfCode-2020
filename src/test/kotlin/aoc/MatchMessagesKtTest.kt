package aoc

import assertk.assertThat
import assertk.assertions.containsOnly
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

//    @Test
//    fun `should return messages that meet simple rule 0`() {
//        val input = listOf(
//            """0: 1 2""",
//            """1: "a"""",
//            """2: "b""""
//        )
//
//        val result = validMessages(input)
//
//        assertThat(result).containsOnly("ab")
//    }
//
//    @Test
//    fun `should return messages that meet rule 0`() {
//        val input = listOf(
//            """0: 1 2""",
//            """1: "a"""",
//            """2: 1 3 | 3 1""",
//            """3: "b""""
//        )
//
//        val result = validMessages(input)
//
//        assertThat(result).containsOnly("aab", "aba")
//    }
}