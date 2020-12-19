package aoc

typealias Coordinate = Pair<Int, Int>
typealias SeatDetails = Pair<Char, Coordinate>

fun countOccupiedSeats(seatingPlan: List<String>): Int =
    rotateSeating(seatingPlan)
        .joinToString("")
        .filter { it == '#' }.length

fun rotateSeating(seatingPlan: List<String>): List<String> {
    val newStateOfSeats = musicalChairs(seatingPlan)

    return if (seatingPlan == newStateOfSeats) {
        newStateOfSeats
    } else {
        rotateSeating(newStateOfSeats)
    }
}

fun musicalChairs(seatingPlan: List<String>): List<String> =
    seatingPlan.mapIndexed { rowIndex, row ->
        row.mapIndexed { columnIndex, seat ->
            checkSeat(SeatDetails(seat, Coordinate(rowIndex, columnIndex)), seatingPlan)
        }.joinToString("")
    }

fun checkSeat(
    // comment in/out the below for part 1 or part 2 variants
    seatDetails: SeatDetails,
    seatingPlan: List<String>
): Char {
//    val adjacentSeats = adjacentSeats(seatDetails.second, seatingPlan)
    val part2AdjacentSeats = part2AdjacentSeats(seatDetails.second, seatingPlan)

    return when (seatDetails.first) {
//        'L' -> { if (adjacentSeats.map { it.first }.contains('#')) 'L' else '#' }
        'L' -> { if (part2AdjacentSeats.map { it.first }.contains('#')) 'L' else '#' }
//        '#' -> { if (adjacentSeats.filter { it.first == '#' }.size >= 4) 'L' else '#' }
        '#' -> { if (part2AdjacentSeats.filter { it.first == '#' }.size >= 5) 'L' else '#' }
        else -> seatDetails.first
    }
}

fun adjacentSeats(
    seatCoordinates: Coordinate,
    seatingPlan: List<String>
): List<SeatDetails> {
    val adjacentSeats = mutableListOf<SeatDetails>()

    seatingPlan.mapIndexed { rowIndex, row ->
        row.mapIndexed { columnIndex, seat ->
            val seatRow = seatCoordinates.first
            val seatCol = seatCoordinates.second

            when {
                rowIndex in seatRow - 1..seatRow + 1 && columnIndex in seatCol - 1..seatCol + 1 ->
                    adjacentSeats.add(SeatDetails(seat, Coordinate(rowIndex, columnIndex)))
                else -> null
            }
        }
    }

    return adjacentSeats.filterNot { it.second == seatCoordinates }
}

// part 2

fun part2AdjacentSeats(
    seatCoordinates: Coordinate,
    seatingPlan: List<String>
): List<SeatDetails> {
    val adjacentSeats = mutableListOf<SeatDetails>()

    seatingPlan.mapIndexed { rowIndex, row ->
        row.mapIndexed { columnIndex, seat ->
            val seatRow = seatCoordinates.first
            val seatCol = seatCoordinates.second

            when {
                rowIndex in seatRow - 1..seatRow + 1 && columnIndex in seatCol - 1..seatCol + 1 ->
                    adjacentSeats.add(SeatDetails(seat, Coordinate(rowIndex, columnIndex)))
                else -> null
            }
        }
    }

    return adjacentSeats
        .filterNot { it.second == seatCoordinates }
        .mapNotNull {
            if (it.first == '.') {
                val nearestSeatCoordinates = getNearestSeat(seatCoordinates, it.second, seatingPlan)

                if (nearestSeatCoordinates == null) {
                    null
                } else {
                    SeatDetails(
                        seatingPlan[nearestSeatCoordinates.first][nearestSeatCoordinates.second],
                        nearestSeatCoordinates
                    )
                }
            } else {
                it
            }
        }
}

fun getNearestSeat(
    seatCoordinates: Coordinate,
    emptySpaceCoordinates: Coordinate,
    seatingPlan: List<String>
): Coordinate? {
    val move = emptySpaceCoordinates.minus(seatCoordinates)

    val newCoordinates = emptySpaceCoordinates.plus(move)

    if (newCoordinates.first < 0 || newCoordinates.second < 0) return null
    if (newCoordinates.first > seatingPlan.lastIndex || newCoordinates.second > seatingPlan[0].lastIndex) return null
    return if (seatingPlan[newCoordinates.first][newCoordinates.second] == '.') {
        getNearestSeat(emptySpaceCoordinates, newCoordinates, seatingPlan)
    } else {
        newCoordinates
    }
}

private fun Pair<Int, Int>.minus(coordinates: Pair<Int, Int>): Pair<Int, Int> =
    Pair(this.first - coordinates.first, this.second - coordinates.second)

private fun Pair<Int, Int>.plus(coordinates: Pair<Int, Int>): Pair<Int, Int> =
    Pair(this.first + coordinates.first, this.second + coordinates.second)