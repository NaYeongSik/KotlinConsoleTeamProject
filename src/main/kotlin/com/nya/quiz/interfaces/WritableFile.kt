package com.nya.quiz.interfaces

interface WritableFile {
    val fileName : String
    fun writeFile() : Boolean
}