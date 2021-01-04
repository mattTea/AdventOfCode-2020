package aoc

fun determineAge(
    startingNumbers: List<Int>,
    spokenNumberToReturn: Int
): Int {
    val lastNumberSpoken = startingNumbers.last()

    return if (startingNumbers.size == spokenNumberToReturn) {
        lastNumberSpoken
    } else {
        val lastTimeSpoken = startingNumbers.dropLast(1).withIndex().findLast { it.value == lastNumberSpoken }
        if (lastTimeSpoken == null) {
            determineAge((startingNumbers + 0), spokenNumberToReturn)
        } else {
            val lastIndex = lastTimeSpoken.index
            val age = startingNumbers.lastIndex - lastIndex
            determineAge((startingNumbers + age), spokenNumberToReturn)
        }
    }
}

// Part 2

typealias Ages = Pair<Long, Long?>

fun lastSpoken(
    turns: Long,
    startingLastSpokenStore: MutableMap<Long, Ages>,
    startingTurn: Long
): Long {
//    var lastSpokenNumber = 6L // for example input
    var lastSpokenNumber = 1L // for full input
    for (turn in startingTurn..turns) {
        if (startingLastSpokenStore[lastSpokenNumber]!!.second == null) {
            lastSpokenNumber = 0
            startingLastSpokenStore[lastSpokenNumber] = Pair(turn, startingLastSpokenStore[lastSpokenNumber]!!.first)
        } else {
            lastSpokenNumber = startingLastSpokenStore[lastSpokenNumber]!!.first - startingLastSpokenStore[lastSpokenNumber]!!.second!!
            startingLastSpokenStore[lastSpokenNumber] = Pair(turn, startingLastSpokenStore[lastSpokenNumber]?.first)
        }
    }

    return lastSpokenNumber
}

val exampleLastSpokenStore = mutableMapOf(
    // lastSpoken to (current age, previous age)
    0L to Ages(1L, null),
    3L to Ages(2L, null),
    6L to Ages(3L, null)
)

val fullLastSpokenStore = mutableMapOf(
    13L to Ages(1L, null),
    16L to Ages(2L, null),
    0L to Ages(3L, null),
    12L to Ages(4L, null),
    15L to Ages(5L, null),
    1L to Ages(6L, null)
)