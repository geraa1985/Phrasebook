package com.geraa1985.phrasebook.ca_d_frameworks.db.room.caches

import com.geraa1985.phrasebook.ca_c_adapters.repositories.IWordCache
import com.geraa1985.phrasebook.ca_d_frameworks.db.room.appdb.AppDB
import com.geraa1985.phrasebook.ca_d_frameworks.db.room.entities.RoomWord

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