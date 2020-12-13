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
