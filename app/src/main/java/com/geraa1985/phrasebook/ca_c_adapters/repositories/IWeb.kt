package com.geraa1985.phrasebook.ca_c_adapters.repositories

import com.geraa1985.phrasebook.ca_a_entities.DataModel

interface IWeb {
    suspend fun getData(word: String): List<DataModel>
}