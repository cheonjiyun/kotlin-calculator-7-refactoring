package calculator

import calculator.controller.Controller
import calculator.view.InputView
import calculator.view.OutputView

// 구분자입력 형식
const val SEPARATOR_COMMAND_FRONT = "//"
const val SEPARATOR_COMMAND_BACK = "\\\\n"


fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val controller = Controller(inputView, outputView)

    controller.runController()
}
