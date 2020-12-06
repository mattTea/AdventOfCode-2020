package aoc

import kotlin.math.ceil

fun treesEncountered(input: List<String>, gradient: Double): Int =
    encounters(makeMapCorrectWidth(input, gradient), gradient)
        .filterNot { it.isEmpty() }
        .filter { it.single() == '#' }
        .size

fun makeMapCorrectWidth(treeMap: List<String>, gradient: Double): List<String> {
    val width = treeMap[0].length - 1
    val length = treeMap.size - 1
    val repeats = (length.toDouble() * gradient) / width.toDouble()

    return treeMap.map {
        it.repeat(if (repeats <= 1) 1 else ceil(repeats).toInt())
    }
}

fun encounters(treeMap: List<String>, gradient: Double): List<String> =
    treeMap.mapIndexed { rowIndex, row ->
       row.filterIndexed { columnIndex, _ ->
           columnIndex.toDouble() == rowIndex * gradient
       }
    }.drop(1)
