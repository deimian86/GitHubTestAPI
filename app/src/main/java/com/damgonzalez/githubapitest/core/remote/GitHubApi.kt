package com.damgonzalez.githubapitest.core.remote

import com.damgonzalez.githubapitest.BuildConfig
import com.damgonzalez.githubapitest.core.model.Query
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import io.reactivex.Observable
import com.damgonzalez.githubapitest.core.model.RepositoryResult
import com.damgonzalez.githubapitest.core.model.UserListResult
import com.damgonzalez.githubapitest.core.model.UserResult
import com.damgonzalez.githubapitest.util.Constants

interface GitHubApi {

    @Headers("Authorization:Bearer " + BuildConfig.GITHUB_TOKEN)
    @POST("graphql")
    fun getRepoInfo(@Body jsonQuery: Query): Observable<RepositoryResult>

    @Headers("Authorization:Bearer " + BuildConfig.GITHUB_TOKEN)
    @POST("graphql")
    fun getRepoWatchers(@Body jsonQuery: Query): Observable<UserListResult>

    @Headers("Authorization:Bearer " + BuildConfig.GITHUB_TOKEN)
    @POST("graphql")
    fun getWatcherDetail(@Body jsonQuery: Query): Observable<UserResult>

    companion object Factory {

        fun create(): GitHubApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            return retrofit.create(GitHubApi::class.java);

        }

    }
}