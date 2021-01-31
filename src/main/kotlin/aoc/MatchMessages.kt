package aoc

fun followRule(
    rules: RulesMap,
    rule: Rule = Rule(0, rules[0]!!)
): String {
    return when (rule.second) {
        "a" -> "a"
        "b" -> "b"
        else -> {
            val nextRuleNumber = rule.second.toInt()
            val nextRule = Rule(nextRuleNumber, rules[nextRuleNumber]!!)
            followRule(rules, nextRule)
        }
    }
}

fun convertRules(rules: List<String>): RulesMap =
    rules
        .map { it.replace("\"", "") }
        .map { it.substringBefore(":").toInt() to it.substringAfter(": ") }.toMap()

typealias RulesMap = Map<Int, String>
typealias Rule = Pair<Int, String>
