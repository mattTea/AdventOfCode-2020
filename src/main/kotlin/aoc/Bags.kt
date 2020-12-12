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

    val reducedRules = rules.filterNot { rule ->
        bagColours.map { rule.substringAfter("contain").contains(it) }.any { it }
    }

    return if (directlyContainingBags.isNullOrEmpty()) {
        count
    } else {
        bagsContaining(directlyContainingBags, reducedRules, directlyContainingBags.size + count)
    }
}

// part 2

fun bagsNeededIn(bagColour: String, rules: List<String>): Int =
    countBagsInTree(createTreeFrom(Pair(1, bagColour), rules))

fun countBagsInTree(tree: List<Bag>): Int =
    tree.map {
        if (it.children.isNotEmpty()) {
            it.number + (it.number * countBagsInTree(it.children))
        } else {
            it.number
        }
    }.reduce { total, next -> total + next }

fun createTreeFrom(startingBag: Pair<Int, String>, rules: List<String>): List<Bag> {
    val startingRule = rules.single {
        it.substringBefore(" bags contain") == startingBag.second
    }

    return numbersAndColoursFrom(startingRule).map {
        Bag(
            number = it.first,
            colour = it.second,
            children = if (hasChildren(it.second, rules)) {
                createTreeFrom(it, rules)
            } else {
                emptyList()
            }
        )
    }
}

fun numbersAndColoursFrom(rule: String): List<Pair<Int, String>> =
    rule.substringAfter("contain ")
        .split(", ")
        .map {
            if (it.take(1) == "n") {
                Pair(0, it.drop(3).substringAfter(" bag"))
            } else {
                Pair(it.take(1).toInt(), it.drop(2).substringBefore(" bag"))
            }
        }

private fun hasChildren(bagColour: String, rules: List<String>): Boolean =
    rules.single { it.substringBefore(" bags contain") == bagColour }
        .substringAfter("contain ")
        .take(2) != "no"

data class Bag(
    val number: Int,
    val colour: String,
    val children: List<Bag>
)