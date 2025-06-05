package com.nya.quiz.interfaces

interface WritableFile {
    fun writeFile(text: String) : Boolean
    fun updateFile(text: String): Boolean
}