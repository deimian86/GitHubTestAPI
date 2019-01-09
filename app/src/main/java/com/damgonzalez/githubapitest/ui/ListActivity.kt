package com.damgonzalez.githubapitest.ui

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.damgonzalez.githubapitest.R
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.damgonzalez.githubapitest.core.model.Query
import com.damgonzalez.githubapitest.core.model.User
import com.damgonzalez.githubapitest.core.remote.GitHubApi
import com.damgonzalez.githubapitest.ui.adapter.UserAdapter
import com.damgonzalez.githubapitest.util.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_list.*
import java.util.logging.Logger

class ListActivity : AppCompatActivity() {

    private lateinit var gitApi: GitHubApi
    private lateinit var disposable: Disposable
    val watchers: ArrayList<User> = ArrayList()

    companion object {

        val Log = Logger.getLogger(ListActivity::class.java.name)

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, ListActivity::class.java)
            return intent
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        // INIT RECYCLERVIEW

        rvList.layoutManager = LinearLayoutManager(this)
        val mAdapter = UserAdapter()
        mAdapter.UserAdapter(watchers, this)
        rvList.adapter = mAdapter

        // REQUEST WATCHERS

        progressBar.visibility = View.VISIBLE
        val queryBody = Query()
        queryBody.query = Constants.QUERY_REPO_LIST_WATCHERS
        gitApi = GitHubApi.create()
        disposable = gitApi.getRepoWatchers(queryBody)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                progressBar.visibility = View.INVISIBLE
                Log.info("usersCount = " + result.data?.watchers?.edges?.users?.count())
                mAdapter.updateData(result.data?.watchers?.edges?.users)
            }, { e ->
                progressBar.visibility = View.INVISIBLE
                e.printStackTrace()
            })
    }

}
