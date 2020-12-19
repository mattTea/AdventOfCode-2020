package aoc

import kotlin.math.absoluteValue

typealias Coordinates = Pair<Int, Int>
typealias Bearing = Pair<Char, Coordinates>

fun calculatePosition(
    instructions: List<String>,
    index: Int = 0,
    direction: Char = 'E',
    coordinates: Coordinates = Pair(0,0)
): Int {
    val newBearing = createNewBearing(direction, coordinates, instructions[index])
    return if (index == instructions.lastIndex) {
        newBearing.second.first.absoluteValue + newBearing.second.second.absoluteValue
    } else {
        calculatePosition(instructions, index + 1, newBearing.first, newBearing.second)
    }
}

fun createNewBearing(
    direction: Char,
    coordinates: Coordinates,
    instruction: String
): Bearing {
    return when (instruction.first()) {
        'E','N','W','S','F' -> movePosition(direction, coordinates, instruction)
        'L' -> Bearing(changeDirection(direction, instruction.drop(1).toInt(), 'L'), coordinates)
        'R' -> Bearing(changeDirection(direction, instruction.drop(1).toInt(), 'R'), coordinates)

        else -> Bearing(direction, coordinates)
    }
}

fun changeDirection(bearing: Char, degrees: Int, direction: Char): Char {
    val compassPoints = listOf('N','E','S','W').asSequence().repeatForever()

    val bearingIndex = compassPoints.indexOf(bearing)
    return compassPoints.elementAt(bearingIndex + changeCompassDirection(direction, degrees))
}

fun movePosition(
    direction: Char,
    coordinates: Coordinates,
    instruction: String
): Bearing =
    when (instruction.first()) {
        'F' -> {
            when (direction) {
                'E' -> Bearing(direction, Coordinates(coordinates.first + instruction.drop(1).toInt(), coordinates.second))
                'N' -> Bearing(direction, Coordinates(coordinates.first, coordinates.second + instruction.drop(1).toInt()))
                'W' -> Bearing(direction, Coordinates(coordinates.first - instruction.drop(1).toInt(), coordinates.second))
                else -> Bearing(direction, Coordinates(coordinates.first, coordinates.second - instruction.drop(1).toInt()))
            }
        }
        'E' -> Bearing(direction, Coordinates(coordinates.first + instruction.drop(1).toInt(), coordinates.second))
        'N' -> Bearing(direction, Coordinates(coordinates.first, coordinates.second + instruction.drop(1).toInt()))
        'W' -> Bearing(direction, Coordinates(coordinates.first - instruction.drop(1).toInt(), coordinates.second))
        else -> Bearing(direction, Coordinates(coordinates.first, coordinates.second - instruction.drop(1).toInt()))
    }

private fun changeCompassDirection(direction: Char, degrees: Int): Int =
    if (direction == 'R') {
        when (degrees) {
            90 -> 1
            180 -> 2
            else -> 3
        }
    } else {
        when (degrees) {
            90 -> 3
            180 -> 2
            else -> 1
        }
    }

private fun <T> Sequence<T>.repeatForever() =
    generateSequence(this) { it }.flatten()