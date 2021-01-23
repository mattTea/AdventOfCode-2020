package aoc


fun calculateExpressionWithBraces(input: String): Int {
    val trimmedInput = input.filterNot { it == ' ' }
    val closingBraceIndex = trimmedInput.withIndex().find { it.value == ')' }?.index
    val openingBraceIndex = trimmedInput.substringBefore(')').withIndex().findLast { it.value == '(' }?.index

    return if (openingBraceIndex == null || closingBraceIndex == null) {
        newOrder(trimmedInput)
    }
    else {
        val expressionInBraces = trimmedInput.substring(openingBraceIndex + 1 until closingBraceIndex)
        val newOrderValue = newOrder(expressionInBraces)
        val newInput = trimmedInput.replaceRange(openingBraceIndex, closingBraceIndex +1, newOrderValue.toString())
        calculateExpressionWithBraces(newInput)
    }
}

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
