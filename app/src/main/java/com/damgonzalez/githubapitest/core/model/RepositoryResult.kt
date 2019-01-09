package com.damgonzalez.githubapitest.core.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class RepositoryResult {

    @SerializedName("data")
    @Expose
    var data: RepositoryData? = null

}

class RepositoryData {

    @SerializedName("repository")
    @Expose
    var repository: Repository? = null

}
