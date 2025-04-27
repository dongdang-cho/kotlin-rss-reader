package study.view

object InputView {
    fun getSearchKeword(): String {
        println("검색어를 입력하세요 (없으면 전체 출력):")
        val input = readln()
        return input.trim()
    }
}
