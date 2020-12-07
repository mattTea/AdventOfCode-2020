package aoc

fun separateGroups(input: String): List<String> =
    input.split("\n\n")

fun uniqueLetters(input: String): Int =
    input
        .replace("\n", "")
        .toList()
        .distinct()
        .size

fun sumUniqueCounts(input: String): Int =
    separateGroups(input)
        .map { uniqueLetters(it) }
        .reduce { total, next -> total + next }

// part 2

fun commonLetters(input: String): Int {
    val numberOfResponses = input.filter { it == '\n' }.length + 1
    val list = input.replace("\n", "").toList()
    val counts = list.filter{ it in list }.groupingBy { it }.eachCount()

    return counts.filter {
        it.value == numberOfResponses
    }.size
}

fun sumCommonCounts(input: String): Int =
    separateGroups(input)
        .map { commonLetters(it) }
        .reduce { total, next -> total + next }