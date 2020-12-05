package aoc

fun numberValidPasswords(passwords: List<String>): Int =
    passwords.filter { validPassword(it) }.size

fun validPassword(passwordAndPolicy: String): Boolean {
    val letter = policyLetter(passwordAndPolicy)
    val instancesRequired = policyInstances(passwordAndPolicy)
    val password = password(passwordAndPolicy)

    val numberOfInstances = password.filter { it == letter }.length

    return instancesRequired.contains(numberOfInstances)
}

fun policyInstances(input: String): IntRange {
    val start = input.substringBefore('-').toInt()
    val end = input
        .substringAfter('-')
        .substringBefore(' ')
        .toInt()

    return start..end
}

fun policyLetter(input: String): Char =
    input.substringAfter(' ').substringBefore(':').single()

fun password(input: String): String =
    input.substringAfter(": ")

// part 2

fun part2NumberValidPasswords(passwords: List<String>): Int =
    passwords.filter { part2ValidPassword(it) }.size

fun part2ValidPassword(passwordAndPolicy: String): Boolean {
    val letter = policyLetter(passwordAndPolicy)
    val letterPositions = letterPositions(passwordAndPolicy)
    val password = password(passwordAndPolicy)

    return password.filterIndexed { index, char ->
        (letterPositions[0] == index + 1 && char == letter) ||
            (letterPositions[1] == index + 1 && char == letter)
    }.length == 1
}

fun letterPositions(input: String): List<Int> {
    val firstLetter = input.substringBefore('-').toInt()
    val secondLetter = input
        .substringAfter('-')
        .substringBefore(' ')
        .toInt()

    return listOf(firstLetter, secondLetter)
}