package com.geraa1985.phrasebook.ca_c_adapters.viewmodels

interface ILoadImage<T, O> {
    fun loadInto(url: String, container: T, options: O?, progressGone: () -> Unit)
}