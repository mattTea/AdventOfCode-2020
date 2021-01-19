package aoc

fun newOrder(homeworkLine: String): Int {
    val regex = Regex("((?<=[+*])|(?=[+*]))")
    val splitLine = homeworkLine
        .filterNot { it == ' ' }
        .split(regex)
        .map {
            if (Regex("[0-9]*").matches(it)) it.toInt() else {
                it
            }
        }.toMutableList()

    var sumBuilder = 0

    while (splitLine.isNotEmpty()) {
        val element = splitLine.first()

        if (element is Int) {
            sumBuilder += element
            splitLine.remove(element)
            println("sumBuilder: $sumBuilder | inputLength: ${splitLine.size}")
        } else {
            when (element) {
                "+" -> {
                    sumBuilder += splitLine[1] as Int
                    splitLine.removeAt(0)
                    splitLine.removeAt(0)
                    println("sumBuilder: $sumBuilder | inputLength: ${splitLine.size}")
                }
                "*" -> {
                    sumBuilder *= splitLine[1] as Int
                    splitLine.removeAt(0)
                    splitLine.removeAt(0)
                    println("sumBuilder: $sumBuilder | inputLength: ${splitLine.size}")
                }
            }
        }
    }

    return sumBuilder
}







