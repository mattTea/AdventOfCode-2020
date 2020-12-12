package aoc

fun runProgram(
    instructions: List<String>,
    index: Int = 0,
    indexesVisited: List<Int> = emptyList(),
    acc: Int = 0
): Int {
    return if (indexesVisited.contains(index)) {
        acc
    } else {
        val newIndexesVisited = createIndexes(indexesVisited, index)
        when (instructions[index].take(3)) {
            "nop" -> {
                runProgram(
                    instructions = instructions,
                    index = index + 1,
                    indexesVisited = newIndexesVisited,
                    acc = acc
                )
            }
            "jmp" -> {
                runProgram(
                    instructions = instructions,
                    index = index + instructions[index].substringAfter(" ").toInt(),
                    indexesVisited = newIndexesVisited,
                    acc = acc
                )
            }
            "acc" -> {
                val newAcc = accumulate(acc, instructions[index].substringAfter(" ").toInt())
                runProgram(
                    instructions = instructions,
                    index = index + 1,
                    indexesVisited = newIndexesVisited,
                    acc = newAcc
                )
            }
            else -> throw UnknownError("Instruction not understood")
        }
    }
}

private fun createIndexes(startingList: List<Int> = emptyList(), additionalIndex: Int): List<Int> =
    startingList + additionalIndex

private fun accumulate(startingAcc: Int, accValue: Int): Int =
    startingAcc + accValue
