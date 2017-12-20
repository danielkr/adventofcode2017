package dk.aoc

import java.io.File

fun main(args: Array<String>) {
    val inputFile = File("src/main/resources/inputs/day11.txt")
    val textSteps = inputFile.readText().split(",")
    val parsedSteps = parseSteps(textSteps)
    val coordinate = calculateResultingPosition(parsedSteps)
    println(coordinate)
    val minSteps = calculateMinStepsFromOrigin(coordinate)
    println(minSteps)
}

fun parseSteps(textSteps: List<String>) =
        textSteps.map {
            Step.valueOf(it.trim().toUpperCase())
        }.toList()

fun calculateResultingPosition(steps : List<Step>) : Position {
    var north = 0
    var east = 0
    steps.forEach {
        north += it.north
        east += it.east
    }
    return Position(north, east)
}

fun calculateMinStepsFromOrigin(position: Position) : Int {
    val absCoord = Position(Math.abs(position.north), Math.abs(position.east))
    if (absCoord.north > absCoord.east)
        return absCoord.east + (absCoord.north - absCoord.east) / 2
    return absCoord.east
}

data class Position(val north : Int, val east : Int)

enum class Step(val north : Int, val east : Int) {
    N(2,0),
    NE(1,1),
    SE(-1,1),
    S(-2,0),
    SW(-1,-1),
    NW(1,-1)
}