package calculator

class Seperator(val seperators: List<String>) {

    fun addSeperator(string: String) {
        seperators.addLast(string)
    }

    fun getSeperator(): List<String> {
        return seperators
    }
}