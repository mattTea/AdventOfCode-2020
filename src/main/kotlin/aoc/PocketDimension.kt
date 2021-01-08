package aoc

val initialState = listOf(
    listOf(
        "##..#.#.",
        "###.#.##",
        "..###..#",
        ".#....##",
        ".#..####",
        "#####...",
        "#######.",
        "#.##.#.#"
    )
)

typealias CubeCoord = Triple<Int, Int, Int> // first = zPlane, second = row, third = column
typealias Neighbour = Pair<Char, CubeCoord>  // char is status (active = # | inactive = .)

//fun cycleState(input: List<List<String>>): Int {
//    val inputCoords = getInputCoords(input)
//
//    inputCoords.map { cubeCoord ->
//        val neighbours = getNeighboursWithState(cubeCoord, input, inputCoords)
//        val status = input[cubeCoord.first][cubeCoord.second][cubeCoord.third]
//        val numberOfActiveNeighbours = neighbours.map { it.first }.filter { it == '#' }.size
//        when {
//            status == '#' && numberOfActiveNeighbours == 2 || numberOfActiveNeighbours == 3 -> 0
//            else -> 1
//        }
//    }
//
//    return 0
//}

fun getNeighboursWithState(
    cube: CubeCoord,
    input: List<List<String>>,
    inputCoords: List<CubeCoord> = getInputCoords(input)
): List<Neighbour> {
    val zRange = cube.first - 1..cube.first + 1
    val rowRange = cube.second - 1..cube.second + 1
    val colRange = cube.third - 1..cube.third + 1

    val neighbourCoords = zRange.flatMap { zCoord ->
        rowRange.flatMap { rowCoord ->
            colRange.map { colCoord ->
                CubeCoord(zCoord, rowCoord, colCoord)
            }
        }
    }.filterNot { it == cube }

    return neighbourCoords.map {
        if (inputCoords.contains(it)) {
            Neighbour(input[it.first][it.second][it.third], it)
        } else Neighbour('.', it)
    }
}

private fun getInputCoords(input: List<List<String>>): List<CubeCoord> =
    input.flatMapIndexed { zIndex, zPlane ->
        zPlane.flatMapIndexed { rowIndex, row ->
            row.mapIndexed { colIndex, _ ->
                CubeCoord(zIndex, rowIndex, colIndex)
            }
        }
    }
