package calculator.model

class Separator(val separators: MutableList<String>) {
    fun addSeperator(string: String) {
        separators.addLast(string)
    }

    fun getSeperator(): List<String> {
        return separators
    }
}