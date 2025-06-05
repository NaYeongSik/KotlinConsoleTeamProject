package com.nya.quiz.interfaces

interface ReadableFile {
    suspend fun readFile(): List<String>?
}