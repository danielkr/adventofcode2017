package dk.aoc

import java.io.File
import java.util.*

val LAYER_PATTERN = Regex("""(\d+): (\d+)""")

fun main(args: Array<String>) {
    val inputFile = File("src/main/resources/inputs/day13.txt")
    val lines = inputFile.readLines()
    val layers = parseLayers(lines)
    val severity = calculateSeverity(layers)
    println(severity)
}

fun parseLayers(lines: List<String>): SortedMap<Int, Int> {
    val res = TreeMap<Int, Int>()
    lines.forEach {
        val result = LAYER_PATTERN.find(it)
        if (result != null) {
            res.put(result.groupValues[1].toInt(), result.groupValues[2].toInt())
        }
    }
    return res
}

fun calculateSeverity(layers : SortedMap<Int, Int>) =
    layers.entries.sumBy {
        layerSeverity(it.key, it.value)
    }

fun layerSeverity(depth : Int, range : Int) =
        if (isCaught(depth, range))
            depth * range
        else
            0

fun isCaught(depth: Int, range: Int) : Boolean {
    val periodocity = (range - 1) * 2;
    return range == 1 || depth % periodocity == 0
}

