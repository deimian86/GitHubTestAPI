package com.damgonzalez.githubapitest.core.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("avatarUrl")
    @Expose
    var avatarUrl: String? = null

    @SerializedName("bio")
    @Expose
    var bio: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("location")
    @Expose
    var location: String? = null

    @SerializedName("login")
    @Expose
    var login: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    override fun toString(): String {
        return "User(id=$id, login=$login, name=$name)"
    }


}