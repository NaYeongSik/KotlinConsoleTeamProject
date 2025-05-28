package com.nya.quiz.models.rank

import com.nya.quiz.commons.QuizStat
import com.nya.quiz.interfaces.rank.RankingRepository

object RankingRepositoryImpl : RankingRepository<QuizStat>{

    override fun getMyRanking(id: String): QuizStat {
        TODO("Not yet implemented")
        // TODO: 파일에서 해당 계정의 랭킹 정보 가져오기

        //Test
        var incorectQuizNum = mutableListOf<Int>()
        incorectQuizNum += 2
        return QuizStat("test1",incorectQuizNum,5,5)
    }

    override fun getTopRanking(): List<QuizStat> {
        TODO("Not yet implemented")
        // TODO: 파일에서 상위 랭킹 정보 가져오기
    }

    override fun recordRanking(id: String, score: Float) {
        TODO("Not yet implemented")
        // TODO: 파일에 해당 계정의 점수 수정하기
    }

    override fun deleteInfo(id: String) {
        TODO("Not yet implemented")
        // TODO: 파일에 해당 계정의 정보 지우기
    }
}