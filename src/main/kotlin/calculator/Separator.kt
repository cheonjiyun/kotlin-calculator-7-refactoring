package calculator

class Separator(val seperators: MutableList<String>) {


    fun addSeperator(string: String) {
        seperators.addLast(string)
    }

    fun getSeperator(): List<String> {
        return seperators
    }
}