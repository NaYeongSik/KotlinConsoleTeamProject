package com.nya.quiz.interfaces

interface DeletableFile{
    val fileName : String
    fun deleteFile(): Boolean
}