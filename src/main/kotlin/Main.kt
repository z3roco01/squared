package z3roco01.squared

import z3roco01.squared.parser.SquaredParser
import z3roco01.squared.parser.SquaredProgram

fun main() {
    val TEST_CODE = "65."
    var program = SquaredParser.parse(TEST_CODE)

    program.run()
}