package com.damgonzalez.githubapitest.util

class Constants {

    companion object {

        @JvmField
        val BASE_URL = "https://api.github.com/";

        @JvmField
        val REPO_NAME = "ember.js"

        @JvmField
        val REPO_OWNER = "emberjs"

        @JvmField
        val QUERY_REPO_INFO = "query { repository(owner:\"$REPO_OWNER\", name:\"$REPO_NAME\") {" +
                "name description forkCount url }}"

        @JvmField
        val QUERY_REPO_LIST_WATCHERS = "query { repository(owner:\"$REPO_OWNER\", name:\"$REPO_NAME\") {" +
                "id name watchers(first: 30) { edges { node { id avatarUrl bio email location login name websiteUrl url } } } }" +
                " rateLimit { cost } }"

    }

}