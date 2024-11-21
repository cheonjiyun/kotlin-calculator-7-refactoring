package calculator.model

import calculator.view.OutputView

class Calculator(private val numberList: List<Int>, private val outputView: OutputView) {
    fun sum() {
        outputView.printResult(numberList.sum())
    }

}