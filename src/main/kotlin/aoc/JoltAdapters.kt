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