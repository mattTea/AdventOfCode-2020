package aoc

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test

class PassportsKtTest {
    @Test
    fun `should split input into 4 passports`() {
        val input = examplePassportInput

        val result = separatePassports(input)

        assertThat(result.size).isEqualTo(4)
    }

    @Test
    fun `should be valid for input with all fields present`() {
        val input = """
            ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
            byr:1937 iyr:2017 cid:147 hgt:183cm
        """.trimIndent()

        val result = isPassportComplete(input)

        assertThat(result).isTrue()
    }

    @Test
    fun `should be invalid for input with height field missing`() {
        val input = """
            iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
            hcl:#cfa07d byr:1929
        """.trimIndent()

        val result = isPassportComplete(input)

        assertThat(result).isFalse()
    }

    @Test
    fun `should be valid for input with countryId field missing`() {
        val input = """
            hcl:#ae17e1 iyr:2013
            eyr:2024
            ecl:brn pid:760753108 byr:1931
            hgt:179cm
        """.trimIndent()

        val result = isPassportComplete(input)

        assertThat(result).isTrue()
    }

    @Test
    fun `should be invalid for input with countryId and birthYear fields missing`() {
        val input = """
            hcl:#cfa07d eyr:2025 pid:166559648
            iyr:2011 ecl:brn hgt:59in
        """.trimIndent()

        val result = isPassportComplete(input)

        assertThat(result).isFalse()
    }

    @Test
    fun `should count 2 valid passports for example input`() {
        val result = countValidPassports(examplePassportInput)

        assertThat(result).isEqualTo(2)
    }

    @Test
    fun `should count all valid passports for full batch input`() {
        val result = countValidPassports(passportBatchInput)

        println(result)

        assertThat(result).isEqualTo(264)
    }

    // part 2

    @Test
    fun `should validate full set of individual fields`() {
        val input = """
            ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
            byr:1937 iyr:2017 cid:147 hgt:183cm
        """.trimIndent()

        val result = areFieldsValid(input)

        assertThat(result).isTrue()
    }

    @Test
    fun `should validate individual fields`() {
        val eclResult = isFieldValid(Pair("ecl", "gry"))
        val pidResult = isFieldValid(Pair("pid", "860033327"))
        val eyrResult = isFieldValid(Pair("eyr", "2020"))
        val hclResult = isFieldValid(Pair("hcl", "#fffffd"))
        val byrResult = isFieldValid(Pair("byr", "1937"))
        val iyrResult = isFieldValid(Pair("iyr", "2017"))
        val hgtResult = isFieldValid(Pair("hgt", "183cm"))

        assertThat(eclResult).isTrue()
        assertThat(pidResult).isTrue()
        assertThat(eyrResult).isTrue()
        assertThat(hclResult).isTrue()
        assertThat(byrResult).isTrue()
        assertThat(iyrResult).isTrue()
        assertThat(hgtResult).isTrue()
    }

    @Test
    fun `should count all valid and complete passports for full batch input`() {
        val result = countValidAndCompletePassports(passportBatchInput)

        println(result)

        assertThat(result).isEqualTo(224)
    }
}