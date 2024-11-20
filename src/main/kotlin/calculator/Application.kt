package calculator

import camp.nextstep.edu.missionutils.Console.readLine

// 구분자입력 형식
val SEPARATOR_COMMAND_FRONT = "//"
val SEPARATOR_COMMAND_BACK = "\\\\n"

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
    // 입력
    println("덧셈할 문자열을 입력해 주세요.")
    val userInput = readLine()

    // 구분자
    val userSeparator = getUserSeparator(userInput)

    // 숫자
    val numberList = getNumberList(userInput, userSeparator)
    checkNegative(numberList)

    // 합
    println("결과 : ${numberList.sum()}")
}
