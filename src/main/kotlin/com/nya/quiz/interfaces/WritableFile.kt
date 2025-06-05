package com.nya.quiz.interfaces

interface WritableFile {
    suspend fun writeFile(text: String)
    suspend fun updateFile(text: String)
}