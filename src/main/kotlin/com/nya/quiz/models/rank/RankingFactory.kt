package com.nya.quiz.models.rank

//class RankingFactory(private val rankingRepository: RankingRepositoryImpl) {
class RankingFactory {
//    fun create(): RankingModel {
//        return RankingModel(rankingRepository)
//    }
    // 코틀린에서는 내부가 한줄로 끝나는 경우 표현식 본문을 사용하는 것이 권장
    // 코드가 짧아 가독성이 높아짐, 간결하고 명확함
    fun create() = RankingModel(RankingRepositoryImpl)
}