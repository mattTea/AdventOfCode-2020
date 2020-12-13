package aoc

fun firstInvalidValue(input: List<Long>, preambleSize: Int = 25): Long {
    val inputWithoutPreamble = input.drop(preambleSize)

    val validatedValues = inputWithoutPreamble
        .mapIndexed { index, it ->
            val relatedInput = getNewInput((index + preambleSize), input, preambleSize)

            Pair(!isValid(it, relatedInput), it)
        }

    return validatedValues.first { it.first }.second
}

fun isValid(valueToCheck: Long, input: List<Long>): Boolean =
    input.flatMap { firstValue ->
        input
            .filterNot { it == firstValue }
            .map { secondValue -> valueToCheck == (firstValue + secondValue) }
    }.contains(true)

fun getNewInput(index: Int, input: List<Long>, preambleSize: Int): List<Long> =
    input.subList(index - (preambleSize), index)

// part 2

fun findContiguousValues(
    total: Long,
    input: List<Long>,
    index: Int = firstIndexOfValueLowerThanTotal(total, input, input.lastIndex),
    lengthOfSum: Int = 2
): List<Long> {
    val contiguousList = input.subList((index - lengthOfSum) + 1, index + 1)
    val sumTotal = contiguousList.reduce { total, next -> total + next }

    return when {
        sumTotal == total -> contiguousList

        sumTotal < total && index > (lengthOfSum -1) ->
            findContiguousValues(total, input, index, lengthOfSum + 1)

        sumTotal > total && index > 1 ->
            findContiguousValues(total, input, index - 1, 2)

        else -> emptyList()
    }
}

private fun firstIndexOfValueLowerThanTotal(value: Long, inputData: List<Long>, startingIndex: Int): Int =
    if (inputData[startingIndex] > value) {
        firstIndexOfValueLowerThanTotal(value, inputData, startingIndex - 1)
    } else {
        startingIndex
    }

fun findWeakness(input: List<Long>): Long =
    input.minOrNull()!! + input.maxOrNull()!!