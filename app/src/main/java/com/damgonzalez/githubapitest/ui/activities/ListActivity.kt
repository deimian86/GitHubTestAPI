package com.damgonzalez.githubapitest.ui.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.damgonzalez.githubapitest.R
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.damgonzalez.githubapitest.core.db.LocalDB
import com.damgonzalez.githubapitest.core.model.Node
import com.damgonzalez.githubapitest.core.model.Query
import com.damgonzalez.githubapitest.core.remote.GitHubApi
import com.damgonzalez.githubapitest.ui.adapter.UserAdapter
import com.damgonzalez.githubapitest.util.Constants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_list.*
import java.util.*


class ListActivity : AppCompatActivity() {

    private lateinit var gitApi: GitHubApi
    private lateinit var disposable: Disposable
    private var data: ArrayList<Node>? = null

    companion object {

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, ListActivity::class.java)
            return intent
        }

    }

    val itemOnClick: (Int) -> Unit = { position ->
        rvList.adapter!!.notifyDataSetChanged()
        val intent = DetailActivity.newIntent(this, data?.get(position)?.user?.id!!)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        rvList.layoutManager = LinearLayoutManager(this)
        progressBar.visibility = View.VISIBLE
        val queryBody = Query()
        queryBody.query = Constants.QUERY_REPO_LIST_WATCHERS
        gitApi = GitHubApi.create()
        disposable = gitApi.getRepoWatchers(queryBody)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                progressBar.visibility = View.INVISIBLE
                rvList.layoutManager = LinearLayoutManager(this@ListActivity)
                rvList.setHasFixedSize(true)
                data = result.data?.watchers?.edges?.users!!
                insertOnLocalDB()
                val adapter = UserAdapter(data!!, itemClickListener = itemOnClick)
                rvList.adapter = adapter
            }, { e ->
                progressBar.visibility = View.INVISIBLE
                e.printStackTrace()
            })
    }

    fun insertOnLocalDB() {
        Observable.fromCallable({
            val db = LocalDB.getInstance(context = this)
            val userDataDao = db?.userDataDao()
            with(userDataDao){
                data!!.forEach {
                    this?.insert(it.user!!)
                }
            }
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

}
