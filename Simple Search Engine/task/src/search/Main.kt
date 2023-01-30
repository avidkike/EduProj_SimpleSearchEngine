package search

import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val peopleList = File(".\\${args[1]}").readLines()
    while (true) {
        when(askMenu()) {
            0 -> println("Bye!").also { exitProcess(0) }
            1 -> {
                val strategy = readln().lowercase()
                val phrase = readln().lowercase().split(" ")
                println(peopleList.search(phrase, strategy))
            }
            else -> println(peopleList.joinToString("\n"))
        }
    }
}

fun askMenu() : Int {
    while (true) {
        println("=== Menu ===\n1. Find a person\n2. Print all people\n0. Exit")
        val ans = readln()
        if (ans !in listOf("1", "2", "0")) println("Incorrect option! Try again.") else return ans.toInt()
    }
}

fun List<String>.search(phrase: List<String>, strategy: String): String {

    val hitList = when (strategy) {
        "all" -> { this.map { it.split(" ") }.filter { it.all { s -> s.lowercase() in phrase } } }
        "any" -> { this.map { it.split(" ") }.filter { it.any { s -> s.lowercase() in phrase } } }
        else -> { this.map { it.split(" ") }.filter { it.none { s -> s.lowercase() in phrase } } }
    }
    return if (hitList.isEmpty()) "No matching people found."
    else "People found:\n${hitList.joinToString("\n") { it.joinToString(" ") } }"
}
