package com.damgonzalez.githubapitest.core.model

import com.google.gson.annotations.SerializedName
import android.arch.persistence.room.Entity

@Entity(tableName = "userData")
data class User(
        @SerializedName("id") var id: String? = null,
        @SerializedName("avatarUrl") var avatarUrl: String? = null,
        @SerializedName("bio") var bio: String? = null,
        @SerializedName("email") var email: String? = null,
        @SerializedName("location") var location: String? = null,
        @SerializedName("login") var login: String? = null,
        @SerializedName("name") var name: String? = null
)