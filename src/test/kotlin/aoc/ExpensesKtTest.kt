package aoc

import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class ExpensesKtTest {
    @Test
    fun `should find 2 values that add up to 2020 from list of 3`() {
        val expenses = listOf(5, 2000, 20)

        val result = findTwo2020Entries(expenses)

        assertThat(result).containsOnly(2000, 20)
    }

    @Test
    fun `should find 2 values that add up to 2020 from input list`() {
        val result = findTwo2020Entries(input)

        println("2 entries: $result")

        assertThat(result.size).isEqualTo(2)
        assertThat(result).containsOnly(1087, 933)
    }

    @Test
    fun `should return product of entries that add up to 2020 from list of 3`() {
        val expenses = listOf(5, 2000, 20)

        val result = multiplyTwoExpenses(expenses)

        assertThat(result).isEqualTo(40000)
    }

    @Test
    fun `should return product of 2 entries that add up to 2020 from input list`() {
        val result = multiplyTwoExpenses(input)

        println("product of 2 entries: $result")

        assertThat(result).isEqualTo(1014171)
    }

    @Test
    fun `should find 3 values that add up to 2020 from list of 4`() {
        val expenses = listOf(5, 2000, 30, 15)

        val result = findThree2020Entries(expenses)

        assertThat(result.size).isEqualTo(3)
        assertThat(result).containsOnly(5, 2000, 15)
    }

    @Test
    fun `should return product of entries that add up to 2020 from list of 4`() {
        val expenses = listOf(5, 2000, 30, 15)

        val result = multiplyThreeExpenses(expenses)

        assertThat(result).isEqualTo(150000)
    }

    @Test
    fun `should return product of 3 entries that add up to 2020 from input list`() {
        val result = multiplyThreeExpenses(input)

        println("product of 3 entries: $result")

        assertThat(result).isEqualTo(46584630)
    }
}