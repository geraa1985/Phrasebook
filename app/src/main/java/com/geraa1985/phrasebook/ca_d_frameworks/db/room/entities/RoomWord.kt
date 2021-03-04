package com.geraa1985.phrasebook.ca_d_frameworks.db.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomWord(
    @PrimaryKey val word: String
)