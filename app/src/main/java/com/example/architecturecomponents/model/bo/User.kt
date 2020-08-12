package com.example.architecturecomponents.model.bo

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "users")
@Parcelize
data class User(
    var id: String? = null,
    var avatar_url: String? = null,
    var login: String? = null,
    var html_url: String? = null
): Parcelable
