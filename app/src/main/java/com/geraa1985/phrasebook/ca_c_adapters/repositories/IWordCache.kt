package com.geraa1985.phrasebook.ca_c_adapters.repositories

interface IWordCache {
    fun getAllWords(): List<String>
    fun getWordsByChars(chars: String): List<String>
    fun addWord(word: String)
}