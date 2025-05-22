package com.nya.quiz.interfaces

interface ReadableFile {
    val fileName : String
    fun readFile(): Boolean
}