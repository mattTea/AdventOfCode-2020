package aoc

fun errorRate(input: String): Int {
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

// Part 2

fun multiplyDepartures(input: String, start: String): Long {
    val fieldOrder = fieldOrder(input)
    val departureIndexes = fieldOrder.mapIndexedNotNull { index, field ->
        if (field.contains(start)) index else null
    }

    val ticketValues = input
        .substringAfter("your ticket:\n")
        .substringBefore("\n\nnearby tickets:")
        .split(",")
        .map { it.toLong() }
        .filterIndexed { index, _ -> departureIndexes.contains(index) }

    return ticketValues.reduce { total, next -> total * next }
}

fun fieldOrder(input: String): List<String> {
    val validNearbyTickets = validTickets(input)
    val validations = input.substringBefore("\n\n").split("\n")
    val fieldsWithValidation = fieldsAndRanges(validations)
    val ticketsAndFieldValues = validNearbyTickets.map { ticket ->
        ticket.split(",").map { it.toInt() }
    }

    val fields = mutableListOf<Pair<Int, String>>()
    for (index in ticketsAndFieldValues[0].indices) {
        val fieldValuesAtIndex = ticketsAndFieldValues.map { it[index] }

        val validFields = fieldsWithValidation.filter {
            Pair(it.second, it.third).containsAll(fieldValuesAtIndex)
        }

        fields.addAll(validFields.map { Pair(index, it.first) })
    }

    val uniqueFields = findUniqueFields(fields)

    return uniqueFields.sortedBy { it.first }.map { it.second }
}

fun validTickets(input: String): List<String> {
    val validation = input.substringBefore("\n\n").split("\n")
    val tickets = input.substringAfter("nearby tickets:\n").split("\n")

    val fieldsWithValidation = fieldsAndRanges(validation)

    val ticketsAndFieldValues = tickets.map { ticket ->
        ticket.split(",").map { it.toInt() }
    }

    return ticketsAndFieldValues.filter { values ->
        values.all { value ->
            fieldsWithValidation.any {
                it.second.contains(value) || it.third.contains(value)
            }
        }
    }.map { it.joinToString(",") }
}

// helpers

private fun Pair<IntRange, IntRange>.containsAll(values: List<Int>): Boolean =
    values.all {
        this.first.contains(it) || this.second.contains(it)
    }

private fun findUniqueFields(fields: List<Pair<Int, String>>): List<Pair<Int, String>> {
    val startingFields = fields.toMutableList()
    val uniqueFields = mutableListOf<Pair<Int, String>>()

    while (uniqueFields.size < fields.distinctBy { it.second }.size) {
        val fieldCount = startingFields.groupingBy { it.second }.eachCount()
        val singleOccurrenceFields = fieldCount.filter { it.value == 1 }.keys.toList()

        singleOccurrenceFields.map { singleOccurrenceFieldName ->
            val field = startingFields.single { it.second == singleOccurrenceFieldName }
            uniqueFields.add(field)
            startingFields.remove(field)
            startingFields.removeAll(fields.filter { it.first == field.first })
        }
    }

    return uniqueFields
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
