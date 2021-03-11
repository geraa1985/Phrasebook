package com.geraa1985.cache.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomWord(
    @PrimaryKey val word: String
)