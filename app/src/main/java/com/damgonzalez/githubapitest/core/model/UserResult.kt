package com.damgonzalez.githubapitest.core.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class UserResult {

    @SerializedName("data")
    @Expose
    var data: UserData? = null

}

class UserData {

    @SerializedName(value="repositoryOwner")
    @Expose
    var user: User? = null

}
