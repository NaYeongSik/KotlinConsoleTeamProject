package com.nya.quiz.interfaces

interface WritableFile {
    val fileName : String
    fun writeFile(text: String) : Boolean
    fun updateFile(text: String): Boolean
}