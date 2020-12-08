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

    return if (directlyContainingBags.isNullOrEmpty()) {
        count
    } else {
        bagsContaining(directlyContainingBags, reducedRules, directlyContainingBags.size + count)
    }
}