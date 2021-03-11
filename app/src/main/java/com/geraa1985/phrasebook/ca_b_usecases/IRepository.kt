package com.geraa1985.phrasebook.ca_b_usecases

import com.geraa1985.model.DataModel

interface IRepository {
    suspend fun getData(word: String): List<com.geraa1985.model.DataModel>
}