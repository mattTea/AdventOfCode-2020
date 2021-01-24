package aoc

fun runAllHomeworkLines(input: List<String>): Long =
    input
        .map { calculateExpressionWithBraces(it) }
        .reduce { total, next -> total + next }

fun calculateExpressionWithBraces(input: String): Long {
    val trimmedInput = input.filterNot { it == ' ' }
    val closingBraceIndex = trimmedInput.withIndex().find { it.value == ')' }?.index
    val openingBraceIndex = trimmedInput.substringBefore(')').withIndex().findLast { it.value == '(' }?.index

    return if (openingBraceIndex == null || closingBraceIndex == null) {
        newOrder(trimmedInput)
    } else {
        val expressionInBraces = trimmedInput.substring(openingBraceIndex + 1 until closingBraceIndex)
        val newOrderValue = newOrder(expressionInBraces)
        val newInput = trimmedInput.replaceRange(openingBraceIndex, closingBraceIndex + 1, newOrderValue.toString())
        calculateExpressionWithBraces(newInput)
    }
}

fun newOrder(homeworkLine: String): Long {
    val regex = Regex("((?<=[+*])|(?=[+*]))")
    val splitLine = homeworkLine
        .filterNot { it == ' ' }
        .split(regex)
        .map { if (Regex("[0-9]*").matches(it)) it.toLong() else it }
        .toMutableList() // listOf(1,"+",2,"*",3)

    var sumBuilder = 0L

    while (splitLine.isNotEmpty()) {
        val element = splitLine.first()

        if (element is Long) {
            sumBuilder += element
            splitLine.remove(element)
        } else {
            when (element) {
                "+" -> {
                    sumBuilder += splitLine[1] as Long
                    splitLine.removeAt(0)
                    splitLine.removeAt(0)
                }
                "*" -> {
                    sumBuilder *= splitLine[1] as Long
                    splitLine.removeAt(0)
                    splitLine.removeAt(0)
                }
            }
        }
    }

    return sumBuilder
}

// Part 2

fun runAllHomeworkLinesForPart2(input: List<String>): Long =
    input
        .map { calculateExpressionWithBracesForPart2(it) }
        .reduce { total, next -> total + next }

// this function is almost identical to part 1, just calling opposite Order below instead of newOrder above!
fun calculateExpressionWithBracesForPart2(input: String): Long {
    val trimmedInput = input.filterNot { it == ' ' }
    val closingBraceIndex = trimmedInput.withIndex().find { it.value == ')' }?.index
    val openingBraceIndex = trimmedInput.substringBefore(')').withIndex().findLast { it.value == '(' }?.index

    return if (openingBraceIndex == null || closingBraceIndex == null) {
        oppositeOrder(trimmedInput)
    } else {
        val expressionInBraces = trimmedInput.substring(openingBraceIndex + 1 until closingBraceIndex)
        val newOrderValue = oppositeOrder(expressionInBraces)
        val newInput = trimmedInput.replaceRange(openingBraceIndex, closingBraceIndex + 1, newOrderValue.toString())
        calculateExpressionWithBracesForPart2(newInput)
    }
}

fun oppositeOrder(input: String): Long {
    val regex = Regex("((?<=[+*])|(?=[+*]))")
    val splitLine = input
        .filterNot { it == ' ' }
        .split(regex)
        .map { if (Regex("[0-9]*").matches(it)) it.toLong() else it }
        .toMutableList()


    val firstPlusSignIndex = splitLine.withIndex().find { it.value == "+" }?.index

    return if (firstPlusSignIndex == null) {
        newOrder(splitLine.joinToString(""))
    } else {
        val firstPlusOperand = splitLine[firstPlusSignIndex - 1] as Long
        val secondPlusOperand = splitLine[firstPlusSignIndex + 1] as Long
        val sum = firstPlusOperand + secondPlusOperand
        splitLine.removeAt(firstPlusSignIndex - 1)
        splitLine.removeAt(firstPlusSignIndex - 1)
        splitLine[firstPlusSignIndex - 1] = sum

        oppositeOrder(splitLine.joinToString(""))
    }
}