package aoc

fun separateGroups(input: String): List<String> =
    input.split("\n\n")

fun uniqueLetters(input: String): Int =
    input
        .replace("\n", "")
        .toList()
        .distinct()
        .size

fun sumCounts(input: String): Int =
    separateGroups(input)
        .map { uniqueLetters(it) }
        .reduce { total, next -> total + next }
