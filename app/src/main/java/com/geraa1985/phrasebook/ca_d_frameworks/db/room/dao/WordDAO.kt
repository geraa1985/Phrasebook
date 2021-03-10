package com.geraa1985.phrasebook.ca_d_frameworks.db.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.geraa1985.phrasebook.ca_d_frameworks.db.room.entities.RoomWord

@Dao
interface WordDAO {

    @Query("SELECT * FROM RoomWord")
    fun getAll(): List<RoomWord>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWord(word: RoomWord)

    @Query("SELECT * FROM RoomWord WHERE word LIKE '%' || :word  || '%'")
    fun getWordByChar(word: String): List<RoomWord>

}