package com.geraa1985.phrasebook.ca_c_adapters.repositories

import com.geraa1985.model.DataModel

interface IWeb {
    suspend fun getData(word: String): List<com.geraa1985.model.DataModel>
}