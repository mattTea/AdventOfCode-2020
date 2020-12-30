package aoc

fun busAndWait(input: Pair<Int, String>): Int {
    val estimate = input.first
    return input.second
        .split(",")
        .filterNot { it == "x" }
        .map { it.toInt() }
        .map { busId -> Pair(estimate % busId, busId) }
        .map { Pair(it.second, it.second - it.first) }
        .sortedBy { it.second }.first()
        .let { it.first * it.second }
}

// part 2

/*

the remainder list has to be related to the value in the number list,
such that for num list of [7, 13, x, x, 59, x, 31, 19]
we want an increase in timestamp of [0, 1, 2, 3, 4, 5, 6, 7]
which, when taken with the number, the actual remainders are... [0, 13-1, x, x, 59-4, x, 31-6, 19-7]

so, each element in index list is 'num[index]-index'


what we want is...
x % num[0] = rem[0]
x % 7 = 0
x % 17 = 16

*/

fun remainders(input: String): List<Int> =
    input.split(",")
        .mapIndexed { index, it ->
            if (index == 0) Pair(it.toInt(), 0) else {
                if (it != "x") {
                    Pair(it.toInt(), it.toInt() - index)
                } else {
                    Pair(-1, index)
                }
            }
        }
        .filterNot { it.first == -1 }
        .map { it.second }

fun numbers(input: String): List<Int> =
    input.split(",")
        .map { if (it == "x") -1 else it.toInt() }
        .filterNot { it == -1 }

/*
Bus: 7 Bus index: 0 RunTime: 7 Stride: 1
========== Increment time by time stride: '1' where strided time + bus index: '0' is a multiple of bus id: '7'
-------- RunTime: 7 RunTime+index: 7

Bus: 13 Bus index: 1 RunTime: 7 Stride: 7
========== Increment time by time stride: '7' where strided time + bus index: '1' is a multiple of bus id: '13'
-------- RunTime: 7 RunTime+index: 8
-------- RunTime: 14 RunTime+index: 15
-------- RunTime: 21 RunTime+index: 22
-------- RunTime: 28 RunTime+index: 29
-------- RunTime: 35 RunTime+index: 36
-------- RunTime: 42 RunTime+index: 43
-------- RunTime: 49 RunTime+index: 50
-------- RunTime: 56 RunTime+index: 57
-------- RunTime: 63 RunTime+index: 64
-------- RunTime: 70 RunTime+index: 71
-------- RunTime: 77 RunTime+index: 78

Bus: 59 Bus index: 4 RunTime: 77 Stride: 91
========== Increment time by time stride: '91' where strided time + bus index: '4' is a multiple of bus id: '59'
-------- RunTime: 77 RunTime+index: 81
-------- RunTime: 168 RunTime+index: 172
-------- RunTime: 259 RunTime+index: 263
-------- RunTime: 350 RunTime+index: 354

Bus: 31 Bus index: 6 RunTime: 350 Stride: 5369
========== Increment time by time stride: '5369' where strided time + bus index: '6' is a multiple of bus id: '31'
-------- RunTime: 350 RunTime+index: 356
-------- RunTime: 5719 RunTime+index: 5725
-------- RunTime: 11088 RunTime+index: 11094
-------- RunTime: 16457 RunTime+index: 16463
-------- RunTime: 21826 RunTime+index: 21832
-------- RunTime: 27195 RunTime+index: 27201
-------- RunTime: 32564 RunTime+index: 32570
-------- RunTime: 37933 RunTime+index: 37939
-------- RunTime: 43302 RunTime+index: 43308
-------- RunTime: 48671 RunTime+index: 48677
-------- RunTime: 54040 RunTime+index: 54046
-------- RunTime: 59409 RunTime+index: 59415
-------- RunTime: 64778 RunTime+index: 64784
-------- RunTime: 70147 RunTime+index: 70153

Bus: 19 Bus index: 7 RunTime: 70147 Stride: 166439
========== Increment time by time stride: '166439' where strided time + bus index: '7' is a multiple of bus id: '19'
-------- RunTime: 70147 RunTime+index: 70154
-------- RunTime: 236586 RunTime+index: 236593
-------- RunTime: 403025 RunTime+index: 403032
-------- RunTime: 569464 RunTime+index: 569471
-------- RunTime: 735903 RunTime+index: 735910
-------- RunTime: 902342 RunTime+index: 902349
-------- RunTime: 1068781 RunTime+index: 1068788

ANSWER: 1068781
 */

fun runTime(buses: List<Int>): Int {
    var step = 7
    var index = 1
    var lcm = buses[0] * buses[1]

    val timestamp: MutableList<Int> = mutableListOf()

    buses.drop(1).forEach { bus ->
        // find runtime where runtime % stride == 0 && (runtime + index) % bus == 0
        var runtime = buses[index-1]
        while (runtime < lcm) {
            if (runtime % step == 0 && (runtime + index) % bus == 0) {
                timestamp += runtime
                break
            } else {
                runtime += step
                println(runtime)
            }

        }
    }

    return timestamp.reduce { total, next -> total + next}
}