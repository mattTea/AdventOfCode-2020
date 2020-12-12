package aoc

class HandheldDevice {
    var acc = 0
    var indexesVisited = mutableListOf<Int>()

    fun runProgram(instructions: List<String>, index: Int = 0): Int {
        return if (indexesVisited.contains(index)) {
            acc
        } else {
            when (instructions[index].take(3)) {
                "nop" -> {
                    indexesVisited.add(index)
                    runProgram(instructions, index + 1)
                }
                "jmp" -> {
                    indexesVisited.add(index)
                    runProgram(instructions, index + instructions[index].substringAfter(" ").toInt())
                }
                "acc" -> {
                    indexesVisited.add(index)
                    acc += instructions[index].substringAfter(" ").toInt()
                    runProgram(instructions, index + 1)
                }
                else -> throw UnknownError("Instruction not understood")
            }
        }
    }
}
