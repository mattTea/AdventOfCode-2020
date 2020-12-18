package aoc

typealias Coordinate = Pair<Int, Int>

fun adjacentSeats(
    seatCoordinates: Coordinate,
    seatingPlan: List<String>
): List<Coordinate> {
    val separateSeats = seatingPlan.map {
        it.split("").filter { it.isNotEmpty() }
    }

    val adjacentSeats = mutableListOf<Coordinate>()

    separateSeats.mapIndexed { rowIndex, row ->
        row.mapIndexed { columnIndex, _ ->
            val seatRow = seatCoordinates.first
            val seatCol = seatCoordinates.second

            when {
                rowIndex in seatRow-1..seatRow+1 && columnIndex in seatCol-1..seatCol+1 ->
                    adjacentSeats.add(Coordinate(rowIndex, columnIndex))
                else -> null
            }
        }
    }

    return adjacentSeats.filterNot { it == seatCoordinates }
}