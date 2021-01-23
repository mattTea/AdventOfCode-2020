package aoc

fun newOrder(homeworkLine: String): Int {
    val regex = Regex("((?<=[+*])|(?=[+*]))")
    val splitLine = homeworkLine
        .filterNot { it == ' ' }
        .split(regex)
        .map { if (Regex("[0-9]*").matches(it)) it.toInt() else it }
        .toMutableList() // listOf(1,"+",2,"*",3)

    var sumBuilder = 0

    while (splitLine.isNotEmpty()) {
        val element = splitLine.first()

        if (element is Int) {
            sumBuilder += element
            splitLine.remove(element)
        } else {
            when (element) {
                "+" -> {
                    sumBuilder += splitLine[1] as Int
                    splitLine.removeAll(listOf(splitLine[0],splitLine[1]))
                }
                "*" -> {
                    sumBuilder *= splitLine[1] as Int
                    splitLine.removeAll(listOf(splitLine[0],splitLine[1]))
                }
            }
        }
    }

    return sumBuilder
}
