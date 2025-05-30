package com.nya.quiz.interfaces

interface WritableFile {
    val fileName : String
    fun writeFile(str: String) : Boolean
}