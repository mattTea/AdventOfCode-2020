package aoc

val initialState = listOf(
    "##..#.#.",
    "###.#.##",
    "..###..#",
    ".#....##",
    ".#..####",
    "#####...",
    "#######.",
    "#.##.#.#"
)

typealias CubeCoord = Triple<Int, Int, Int> // first = row, second = column, third = z-plane
typealias Neighbour = Pair<Char, CubeCoord>  // char is status (active = # | inactive = .)

fun getNeighboursWithState(cube: CubeCoord, input: List<String>): List<Neighbour> {
    val rowRange = cube.first - 1..cube.first + 1
    val colRange = cube.second - 1..cube.second + 1
    val zRange = cube.third - 1..cube.third + 1

    val neighbourCoords = rowRange.flatMap { rowCoord ->
        colRange.flatMap { colCoord ->
            zRange.map { zCoord ->
                CubeCoord(rowCoord, colCoord, zCoord)
            }
        }
    }.filterNot { it == cube }

    val inputCoords = getInputCoords(input)

    return neighbourCoords.map {
        if (inputCoords.contains(it)) {
            Neighbour(input[it.first][it.second], it)
        } else Neighbour('.', it)
    }
}

private fun getInputCoords(input: List<String>): List<CubeCoord> =
    input.flatMapIndexed { rowIndex, row ->
        row.mapIndexed { colIndex, _ ->
            CubeCoord(rowIndex, colIndex, 0)
        }
    }
