package calculator

import calculator.model.Separator
import org.junit.jupiter.api.Test

class SeparatorTest {
    @Test
    fun `저장된 구분자를 가져올 수 있다`() {
        val separators = mutableListOf(",", "^")
        val separator = Separator(separators)

        assert(separator.getSeperator() == separators)
    }

    @Test
    fun `구분자를 추가할 수 있다`() {
        val separators = mutableListOf(",", "^")
        val separator = Separator(separators)
        separators.addLast("$")

        separator.addSeperator("$")

        assert(separator.getSeperator() == separators)
    }
}