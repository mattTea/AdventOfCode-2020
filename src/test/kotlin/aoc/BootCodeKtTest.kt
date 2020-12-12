package aoc

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class BootCodeKtTest {
    @Test
    fun `should execute acc and jmp commands and return 1`() {
        val instructions = listOf("nop +5", "acc +1", "jmp -1")

        val result = runProgram(instructions)

        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `should execute instructions and return acc as 5`() {
        val instructions = exampleBootCode

        val result = runProgram(instructions)

        assertThat(result).isEqualTo(5)
    }

    @Test
    fun `should execute full input instructions and return acc`() {
        val instructions = bootCode

        val result = runProgram(instructions)

        assertThat(result).isEqualTo(1548)
    }
}