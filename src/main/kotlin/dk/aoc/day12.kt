package dk.aoc.day12

import java.io.File
import java.util.*

val PATTERN = Regex("""(\d+) <-> (.*)""")

fun main(args: Array<String>) {
    val inputFile = File("src/main/resources/inputs/day12.txt")
    val lines = inputFile.readLines()
    val neighbors = parseNeighbors(lines)
    println(neighbors)
    val group0 = mutableSetOf<Int>()
    addNeighborsRecursively(0, group0, neighbors)
    println(group0.size)
    println(group0)
}

fun parseNeighbors(lines: List<String>): SortedMap<Int, List<Int>> {
    val res = TreeMap<Int, List<Int>>()
    lines.forEach {
        val result = PATTERN.find(it)
        if (result != null) {
            val neighbors = result.groupValues[2]
                    .split(",")
                    .map (String::trim)
                    .map (String::toInt)
            res.put(result.groupValues[1].toInt(),neighbors)
        }
    }
    return res
}

fun addNeighborsRecursively(current: Int, group: MutableSet<Int>, neighbors: SortedMap<Int, List<Int>>) {
    if (!group.add(current))
        return
    neighbors.getOrDefault(current, listOf()).forEach {
        addNeighborsRecursively(it, group, neighbors)
    }
}


