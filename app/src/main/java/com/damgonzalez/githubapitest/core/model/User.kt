package com.damgonzalez.githubapitest.core.model

import com.google.gson.annotations.SerializedName
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "userData")
data class User(
        @PrimaryKey
        @SerializedName("id")  var id: String,
        @SerializedName("avatarUrl") var avatarUrl: String? = null,
        @SerializedName("bio") var bio: String? = null,
        @SerializedName("email") var email: String? = null,
        @SerializedName("location") var location: String? = null,
        @SerializedName("login") var login: String? = null,
        @SerializedName("websiteUrl") var websiteUrl: String? = null,
        @SerializedName("url") var url: String? = null,
        @SerializedName("name") var name: String? = null
)