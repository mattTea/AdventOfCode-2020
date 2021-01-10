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
typealias Cube = Pair<Char, CubeCoord>  // char is status (active = # | inactive = .)

fun cycleState(input: List<List<String>>): Int {
    val allInputCubes = getInputCubes(input)
    val allNeighbourCubes = allInputCubes.flatMap {
        getNeighboursWithState(it, input)
    }.distinct()

    return allNeighbourCubes.map {
        manageCubeState(it, input)
    }.filter { it.first == '#' }.size
}

fun manageCubeState(cube: Cube, input: List<List<String>>): Cube {
    val neighbours = getNeighboursWithState(cube, input)
    val status = cube.first
    val activeNeighbours = neighbours.map { it.first }.filter { it == '#' }.size

    return when {
        status == '#' && activeNeighbours == 2 || activeNeighbours == 3 -> Cube('#', cube.second)
        status == '.' && activeNeighbours == 3 -> Cube('#', cube.second)
        else -> Cube('.', cube.second)
    }
}

fun getNeighboursWithState(
    cube: Cube,
    input: List<List<String>>,
    inputCubes: List<Cube> = getInputCubes(input)
): List<Cube> {
    val zRange = cube.second.first - 1..cube.second.first + 1
    val rowRange = cube.second.second - 1..cube.second.second + 1
    val colRange = cube.second.third - 1..cube.second.third + 1

    val neighbourCoords = zRange.flatMap { zCoord ->
        rowRange.flatMap { rowCoord ->
            colRange.map { colCoord ->
                CubeCoord(zCoord, rowCoord, colCoord)
            }
        }
    }.filterNot { it == cube.second }

    return neighbourCoords.map { cubeCoord ->
        if (inputCubes.map { it.second }.contains(cubeCoord)) {
            inputCubes.single { it.second == cubeCoord }
        } else Cube('.', cubeCoord)
    }
}

private fun getInputCubes(input: List<List<String>>): List<Cube> =
    input.flatMapIndexed { zIndex, zPlane ->
        zPlane.flatMapIndexed { rowIndex, row ->
            row.mapIndexed { colIndex, _ ->
                Cube(input[zIndex][rowIndex][colIndex], CubeCoord(zIndex, rowIndex, colIndex))
            }
        }
    }
