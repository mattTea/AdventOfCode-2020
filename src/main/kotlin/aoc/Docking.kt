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


// Part 2

fun storeDockingInputInMultipleAddresses(
    dockingInput: List<String>
): Long {
    val memory = mutableMapOf<Long, Long>()
    var bitMask = "000000000000000000000000000000000000"

    dockingInput.map {
        if (it.take(4) == "mask") {
            bitMask = it.substringAfter("= ")
        } else {
            val dockingValue = it.substringAfter("] = ").toLong()
            val unmaskedAddress = it.drop(4).substringBefore("]").toLong()
            val memoryLocations = memoryBitMask(unmaskedAddress.toBinary(), bitMask)

            memoryLocations.map { location ->
                memory.put(location, dockingValue)
            }
        }
    }

    return memory.values.reduce { total, next -> total + next }
}

fun memoryBitMask(memoryAddress: String, memoryBitMask: String): List<Long> {
    val maskedAddress = memoryBitMask.mapIndexed { index, bit ->
        when (bit) {
            '1' -> '1'
            'X' -> 'X'
            else -> memoryAddress[index]
        }
    }.joinToString("")

    return explodeFloatingBits(maskedAddress).map { it.toDecimal() }
}

fun explodeFloatingBits(
    maskedAddress: String,
    newAddresses: List<String> = emptyList()
): List<String> =
    if (newAddresses.isNotEmpty() && newAddresses.none { it.contains('X') }) {
        newAddresses
    } else {
        if (newAddresses.isEmpty()) {
            val zero = maskedAddress.replaceFirst('X', '0')
            val one = maskedAddress.replaceFirst('X', '1')
            explodeFloatingBits(maskedAddress, listOf(zero, one))
        } else {
            val explodedAddresses = mutableListOf<String>()
            newAddresses.map {
                val zero = it.replaceFirst('X', '0')
                val one = it.replaceFirst('X', '1')
                explodedAddresses.addAll(listOf(zero, one))
            }
            explodeFloatingBits(maskedAddress, explodedAddresses)
        }
    }