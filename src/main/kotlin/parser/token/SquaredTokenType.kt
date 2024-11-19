package z3roco01.squared.parser.token

/**
 * enum that holds all the token types
 */
enum class SquaredTokenType(var char: Char) {
    SQUARE('|'),
    SQRT('\\'),
    OUTPUT(','),
    PRINT('.'),
    IMMEDIATE(' '), // doesnt have one character
    NOP(' ') // doesnt actually get parsed in;
}