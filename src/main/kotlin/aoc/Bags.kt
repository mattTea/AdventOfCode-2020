package aoc

fun extractContainingBagColours(bagColours: List<String>, rule: String): String? =
    if (bagColours.map { rule.substringAfter("contain").contains(it) }.any { it }) {
        rule.substringBefore(" bag")
    } else {
        null
    }

fun bagsContaining(bagColours: List<String>, rules: List<String>, count: Int = 0): Int {
    val directlyContainingBags = rules
        .mapNotNull { extractContainingBagColours(bagColours, it) }
        .distinct()

    val reducedRules = rules.filterNot { rule -> // if any of the bagColours are contained in subStringAfter("contain")
        bagColours.map { rule.substringAfter("contain").contains(it) }.any { it }
    }

    return if (directlyContainingBags.isNullOrEmpty()) count
    else bagsContaining(directlyContainingBags, reducedRules, directlyContainingBags.size + count)
}

// part 2

fun bagsInside(outerBagColours: List<String>, rules: List<String>, count: Int = 0): Int {
    val directRules = outerBagColours.flatMap { colour ->
        rules.filter { rule ->
            rule.substringBefore(" bags contain") == colour
        }
    }

    return if (directRules.isEmpty()) {
        count
    } else {
        val containedNumbersOfEachColourBag = directRules.flatMap { directRule ->
            val containedBags =
                directRule.substringAfter("contain ").split(", ") // listOf("1 dark olive bag", "2 vibrant plum bags")
            containedBags.map {
                Pair(it.take(1).toInt(), it.drop(2).substringBefore(" bag")) // Pair(1, "dark olive")
            }
        }

        val thisCount = containedNumbersOfEachColourBag
            .map { it.first }
            .reduce { total, next -> total + next }

        val listOfNextColours = containedNumbersOfEachColourBag.map { it.second }

        return if (thisCount == 0) count
        else bagsInside(listOfNextColours, rules, thisCount + count)
    }
}
