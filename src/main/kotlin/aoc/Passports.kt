package aoc

fun countValidPassports(batchInput: String): Int =
    separatePassports(batchInput)
        .filter { isPassportComplete(it) }.size

fun separatePassports(batchInput: String): List<String> =
    batchInput.split("\n\n")

fun isPassportComplete(input: String): Boolean {
    val fieldsPresent = input
        .split(" ", "\n")
        .map { it.substringBefore(':') }

    return fieldsPresent.containsAll(fields)
}

// part 2

fun countValidAndCompletePassports(batchInput: String): Int =
    separatePassports(batchInput)
        .filter { isPassportComplete(it) }
        .filter { areFieldsValid(it) }.size

fun areFieldsValid(input: String): Boolean {
    val fieldsAndValues = input
        .split(" ", "\n")
        .map {
            Pair(it.substringBefore(":"), it.substringAfter(":"))
        }

    val fieldValidations = fieldsAndValues.map { isFieldValid(it) }

    return !fieldValidations.contains(false)
}

fun isFieldValid(fieldAndValue: Pair<String, String>): Boolean {
    val field = fieldAndValue.first
    val value = fieldAndValue.second

    return when (field) {
        "ecl" -> listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").any { it == value }
        "pid" -> value.length == 9
        "eyr" -> value.length == 4 && value.toInt() in 2020..2030
        "hcl" -> value.first() == '#' && value.length == 7 && Regex("([a-f]|[0-9]){6}").matches(value.drop(1))
        "byr" -> value.length == 4 && value.toInt() in 1920..2002
        "iyr" -> value.length == 4 && value.toInt() in 2010..2020
        "hgt" -> validateHeight(value)
        else -> true
    }
}

val fields = listOf(
    "ecl",
    "pid",
    "eyr",
    "hcl",
    "byr",
    "iyr",
    "hgt"
)

private fun validateHeight(value: String): Boolean {
    val unit = value.takeLast(2)
    val measurement = value.take(value.length - 2)
    val unitIsValid = unit == "cm" || unit == "in"
    val measurementIsValid =
        when (unit) {
            "cm" -> measurement.toInt() in 150..193
            "in" -> measurement.toInt() in 59..76
            else -> false
        }

    return unitIsValid && measurementIsValid
}

