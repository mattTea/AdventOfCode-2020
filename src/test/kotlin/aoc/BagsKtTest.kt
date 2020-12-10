package aoc

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class BagsKtTest {
    @Test
    fun `should create tree structure from rule data`() {
        val rules = listOf(
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags",
            "faded blue bags contain no other bags",
            "dotted black bags contain 1 faded blue bags",
            "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags",
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags"
        )

        val result = createTreeFrom(Pair(1, "shiny gold"), rules)

        val expected = exampleRuleTree48Bags

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `should count 8 bags in tree`() {
        val tree = exampleRuleTree8Bags

        val result = countBagsInTree(tree)

        assertThat(result).isEqualTo(8)
    }

    @Test
    fun `should count 12 bags in tree`() {
        val tree = exampleRuleTree12Bags

        val result = countBagsInTree(tree)

        assertThat(result).isEqualTo(12)
    }

    @Test
    fun `should count 48 bags in tree`() {
        val tree = exampleRuleTree48Bags

        val result = countBagsInTree(tree)

        assertThat(result).isEqualTo(48)
    }

    @Test
    fun `should generate tree and return 3 bags count`() {
        val rules = listOf(
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags",
            "dark olive bags contain no other bags",
            "vibrant plum bags contain no other bags"
        )

        val result = bagsNeededIn("shiny gold", rules)

        assertThat(result).isEqualTo(3)
    }

    @Test
    fun `should generate tree and return 48 bags count`() {
        val rules = listOf(
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags",
            "faded blue bags contain no other bags",
            "dotted black bags contain 1 faded blue bags",
            "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags",
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags"
        )

        val result = bagsNeededIn("shiny gold", rules)

        assertThat(result).isEqualTo(48)
    }

    @Test
    fun `should generate tree and return bags count for full input list`() {
        val rules = bagRules

        val result = bagsNeededIn("shiny gold", rules)

        println(result)

        assertThat(result).isEqualTo(48160)
    }
}

val exampleRuleTree8Bags = listOf(
    Bag(
        number = 1, // 1
        colour = "dark olive",
        children = listOf(
            Bag(
                number = 3, // + (1*3)
                colour = "faded blue",
                children = emptyList()
            ),
            Bag(
                number = 4, // + (1*4)
                colour = "dotted black",
                children = emptyList()
            )
        )
    )
)

val exampleRuleTree12Bags = listOf(
    Bag(
        number = 1, // 1
        colour = "dark olive",
        children = listOf(
            Bag(
                number = 3, // + (1*3)
                colour = "faded blue",
                children = emptyList()
            ),
            Bag(
                number = 4, // + (1*4)
                colour = "dotted black",
                children = listOf(
                    Bag(
                        number = 1, // + (1*4*1)
                        colour = "faded blue",
                        children = emptyList()
                    )
                )
            )
        )
    )
)

val exampleRuleTree48Bags = listOf(
    Bag(
        number = 1,
        colour = "dark olive",
        children = listOf(
            Bag(
                number = 3,
                colour = "faded blue",
                children = emptyList()
            ),
            Bag(
                number = 4,
                colour = "dotted black",
                children = listOf(
                    Bag(
                        number = 1,
                        colour = "faded blue",
                        children = emptyList()
                    )
                )
            )
        )
    ),
    Bag(
        number = 2,
        colour = "vibrant plum",
        children = listOf(
            Bag(
                number = 5,
                colour = "faded blue",
                children = emptyList()
            ),
            Bag(
                number = 6,
                colour = "dotted black",
                children = listOf(
                    Bag(
                        number = 1,
                        colour = "faded blue",
                        children = emptyList()
                    )
                )
            )
        )
    )
)