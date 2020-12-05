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