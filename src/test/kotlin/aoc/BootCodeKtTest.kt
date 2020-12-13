package aoc

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import org.junit.jupiter.api.Test

class BootCodeKtTest {
    // part 1

    @Test
    fun `should execute acc and jmp commands and return 1`() {
        val instructions = listOf("nop +5", "acc +1", "jmp -1")

        val result = runProgram(instructions)

        assertThat(result.acc).isEqualTo(1)
    }

    @Test
    fun `should execute instructions and return acc as 5`() {
        val instructions = exampleBootCode

        val result = runProgram(instructions)

        assertThat(result.acc).isEqualTo(5)
    }

    @Test
    fun `should execute full input instructions and return acc`() {
        val instructions = bootCode

        val result = runProgram(instructions)

        assertThat(result.acc).isEqualTo(1548)
    }

    // part 2

    @Test
    fun `should return new instructions`() {
        val instructions = exampleBootCode
        val index = 0

        val result = createNewInstructions(index, instructions)

        val expected = listOf(
            "jmp +0",
            "acc +1",
            "jmp +4",
            "acc +3",
            "jmp -3",
            "acc -99",
            "acc +1",
            "jmp -4",
            "acc +6"
        )

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should loop and return acc of zero`() {
        val instructions = exampleBootCode
        val index = 0

        val result = checkInstruction(instructions, index)

        assertThat(result).isFalse()
    }

    /*
    runProgram needs to return a Pair<Int, String> for acc and exitCode
    - i.e. Pair(4, "terminated") or Pair(45, "loop")
    */

    @Test
    fun `should return acc of 5 and exit code of loop from program`() {
        val instructions = exampleBootCode

        val result = runProgram(instructions)

        assertThat(result).isEqualTo(Output(5, "loop"))
    }

    @Test
    fun `should return acc of 8 and exit code of terminated from program`() {
        val instructions = listOf(
            "nop +0",
            "acc +1",
            "jmp +4",
            "acc +3",
            "jmp -3",
            "acc -99",
            "acc +1",
            "nop -4",
            "acc +6"
        )

        val result = runProgram(instructions)

        assertThat(result).isEqualTo(Output(8, "terminated"))
    }
}