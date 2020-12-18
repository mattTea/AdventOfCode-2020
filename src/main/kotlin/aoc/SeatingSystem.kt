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

fun musicalChairs(seatingPlan: List<String>): List<String> {
    val separateSeats = seatingPlan.map {
        it.split("").filter { it.isNotEmpty() }
    }

    return separateSeats.mapIndexed { rowIndex, row ->
        row.mapIndexed { columnIndex, seat ->
            checkSeat(SeatDetails(seat.single(), Coordinate(rowIndex, columnIndex)), seatingPlan)
        }.joinToString("")
    }
}

fun checkSeat(
    seatDetails: SeatDetails,
    seatingPlan: List<String>
): Char {
    val adjacentSeats = adjacentSeats(seatDetails.second, seatingPlan)

    return when (seatDetails.first) {
        'L' -> { if (adjacentSeats.map { it.first }.contains('#')) 'L' else '#' }
        '#' -> { if (adjacentSeats.filter { it.first == '#' }.size >= 4) 'L' else '#' }
        else -> seatDetails.first
    }
}

fun adjacentSeats(
    seatCoordinates: Coordinate,
    seatingPlan: List<String>
): List<SeatDetails> {
    val separateSeats = seatingPlan.map {
        it.split("").filter { it.isNotEmpty() }
    }

    val adjacentSeats = mutableListOf<SeatDetails>()

    separateSeats.mapIndexed { rowIndex, row ->
        row.mapIndexed { columnIndex, seat ->
            val seatRow = seatCoordinates.first
            val seatCol = seatCoordinates.second

            when {
                rowIndex in seatRow-1..seatRow+1 && columnIndex in seatCol-1..seatCol+1 ->
                    adjacentSeats.add(Pair(seat.single(), Coordinate(rowIndex, columnIndex)))
                else -> null
            }
        }
    }

    return adjacentSeats.filterNot { it.second == seatCoordinates }
}