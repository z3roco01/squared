package z3roco01.squared.parser.token

/**
 * holds both the token type and how many times it reoccurs in a row
 */
class SquaredToken(var type: SquaredTokenType,var data: Byte = 0, var times: Long = 1) {
    constructor(type: SquaredTokenType, times: Long): this(type, 0, times)

    operator override fun equals(other: Any?): Boolean {
        if(!(other is SquaredToken))
            return false
        return (other.data == this.data && other.type == this.type)
    }

    override fun toString(): String {
       return "$type: $data x$times"
    }
}