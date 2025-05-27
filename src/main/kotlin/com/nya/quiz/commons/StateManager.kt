package com.nya.quiz.commons

/**
 * State manager
 * 상태 관리를 싱글톤 패턴으로 구현.
 * 메뉴 이동이나 동작 처리를 위해 상태로 관리하기 위해 만들었습니다. (시작 메뉴 상태, 메인 메뉴 상태, 종료 상태)
 * @constructor Create empty State manager
 */
object StateManager {
    private var state = ViewState.START_VIEW // 최초 상태를 시작 메뉴 상태로 초기화

    private fun setState(state: ViewState) {
        this.state = state
    }

    fun getState(): ViewState = this.state

    fun isEndState() = if (this.state == ViewState.END_VIEW) true else false // while문 종료 조건으로 사용. 사용자가 종료 입력시 프로그램 종료 처리를 위해

    /**
     * Update state
     * 현재 상태와 사용자 입력값을 종합해 상태를 변경하기 위한 함수. (ex. 로그인 성공시, 로그아웃시, 종료시)
     * 예시로 구현 해놓은거라 수정 필요. (구체적인 처리는 각각 부분에서 처리하고, 해당 부분에서는 상태만 변경하는 방식으로 변경이 필요할 것 같아요)
     * @param line : Test를 위해 잠시 파라미터로 받았습니다.
     */
    // TestOnly, 로그인 성공이라는 가정하에 메인메뉴로 넘어가는지 확인용
    fun updateState(line: String){
        when(this.state){
            ViewState.START_VIEW -> {
                if (StartViewState.fromInt(line.trim().toInt()) == StartViewState.LOG_IN) setState(ViewState.MAIN_VIEW)
            }
            ViewState.MAIN_VIEW -> TODO()
            ViewState.END_VIEW -> TODO()
        }
    }
}