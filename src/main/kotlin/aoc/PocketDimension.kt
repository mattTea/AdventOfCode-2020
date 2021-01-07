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

typealias CubeCoord = Triple<Int, Int, Int>
// first = row, second = column, third = z-plane

fun getNeighbours(cube: CubeCoord): List<CubeCoord> {
    val rowRange = cube.first - 1..cube.first + 1
    val colRange = cube.second - 1..cube.second + 1
    val zRange = cube.third - 1..cube.third + 1

    return rowRange.flatMap { rowCoord ->
        colRange.flatMap { colCoord ->
            zRange.map { zCoord ->
                CubeCoord(rowCoord, colCoord, zCoord)
            }
        }
    }.filterNot { it == cube }
}
