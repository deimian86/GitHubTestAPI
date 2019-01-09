package com.damgonzalez.githubapitest.core.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Repository {

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("forkCount")
    @Expose
    var forkCount: Int? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    override fun toString(): String {
        return "Repository(name=$name, description=$description, forkCount=$forkCount, url=$url)"
    }

}