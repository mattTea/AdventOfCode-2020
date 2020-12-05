package aoc

import assertk.assertThat
import assertk.assertions.containsExactly
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

    @Test
    fun `should return two valid passwords in list of three`() {
        val input = listOf(
            "1-3 a: abcde",
            "1-3 b: cdefg",
            "2-9 c: ccccccccc"
        )

        val result = numberValidPasswords(input)

        assertThat(result).isEqualTo(2)
    }

    @Test
    fun `should return number of valid password in full input list`() {
        val result = numberValidPasswords(passwordInput)

        println("number of valid passwords: $result")

        assertThat(result).isEqualTo(536)
    }

    // part 2
    // policyLetter() remains the same
    // password() remains the same

    @Test
    fun `should extract first and second letter positions`() {
        val input = "11-33 a: abcde"

        val result = letterPositions(input)

        assertThat(result).containsExactly(11, 33)
    }

    @Test
    fun `should return true for valid password with part 2 rules`() {
        val passwordInput = "1-3 a: abcde"

        val result = part2ValidPassword(passwordInput)

        assertThat(result).isTrue()
    }

    @Test
    fun `should return one valid password in list of three with part 2 rules`() {
        val input = listOf(
            "1-3 a: abcde",
            "1-3 b: cdefg",
            "2-9 c: ccccccccc"
        )

        val result = part2NumberValidPasswords(input)

        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `should return number of valid password in full input list with part 2 rules`() {
        val result = part2NumberValidPasswords(passwordInput)

        println("number of valid passwords: $result")

        assertThat(result).isEqualTo(558)
    }
}