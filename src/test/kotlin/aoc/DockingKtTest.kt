package aoc

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class DockingKtTest {
    @Test
    fun `should convert decimal Int to 36 bit binary String`() {
        val result = 11L.toBinary()

        assertThat(result).isEqualTo("000000000000000000000000000000001011")
    }

    @Test
    fun `should apply bit mask`() {
        val input = "000000000000000000000000000000001011"
        val bitMask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"

        val result = applyBitMask(input, bitMask)

        assertThat(result).isEqualTo("000000000000000000000000000001001001")
    }

    @Test
    fun `should convert 36 bit binary String to decimal Int`() {
        val result = "000000000000000000000000000001001001".toDecimal()

        assertThat(result).isEqualTo(73)
    }

    @Test
    fun `should store decimal value of 73 in memory index 8`() {
        val input = listOf(
            "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
            "mem[8] = 11"
        )

        val result = storeDockingInput(input)

        assertThat(result).isEqualTo(73)
    }

    @Test
    fun `should store decimal values of 101 and 64 in memory`() {
        val input = exampleDockingInput

        val result = storeDockingInput(input)

        assertThat(result).isEqualTo(165)
    }

    @Test
    fun `should determine sum of all values in full input`() {
        val input = dockingInput

        val result = storeDockingInput(input)

        assertThat(result).isEqualTo(9879607673316)
    }

    // Part 2

    @Test
    fun `should create 2 possible options for a maskedAddress with 1 'X' present`() {
        val maskedAddress = "00000000000000000000000000000001101X"

        val expected = listOf(
            "000000000000000000000000000000011010",
            "000000000000000000000000000000011011"
        )

        val result = explodeFloatingBits(maskedAddress)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should create all possible options for a maskedAddress with 2 'X's present`() {
        val maskedAddress = "000000000000000000000000000000X1101X"

        val expected = listOf(
            "000000000000000000000000000000011010",
            "000000000000000000000000000000011011",
            "000000000000000000000000000000111010",
            "000000000000000000000000000000111011"
        )

        val result = explodeFloatingBits(maskedAddress)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should apply bit mask to memory address and return all possible addresses for 2 Xs in mask`() {
        val memoryAddress = "000000000000000000000000000000101010"
        val memoryBitMask = "000000000000000000000000000000X1001X"

        val expected = listOf(26L,27L,58L,59L)

        val result = memoryBitMask(memoryAddress, memoryBitMask)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should apply bit mask to memory address and return all possible addresses for 3 Xs in mask`() {
        val memoryAddress = "000000000000000000000000000000011010"
        val memoryBitMask = "00000000000000000000000000000000X0XX"

        val expected = listOf(16L,17L,18L,19L,24L,25L,26L,27L)

        val result = memoryBitMask(memoryAddress, memoryBitMask)

        assertThat(result).isEqualTo(expected)
    }
}

