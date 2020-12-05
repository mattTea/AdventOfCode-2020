package aoc

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test

class PasswordsKtTest {
    @Test
    fun `should extract the single digit range of instances the password letter must appear`() {
        val input = "1-3 a: abcde"

        val result = policyInstances(input)

        assertThat(result).isEqualTo(1..3)
    }

    @Test
    fun `should extract the double digit range of instances the password letter must appear`() {
        val input = "11-33 a: abcde"

        val result = policyInstances(input)

        assertThat(result).isEqualTo(11..33)
    }

    @Test
    fun `should extract password policy letter`() {
        val input = "1-3 a: abcde"

        val result = policyLetter(input)

        assertThat(result).isEqualTo('a')
    }

    @Test
    fun `should extract password`() {
        val input = "1-3 a: abcde"

        val result = password(input)

        assertThat(result).isEqualTo("abcde")
    }

    @Test
    fun `should return true for valid password`() {
        val passwordInput = "1-3 a: abcde"

        val result = validPassword(passwordInput)

        assertThat(result).isTrue()
    }
}