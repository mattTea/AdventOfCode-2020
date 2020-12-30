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
}

