package z3roco01.squared.parser

import z3roco01.squared.parser.token.SquaredToken
import z3roco01.squared.parser.token.SquaredTokenType
import kotlin.math.sinh

/**
 * handles the parsing of a squared program
 */
class SquaredParser {
    /**
     * accumulator ( accum ) - one byte register
     * | ( pipe ) - accum = accum^2
     * \ ( backslash ) - accum = sqrt(accum)
     * , ( comma ) - print(accum)
     * . ( period ) - print(accum.toChar())
     * <any number> - accum = number
     */
    companion object {
        /**
         * parses the provided code
         * @param code the code to be parsed
         * @return the parsed [SquaredProgram]
         */
        fun parse(code: String): SquaredProgram {
            var program = SquaredProgram()

            // current character
            var char = ' '
            var inNumber = false
            // loop over every character in the code
            for(i in 0..<code.length) {
                char = code[i]
                when(char) {
                    // check if it matches any tokens character, if it does add it
                    SquaredTokenType.SQUARE.char -> {
                        program.addToken(SquaredToken(SquaredTokenType.SQUARE))
                        inNumber = false
                    }
                    SquaredTokenType.SQRT.char   -> {
                        program.addToken(SquaredToken(SquaredTokenType.SQUARE))
                        inNumber = false
                    }
                    SquaredTokenType.OUTPUT.char -> {
                        program.addToken(SquaredToken(SquaredTokenType.OUTPUT))
                        inNumber = false
                    }
                    SquaredTokenType.PRINT.char  -> {program.addToken(SquaredToken(SquaredTokenType.PRINT))}
                    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
                        -> {
                            if(!inNumber) {
                                var y = i
                                while(code[y].isDigit()) {
                                    println(y)
                                    y++}
                                program.addToken(SquaredToken(SquaredTokenType.IMMEDIATE, code.substring(i..<y).toByte()))
                                inNumber = true
                            }
                        }
                }
            }
            collapse(program)

            println()

            return program
        }

        /**
         * collapses any tokens that reoccur right after each other into one
         */
        private fun collapse(program: SquaredProgram) {
            // create empty new array
            var newToks = arrayListOf<SquaredToken>()
            var reoccurs = false
            var nextToken = SquaredToken(SquaredTokenType.NOP)
            var curToken = SquaredToken(SquaredTokenType.NOP)
            var skipNext = false

            // while there are still reoccurenses
            do {
                // reset reoccurs
                reoccurs = false

                for(i in 0..<program.tokens.size) {
                    if(skipNext) {
                        skipNext = false
                        continue
                    }
                    curToken = program.tokens[i]
                    // if there is a reoccurance and they are identical
                    if((i+1 < program.tokens.size) && curToken == program.tokens[i+1]) {
                        // add a new token that is the 2 collapsed
                        newToks.add(SquaredToken(curToken.type, curToken.data, curToken.times + program.tokens[i+1].times))
                        // and set reoccurs
                        reoccurs = true
                        skipNext = true
                    }else {
                        // else add the token to the list
                        newToks.add(curToken)
                    }
                }
                // set the programs tokens to the new ones
                program.tokens = newToks

                // and empty newToks
                newToks = arrayListOf()
            }while(reoccurs)
        }
    }

}