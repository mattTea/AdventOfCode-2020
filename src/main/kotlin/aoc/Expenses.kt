package aoc

fun multiplyTwoExpenses(expenses: List<Int>): Int =
    findTwo2020Entries(expenses).reduce { total, next -> total * next }

fun multiplyThreeExpenses(expenses: List<Int>): Int =
    findThree2020Entries(expenses).reduce { total, next -> total * next }

fun findTwo2020Entries(expenses: List<Int>): List<Int> =
    expenses.flatMap { firstExpense ->
        expenses.filter { it + firstExpense == 2020 }
    }

fun findThree2020Entries(expenses: List<Int>): List<Int> =
    expenses.flatMap { firstExpense ->
        expenses.flatMap { secondExpense ->
            expenses.filter { it + firstExpense + secondExpense == 2020 }
        }
    }.distinct()

