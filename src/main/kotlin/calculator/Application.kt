package calculator

import calculator.view.InputView
import calculator.view.OutputView

// 구분자입력 형식
const val SEPARATOR_COMMAND_FRONT = "//"
const val SEPARATOR_COMMAND_BACK = "\\\\n"

//const val SEPERLATOR = "1"

fun getUserSeparator(userInput: String): String? {
    val separatorRegex = Regex("(?<=${SEPARATOR_COMMAND_FRONT})(.*?)(?=${SEPARATOR_COMMAND_BACK})") // 정규식
    return separatorRegex.find(userInput)?.value
}

fun getNumberList(userInput: String, userSeparator: String?): List<Int> {
    //구분자입력까지 길이
    val onlyNumberInputStringIndex =
        userSeparator?.length?.plus(SEPARATOR_COMMAND_FRONT.length + SEPARATOR_COMMAND_BACK.length - 1) ?: 0

    //구분자입력 이후로 숫자만 추출
    val onlyNumberInputString = userInput.substring(onlyNumberInputStringIndex)

    try {
        return onlyNumberInputString.split(",|:|${userSeparator}".toRegex()).map { it.toInt() }
    } catch (err: NumberFormatException) {
        throw IllegalArgumentException()
    }
}

fun checkNegative(numberList: List<Int>) {
    if (!numberList.all { it > 0 }) {
        throw IllegalArgumentException()
    }
}


fun main() {
    val inputView = InputView()
    val outputView = OutputView()

    // 입력
    val userInput = inputView.inputString()

    // 구분자
    val userSeparator = getUserSeparator(userInput)

    // 숫자
    val numberList = getNumberList(userInput, userSeparator)
    checkNegative(numberList)

    // 합
    outputView.printResult(numberList.sum())
}
