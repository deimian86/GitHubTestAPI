package com.damgonzalez.githubapitest.core.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserListResult {

    @SerializedName("data")
    @Expose
    var data: UserListData? = null

}

class UserListData {

    @SerializedName("repository")
    @Expose
    var watchers: Watchers? = null

}

class Watchers {

    @SerializedName("watchers")
    @Expose
    var edges: Edges? = null

}

class Edges {

    @SerializedName("edges")
    @Expose
    var users: ArrayList<User>? = null

}

