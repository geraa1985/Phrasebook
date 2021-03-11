package com.geraa1985.cache.room.caches

import com.geraa1985.cache.IWordCache
import com.geraa1985.cache.room.appdb.AppDB
import com.geraa1985.cache.room.entities.RoomWord

class WordCache(private val db: AppDB): IWordCache {

    override fun getAllWords(): List<String> =
        db.wordDAO.getAll().map {
            it.word
        }


    override fun getWordsByChars(chars: String): List<String> =
        db.wordDAO.getWordByChar(chars).map {
            it.word
        }

    override fun addWord(word: String) {
        return db.wordDAO.insertWord(RoomWord(word))
    }
}