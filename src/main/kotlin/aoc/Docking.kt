package aoc

fun storeDockingInput(
    dockingInput: List<String>
): Long {
    val memoryLength = dockingInput
        .filter { it.take(3) == "mem" }
        .map { it.drop(4).substringBefore("]").toInt() }
        .maxOrNull()!! + 1

    var memory: MutableList<Long> = MutableList(memoryLength) { 0 }
    var bitMask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"

    dockingInput.map {
        if (it.take(3) == "mas") {
            bitMask = it.substringAfter("= ")
        } else {
            val binary = it.substringAfter("] = ").toLong().toBinary()
            val maskedBinary = applyBitMask(binary, bitMask)
            val maskedDecimal = maskedBinary.toDecimal()
            memory.set(it.drop(4).substringBefore("]").toInt(), maskedDecimal)
        }
    }

    return memory.reduce { total, next -> total + next }
}

fun applyBitMask(input: String, bitMask: String): String =
    bitMask.mapIndexed { index, bit ->
        when (bit) {
            '1' -> '1'
            '0' -> '0'
            else -> input[index]
        }
    }.joinToString("")

fun String.toDecimal(): Long =
    this.toLong(2)

fun Long.toBinary(): String =
    this.toString(2).padStart(36, '0')

fun main() {
    println(
        "000100011010101100101001010011110000".toDecimal()
    )
}