package com.geraa1985.phrasebook.ca_a_entities

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Meanings(
    @Expose @SerializedName("translation") val translation: Translation?,
    @Expose @SerializedName("imageUrl") val imageUrl: String?
): Parcelable
