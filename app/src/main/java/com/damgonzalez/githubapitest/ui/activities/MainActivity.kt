package com.damgonzalez.githubapitest.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.damgonzalez.githubapitest.R
import com.damgonzalez.githubapitest.core.model.Query
import com.damgonzalez.githubapitest.core.remote.GitHubApi
import com.damgonzalez.githubapitest.util.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    private lateinit var gitApi: GitHubApi
    private lateinit var disposable:Disposable

    companion object {
        val Log = Logger.getLogger(MainActivity::class.java.name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // BASIC REPO INFO

        btnConsulta1.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            val queryBody = Query()
            queryBody.query = Constants.QUERY_REPO_INFO
            gitApi = GitHubApi.create()
            disposable = gitApi.getRepoInfo(queryBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    progressBar.visibility = View.INVISIBLE
                    Log.info(result.data?.repository.toString())
                    txtResponse.text = result.data?.repository.toString()
                }, { e ->
                    progressBar.visibility = View.INVISIBLE
                    e.printStackTrace()
                })
        }

        // GO TO LIST ACTIVITY

        btnConsulta2.setOnClickListener {
            val intent = ListActivity.newIntent(this)
            startActivity(intent)
        }

    }

}


