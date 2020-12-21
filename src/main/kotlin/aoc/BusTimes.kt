package aoc

fun busAndWait(input: Pair<Int, String>): Int {
    val estimate = input.first
    return input.second
        .split(",")
        .filterNot { it == "x" }
        .map { it.toInt() }
        .map { busId -> Pair(estimate % busId, busId) }
        .map { Pair(it.second, it.second - it.first) }
        .sortedBy { it.second }.first()
        .let { it.first * it.second }
}

