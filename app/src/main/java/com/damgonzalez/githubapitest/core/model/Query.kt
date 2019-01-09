package com.damgonzalez.githubapitest.core.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Query {

    @SerializedName("query")
    @Expose
    var query: String? = null

}