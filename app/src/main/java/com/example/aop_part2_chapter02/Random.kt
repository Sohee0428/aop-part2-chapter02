package com.example.aop_part2_chapter02

import kotlin.random.Random

fun test1() {
    val random = java.util.Random()

    for (i in 1..6) {
        println("${random.nextInt(45) + 1}")
    }
//    이렇게 작성하면 중복된 숫자가 나올 수 있다.
}

fun test2() {
    val random = java.util.Random()
    val list = mutableListOf<Int>()

    while (list.size < 6) {
        val randomNumber = random.nextInt(45) + 1
        if (list.contains(randomNumber)) {
            continue
        }
        list.add(randomNumber)
    }
    println(list)
}

fun test3() {
    val random = java.util.Random()
    val set = mutableSetOf<Int>()

    while (set.size < 6) {
        val randomNumber = random.nextInt(45) + 1
        set.add(randomNumber)
    }
    println(set)
}


fun test4() {
    val list = mutableListOf<Int>().apply {
        for (i in 1..45) {
            this.add(i)
        }
    }
    list.shuffle()

    println(list.subList(0, 6))
}

fun main() {
}