package z3roco01.squared.parser

import z3roco01.squared.parser.token.SquaredToken
import z3roco01.squared.parser.token.SquaredTokenType
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

/**
 * a fully parsed program
 */
class SquaredProgram {
    var tokens = arrayListOf<SquaredToken>()
    // the accumulator, set to 0 at run
    private var accumulator: Byte = 0

    /**
     * appends a token to the token list
     * @param token the token to be appended
     */
    fun addToken(token: SquaredToken) {
        tokens.add(token)
    }

    /**
     * runs the stored program
     */
    fun run() {
        // reset accum
        accumulator = 2

        // type of current token in the loop
        var type = SquaredTokenType.NOP

        for(token in tokens) {
            type = token.type

            // do the logic for the token the proper amount of times
            for(i in 0..<token.times) {
                when(type) {
                    SquaredTokenType.SQUARE -> accumulator = accumulator.toDouble().pow(2).roundToInt().toByte()
                    SquaredTokenType.SQRT   -> accumulator = sqrt(accumulator.toDouble()).roundToInt().toByte()
                    SquaredTokenType.OUTPUT -> print("${accumulator} ")
                    SquaredTokenType.PRINT  -> print(accumulator.toInt().toChar())
                    SquaredTokenType.IMMEDIATE -> accumulator = token.data
                    SquaredTokenType.NOP    -> {}
                }
            }
        }
    }
}