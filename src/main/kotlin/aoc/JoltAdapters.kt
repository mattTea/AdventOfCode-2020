package aoc

fun joltDiffProduct(input: List<Int>): Int =
    ratingJumps(input).first().first * ratingJumps(input).last().first

fun ratingJumps(input: List<Int>): List<Pair<Int, Int>> {
    val deviceRating = input.maxOrNull()!! + 3
    val sortedInput = input.plus(0).plus(deviceRating).sorted()

    return sortedInput
        .mapIndexedNotNull { index, rating ->
            if (index < sortedInput.size - 1) sortedInput[index + 1] - rating else null
        }
        .groupBy { it }
        .map { Pair(it.value.size, it.key) }
}

// part 2

// thanks Paul Martin (Hives)!

fun getPaths(input: List<Int>): Long {
    val deviceRating = input.maxOrNull()!! + 3
    val sortedInput = input.plus(deviceRating).sorted()

    val paths = mutableMapOf(0 to 1L)

    sortedInput.forEach {
        val pathsToHere =
                    (paths[it - 1] ?: 0) +
                    (paths[it - 2] ?: 0) +
                    (paths[it - 3] ?: 0)

        paths[it] = pathsToHere
    }
    return paths[deviceRating] ?: 0
}
