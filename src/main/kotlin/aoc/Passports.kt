package aoc

fun countValidPassports(batchInput: String): Int =
    separatePassports(batchInput)
        .filter { isPassportValid(it) }.size

fun separatePassports(batchInput: String): List<String> =
    batchInput.split("\n\n")

fun isPassportValid(input: String): Boolean {
    val fieldsPresent = input
        .split(" ", "\n")
        .map { it.substringBefore(':') }

    return fieldsPresent.containsAll(fields)
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