package calculator

import calculator.model.Calculator
import calculator.view.OutputView
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.io.PrintStream

class CalculatorTest {
    private lateinit var outputMessage: OutputStream

    @BeforeEach
    fun init() {
        outputMessage = ByteArrayOutputStream()
        System.setOut(PrintStream(outputMessage))
    }

    @AfterEach
    fun printOutput() {
        System.setOut(System.out)
        println(output())
    }

    private fun output(): String {
        return outputMessage.toString().trim()
    }

    @Test
    fun `숫자 더한 결과가 나온다`() {
        val numberList = mutableListOf(1, 2, 5)
        val outputView = OutputView()
        val calculator = Calculator(numberList, outputView)

        calculator.sum()

        assert(output().contains((1 + 2 + 5).toString()))
    }
}

