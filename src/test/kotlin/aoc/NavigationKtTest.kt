package aoc

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class NavigationKtTest {
    @Test
    fun `should return manhattan distance of 25`() {
        val input = exampleShipDirections

        val result = calculatePosition(input)

        assertThat(result).isEqualTo(25)
    }

    @Test
    fun `should return manhattan distance for full input`() {
        val input = shipDirections

        val result = calculatePosition(input)

        println(result)

        assertThat(result).isEqualTo(820)
    }
}