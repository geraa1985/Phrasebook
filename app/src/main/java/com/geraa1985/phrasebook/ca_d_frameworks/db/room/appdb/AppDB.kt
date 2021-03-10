package com.geraa1985.phrasebook.ca_d_frameworks.db.room.appdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geraa1985.phrasebook.ca_d_frameworks.db.room.dao.WordDAO
import com.geraa1985.phrasebook.ca_d_frameworks.db.room.entities.RoomWord

@Database(entities = [RoomWord::class], version = 1, exportSchema = false)
abstract class AppDB: RoomDatabase() {

    abstract val wordDAO: WordDAO

    companion object {
        const val NAME_DB = "database.db"
    }

}