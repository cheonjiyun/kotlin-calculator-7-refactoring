package calculator.controller

import calculator.SEPARATOR_COMMAND_BACK
import calculator.SEPARATOR_COMMAND_FRONT
import calculator.model.Calculator
import calculator.model.Separator
import calculator.view.InputView
import calculator.view.OutputView

class Controller(private val inputView: InputView, private val outputView: OutputView) {
    private fun getUserSeparator(userInput: String): String? {
        val separatorRegex = Regex("(?<=$SEPARATOR_COMMAND_FRONT)(.*?)(?=$SEPARATOR_COMMAND_BACK)") // 정규식
        return separatorRegex.find(userInput)?.value
    }


    private fun getOnlyNumberStringInInput(userInput: String, userSeparator: String?): String {
        //구분자입력까지 길이
        val onlyNumberInputStringIndex =
            userSeparator?.length?.plus(SEPARATOR_COMMAND_FRONT.length + SEPARATOR_COMMAND_BACK.length - 1) ?: 0

        //구분자입력 이후로 숫자만 추출
        return userInput.substring(onlyNumberInputStringIndex)
    }

    private fun getNumberList(userInput: String, userSeparator: String?, separator: Separator): List<Int> {
        val onlyNumberInputString = getOnlyNumberStringInInput(userInput, userSeparator)

        val splitNumbers = onlyNumberInputString.split(separator.getSeperator().joinToString("|").toRegex())
        // 숫자로 바꿀 수 있는지 먼저 확인
        checkNumberAndPositive(splitNumbers)
        return splitNumbers.map { if (it == "") 0 else it.toInt() }
    }

    private fun checkNumberAndPositive(numberList: List<String>) {
        if (numberList.any {
                !it.matches(Regex("\\d+"))
            }) {
            throw IllegalArgumentException("0이상의 정수가 아닙니다")
        }
    }

    fun runController() {
        // 입력
        val userInput = inputView.inputString()

        // 구분자
        val userSeparator = getUserSeparator(userInput)
        val separator = Separator(defaultSeparators)
        userSeparator?.let { separator.addSeperator(it) }

        // 숫자
        val numberList = getNumberList(userInput, userSeparator, separator)
        val calculator = Calculator(numberList, outputView)

        // 합
        calculator.sum()
    }

    companion object {
        val defaultSeparators = mutableListOf(",", ":")
    }

}