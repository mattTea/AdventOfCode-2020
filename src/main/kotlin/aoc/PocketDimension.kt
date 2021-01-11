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
typealias Cube = Pair<Char, CubeCoord>  // char is status (active = '#' | inactive = '.')

fun cycleState(input: List<List<String>>, cycles: Int = 6): Int {
    var cycleInputCubes = getInputCubes(input)
    var allNeighbourCubes: List<Cube>

    for (cycle in 1..cycles) {
        allNeighbourCubes = cycleInputCubes.flatMap {
            getNeighboursWithState(it, input, cycleInputCubes) // input is only her for a default arg
        }.distinct()

        cycleInputCubes = allNeighbourCubes.map {
            manageCubeState(it, input, cycleInputCubes) // input is only here for a default arg
        }
    }

    return cycleInputCubes.filter { it.first == '#' }.size
}

fun manageCubeState(cube: Cube, input: List<List<String>>, inputCubes: List<Cube> = getInputCubes(input)): Cube {
    val neighbours = getNeighboursWithState(cube, input, inputCubes)
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

// Part 2

data class FourDCubeCoord(val wPlane: Int, val zPlane: Int, val row: Int, val column: Int)
typealias HyperCube = Pair<Char, FourDCubeCoord>

fun part2CycleState(input: List<List<String>>, cycles: Int = 6): Int {
    var cycleInputCubes = getInputHyperCubes(input)
    var allNeighbourCubes: List<HyperCube>

    for (cycle in 1..cycles) {
        allNeighbourCubes = cycleInputCubes.flatMap {
            part2GetNeighboursWithState(it, input, cycleInputCubes)
        }.distinct()

        cycleInputCubes = allNeighbourCubes.map {
            part2ManageCubeState(it, input, cycleInputCubes)
        }
    }

    return cycleInputCubes.filter { it.first == '#' }.size
}

fun part2ManageCubeState(hyperCube: HyperCube, input: List<List<String>>, inputCubes: List<HyperCube> = getInputHyperCubes(input)): HyperCube {
    val neighbours = part2GetNeighboursWithState(hyperCube, input, inputCubes)
    val status = hyperCube.first
    val activeNeighbours = neighbours.map { it.first }.filter { it == '#' }.size

    return when {
        status == '#' && activeNeighbours == 2 || activeNeighbours == 3 -> HyperCube('#', hyperCube.second)
        status == '.' && activeNeighbours == 3 -> HyperCube('#', hyperCube.second)
        else -> HyperCube('.', hyperCube.second)
    }
}

fun part2GetNeighboursWithState(
    hyperCube: HyperCube,
    input: List<List<String>>,
    inputCubes: List<HyperCube> = getInputHyperCubes(input)
): List<HyperCube> {
    val wRange = hyperCube.second.wPlane - 1..hyperCube.second.wPlane + 1
    val zRange = hyperCube.second.zPlane - 1..hyperCube.second.zPlane + 1
    val rowRange = hyperCube.second.row - 1..hyperCube.second.row + 1
    val colRange = hyperCube.second.column - 1..hyperCube.second.column + 1

    val neighbourCoords = wRange.flatMap { wCoord ->
        zRange.flatMap { zCoord ->
            rowRange.flatMap { rowCoord ->
                colRange.map { colCoord ->
                    FourDCubeCoord(wCoord, zCoord, rowCoord, colCoord)
                }
            }
        }
    }.filterNot { it == hyperCube.second }

    return neighbourCoords.map { cubeCoord ->
        if (inputCubes.map { it.second }.contains(cubeCoord)) {
            inputCubes.single { it.second == cubeCoord }
        } else HyperCube('.', cubeCoord)
    }
}

private fun getInputHyperCubes(input: List<List<String>>): List<HyperCube> =
    input.flatMapIndexed { zIndex, zPlane ->
        zPlane.flatMapIndexed { rowIndex, row ->
            row.mapIndexed { colIndex, _ ->
                HyperCube(input[zIndex][rowIndex][colIndex], FourDCubeCoord(0, zIndex, rowIndex, colIndex))
            }
        }
    }