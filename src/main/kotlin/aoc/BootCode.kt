package aoc

// part 1

fun runProgram(
    instructions: List<String>,
    index: Int = 0,
    indexesVisited: List<Int> = emptyList(),
    acc: Int = 0
): Output {
    return if (indexesVisited.contains(index)) {
        Output(acc, "loop")
    } else {
        val newIndexesVisited = createIndexes(indexesVisited, index)
        when (instructions[index].take(3)) {
            "nop" -> {
                if (index == instructions.size - 1) {
                    Output(acc, "terminated")
                } else {
                    runProgram(
                        instructions = instructions,
                        index = index + 1,
                        indexesVisited = newIndexesVisited,
                        acc = acc
                    )
                }
            }
            "jmp" -> {
                val jumpNumber = instructions[index].substringAfter(" ").toInt()
                if (index + jumpNumber > instructions.size - 1) {
                    Output(acc, "terminated")
                } else {
                    runProgram(
                        instructions = instructions,
                        index = index + jumpNumber,
                        indexesVisited = newIndexesVisited,
                        acc = acc
                    )
                }
            }
            "acc" -> {
                val newAcc = accumulate(acc, instructions[index].substringAfter(" ").toInt())
                if (index == instructions.size - 1) {
                    Output(newAcc, "terminated")
                } else {
                    runProgram(
                        instructions = instructions,
                        index = index + 1,
                        indexesVisited = newIndexesVisited,
                        acc = newAcc
                    )
                }
            }
            else -> throw UnknownError("Instruction not understood")
        }
    }
}

private fun createIndexes(startingList: List<Int> = emptyList(), additionalIndex: Int): List<Int> =
    startingList + additionalIndex

private fun accumulate(startingAcc: Int, accValue: Int): Int =
    startingAcc + accValue

// part 2

fun fixProgram(instructions: List<String>): Int {
    val errorIndex = instructions.mapIndexedNotNull { index, _ ->
        if (checkInstruction(instructions, index)) index else null
    }.single()

    return runProgram(createNewInstructions(errorIndex, instructions)).acc
}

fun checkInstruction(instructions: List<String>, index: Int = 0): Boolean {
    val instruction = instructions[index].take(3)
    return if (instruction == "nop" || instruction == "jmp") {
        runProgram(createNewInstructions(index, instructions)).exitCode == "terminated"
    } else {
        false
    }
}

fun createNewInstructions(index: Int, instructions: List<String>): List<String> =
    when (instructions[index].take(3)) {
        "nop" -> instructions.mapIndexed { instructionIndex, instruction ->
            if (instructionIndex == index) {
                "jmp${instruction.substringAfter("nop")}"
            } else {
                instruction
            }
        }

        "jmp" -> instructions.mapIndexed { instructionIndex, instruction ->
            if (instructionIndex == index) {
                "nop${instruction.substringAfter("jmp")}"
            } else {
                instruction
            }
        }

        else -> instructions
    }

data class Output(
    val acc: Int,
    val exitCode: String
)