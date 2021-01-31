package aoc

fun followRule(
    rules: RulesMap,
    rule: Rule = Rule(0, rules[0]!!)
): String {
    return when (rule.second) {
        "a" -> "a"
        "b" -> "b"
        else -> {
            val nextRuleNumbers = rule.second.split(" ").map { it.toInt() }
            nextRuleNumbers.joinToString("") {
                val nextRule = Rule(it, rules[it]!!)
                followRule(rules, nextRule)
            }
        }
    }
}

fun convertRules(rules: List<String>): RulesMap =
    rules
        .map { it.replace("\"", "") }
        .map { it.substringBefore(":").toInt() to it.substringAfter(": ") }.toMap()

typealias RulesMap = Map<Int, String>
typealias Rule = Pair<Int, String>
