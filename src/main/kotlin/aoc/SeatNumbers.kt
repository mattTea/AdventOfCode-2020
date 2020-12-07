package aoc

fun highestSeatId(seatInput: List<String>): Int =
    seatInput.map { calculateSeatId(it) }.maxOrNull()!!

fun calculateSeatId(seatCode: String): Int =
    (findRow(seatCode) * 8) + findColumn(seatCode)

fun findRow(seatCode: String): Int {
    val allRows = (0..127).toList()
    val rowCode = seatCode.take(7)

    return allRows
        .takeHalf(rowCode[0])
        .takeHalf(rowCode[1])
        .takeHalf(rowCode[2])
        .takeHalf(rowCode[3])
        .takeHalf(rowCode[4])
        .takeHalf(rowCode[5])
        .takeHalf(rowCode[6])
        .single()
}

fun findColumn(seatCode: String): Int {
    val allColumns = (0..7).toList()
    val columnCode = seatCode.takeLast(3)

    return allColumns
        .takeHalf(columnCode[0])
        .takeHalf(columnCode[1])
        .takeHalf(columnCode[2])
        .single()
}

fun <T> List<T>.takeHalf(half: Char): List<T> =
    if (half == 'F' || half == 'L') this.take(this.size / 2)
    else this.takeLast(this.size / 2)