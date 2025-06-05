package com.nya.quiz.interfaces

interface ReadableFile {
    // 코투린 객체를 받기 위한 suspend 함수로 정의
    suspend fun readFile(): List<String>?
}