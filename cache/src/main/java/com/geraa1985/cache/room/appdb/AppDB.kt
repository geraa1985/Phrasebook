package com.geraa1985.cache.room.appdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geraa1985.cache.room.dao.WordDAO
import com.geraa1985.cache.room.entities.RoomWord

@Database(entities = [RoomWord::class], version = 1, exportSchema = false)
abstract class AppDB: RoomDatabase() {

    abstract val wordDAO: WordDAO

    companion object {
        const val NAME_DB = "database.db"
    }

}