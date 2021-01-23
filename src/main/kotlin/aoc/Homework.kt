package aoc


fun calculateExpressionInBraces(input: String): Int {
    val closingBraceIndex = input.withIndex().find { it.value == ')' }?.index
    val openingBraceIndex = input.substringBefore(')').withIndex().findLast { it.value == '(' }?.index

    val expressionInBraces = if (openingBraceIndex == null || closingBraceIndex == null) input
    else input.substring(openingBraceIndex + 1 until closingBraceIndex)

    return newOrder(expressionInBraces)
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

fun main() {
    val input = "1+(2*3)"
    val input2 = "(4 * (3 * 2 + 2) * (9 * 7 * 5 * 4 * 9) * (7 * 7 + 7 * 4 + 9)) + 6 * 4 + 8 + ((6 * 5) * 4 * (2 * 8 + 4 + 7 * 9 + 3) * 2 + 6) + 3"

}

/*
"(4 * (3 * 2 + 2) * (9 * 7 * 5 * 4 * 9) * (7 * 7 + 7 * 4 + 9))"
"o1 .o2. . . . .c2.o3. . . . . . . . .c3.o4. . . . . . . . .c4c1

find first ')' then work back and find last '(' before the first ')' split between then and run newOrder() on that section in the string

*/
