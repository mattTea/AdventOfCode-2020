package aoc

fun treesEncountered(input: List<String>): Int =
    encounters(makeMapCorrectWidth(input))
        .filter { it.single() == '#' }
        .size

fun makeMapCorrectWidth(treeMap: List<String>): List<String> {
    val width = treeMap[0].length - 1
    val length = treeMap.size - 1
//    val repeats = 3 - (width / length)
    val repeats = (length * 3) / width

    return treeMap.map {
        it.repeat(if (repeats <= 0) 1 else repeats)
    }
}

fun encounters(treeMap: List<String>): List<String> =
    treeMap.mapIndexed { rowIndex, row ->
       row.filterIndexed { columnIndex, _ ->
           columnIndex == rowIndex * 3
       }
    }.drop(1)


