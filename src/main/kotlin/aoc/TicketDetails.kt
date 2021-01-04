package aoc

fun invalidValues(input: String): Int {
    val validation = input.substringBefore("\n\n").split("\n")
    val tickets = input.substringAfter("nearby tickets:\n").split("\n")

    val fieldsWithValidation = fieldsAndRanges(validation)

    val ticketsAndFieldValues = tickets.map { ticket ->
        ticket.split(",").map { it.toInt() }
    }

    return ticketsAndFieldValues.map {
        it.filter { fieldValue ->
            fieldsWithValidation.none {
                it.second.contains(fieldValue) || it.third.contains(fieldValue)
            }
        }
    }.flatten().reduce { total, next -> total + next }
}

private fun fieldsAndRanges(validation: List<String>): List<FieldAndRanges> =
    validation.map {
        val field = it.substringBefore(":")
        val firstRangeAsString = it.substringAfter(": ").substringBefore(" or")
        val firstIntRange = IntRange(
            firstRangeAsString.substringBefore("-").toInt(),
            firstRangeAsString.substringAfter("-").toInt()
        )
        val secondRangeAsString = it.substringAfter("or ")
        val secondIntRange = IntRange(
            secondRangeAsString.substringBefore("-").toInt(),
            secondRangeAsString.substringAfter("-").toInt()
        )

        FieldAndRanges(field, firstIntRange, secondIntRange)
    }

typealias FieldAndRanges = Triple<String, IntRange, IntRange>


/*
maybe in part 2?

1. find ticketFieldValues that are only valid for one fieldWithValidation
2. Remove those ticketValues and those fieldsWithValidation
3. Then keep looping with shorter lists so that others are only valid for one field (and remove those)
 */
