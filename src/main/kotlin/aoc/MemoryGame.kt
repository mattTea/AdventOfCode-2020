package aoc

fun determineAge(
    startingNumbers: List<Int>,
    spokenNumberToReturn: Int
): Int =
    if (startingNumbers.size >= spokenNumberToReturn) {
        startingNumbers[spokenNumberToReturn - 1]
    } else {
        val lastNumberSpoken = startingNumbers.last()
        if (!startingNumbers.dropLast(1).contains(lastNumberSpoken)) {
            determineAge((startingNumbers + 0), spokenNumberToReturn)
        } else {
            val lastTimeSpoken = startingNumbers.dropLast(1).withIndex().findLast { it.value == lastNumberSpoken }
            val lastIndex = lastTimeSpoken!!.index
            val age = startingNumbers.lastIndex - lastIndex
            determineAge((startingNumbers + age), spokenNumberToReturn)
        }
    }