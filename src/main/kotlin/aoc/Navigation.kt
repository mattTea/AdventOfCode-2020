package aoc

import kotlin.math.absoluteValue

typealias ShipCoordinates = Pair<Int, Int>
typealias Bearing = Pair<Char, ShipCoordinates>

fun calculatePosition(
    instructions: List<String>,
    index: Int = 0,
    direction: Char = 'E',
    shipCoordinates: ShipCoordinates = Pair(0,0)
): Int {
    val newBearing = createNewBearing(direction, shipCoordinates, instructions[index])
    return if (index == instructions.lastIndex) {
        newBearing.second.first.absoluteValue + newBearing.second.second.absoluteValue
    } else {
        calculatePosition(instructions, index + 1, newBearing.first, newBearing.second)
    }
}

fun createNewBearing(
    direction: Char,
    shipCoordinates: ShipCoordinates,
    instruction: String
): Bearing {
    return when (instruction.first()) {
        'E','N','W','S','F' -> movePosition(direction, shipCoordinates, instruction)
        'L' -> Bearing(changeDirection(direction, instruction.drop(1).toInt(), 'L'), shipCoordinates)
        'R' -> Bearing(changeDirection(direction, instruction.drop(1).toInt(), 'R'), shipCoordinates)

        else -> Bearing(direction, shipCoordinates)
    }
}

fun changeDirection(bearing: Char, degrees: Int, direction: Char): Char {
    val compassPoints = listOf('N','E','S','W').asSequence().repeatForever()

    val bearingIndex = compassPoints.indexOf(bearing)
    return compassPoints.elementAt(bearingIndex + changeCompassDirection(direction, degrees))
}

fun movePosition(
    direction: Char,
    shipCoordinates: ShipCoordinates,
    instruction: String
): Bearing =
    when (instruction.first()) {
        'F' -> {
            when (direction) {
                'E' -> Bearing(direction, ShipCoordinates(shipCoordinates.first + instruction.drop(1).toInt(), shipCoordinates.second))
                'N' -> Bearing(direction, ShipCoordinates(shipCoordinates.first, shipCoordinates.second + instruction.drop(1).toInt()))
                'W' -> Bearing(direction, ShipCoordinates(shipCoordinates.first - instruction.drop(1).toInt(), shipCoordinates.second))
                else -> Bearing(direction, ShipCoordinates(shipCoordinates.first, shipCoordinates.second - instruction.drop(1).toInt()))
            }
        }
        'E' -> Bearing(direction, ShipCoordinates(shipCoordinates.first + instruction.drop(1).toInt(), shipCoordinates.second))
        'N' -> Bearing(direction, ShipCoordinates(shipCoordinates.first, shipCoordinates.second + instruction.drop(1).toInt()))
        'W' -> Bearing(direction, ShipCoordinates(shipCoordinates.first - instruction.drop(1).toInt(), shipCoordinates.second))
        else -> Bearing(direction, ShipCoordinates(shipCoordinates.first, shipCoordinates.second - instruction.drop(1).toInt()))
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

// part 2

typealias Waypoint = Pair<Int, Int>
typealias PositionAndWaypoint = Pair<ShipCoordinates, Waypoint>

fun newCalculatePosition(
    instructions: List<String>,
    positionAndWaypoint: PositionAndWaypoint = PositionAndWaypoint(ShipCoordinates(0,0), Waypoint(10,1)),
    index: Int = 0
): Int {
    val newPositionAndWaypoint = when (instructions[index].take(1)) {
        "N","E","S","W" -> PositionAndWaypoint(
            positionAndWaypoint.first,
            moveWaypoint(positionAndWaypoint.second, instructions[index])
        )
        "F" -> PositionAndWaypoint(
            moveForward(positionAndWaypoint, instructions[index]),
            positionAndWaypoint.second
        )
        "R","L" -> PositionAndWaypoint(
            positionAndWaypoint.first,
            rotateWaypoint(positionAndWaypoint.second, instructions[index])
        )
        else -> positionAndWaypoint
    }

    return if (index == instructions.lastIndex) {
        newPositionAndWaypoint.first.first.absoluteValue + newPositionAndWaypoint.first.second.absoluteValue
    } else {
        newCalculatePosition(
            instructions, newPositionAndWaypoint, index + 1
        )
    }
}

fun moveForward(positionAndWaypoint: PositionAndWaypoint, instruction: String): ShipCoordinates {
    val shipCoordinates = positionAndWaypoint.first
    val waypoint = positionAndWaypoint.second

    return ShipCoordinates(
        shipCoordinates.first + (waypoint.first * instruction.drop(1).toInt()),
        shipCoordinates.second + (waypoint.second * instruction.drop(1).toInt())
    )
}

fun moveWaypoint(startingWaypoint: Waypoint, instruction: String): Waypoint =
    when (instruction.take(1)) {
        "N" -> Waypoint(startingWaypoint.first, startingWaypoint.second + instruction.drop(1).toInt())
        "E" -> Waypoint(startingWaypoint.first + instruction.drop(1).toInt(), startingWaypoint.second)
        "S" -> Waypoint(startingWaypoint.first, startingWaypoint.second - instruction.drop(1).toInt())
        "W" -> Waypoint(startingWaypoint.first - instruction.drop(1).toInt(), startingWaypoint.second)
        else -> startingWaypoint
    }

fun rotateWaypoint(startingWaypoint: Waypoint, instruction: String): Waypoint =
    when (instruction) {
        "R90","L270" -> Waypoint(startingWaypoint.second, (-startingWaypoint.first))
        "R180","L180" -> Waypoint((-startingWaypoint.first), (-startingWaypoint.second))
        "R270","L90" -> Waypoint((-startingWaypoint.second), startingWaypoint.first)
        else -> startingWaypoint
    }
