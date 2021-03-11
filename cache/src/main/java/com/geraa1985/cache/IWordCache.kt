package com.geraa1985.cache

interface IWordCache {
    fun getAllWords(): List<String>
    fun getWordsByChars(chars: String): List<String>
    fun addWord(word: String)
}