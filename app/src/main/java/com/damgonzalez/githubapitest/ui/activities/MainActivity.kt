package com.damgonzalez.githubapitest.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.damgonzalez.githubapitest.R
import com.damgonzalez.githubapitest.core.model.Query
import com.damgonzalez.githubapitest.core.remote.GitHubApi
import com.damgonzalez.githubapitest.util.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 2000 //3 seconds
    private lateinit var gitApi: GitHubApi
    private lateinit var disposable:Disposable

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val intent = ListActivity.newIntent(this)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar.visibility = View.VISIBLE
        val queryBody = Query()
        queryBody.query = Constants.QUERY_REPO_INFO
        gitApi = GitHubApi.create()
        disposable = gitApi.getRepoInfo(queryBody)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                progressBar.visibility = View.INVISIBLE
                txtDescription.visibility = View.VISIBLE
                txtDescription.text = result.data?.repository!!.description
                YoYo.with(Techniques.Tada).duration(1500).playOn(imgLogo);
                mDelayHandler = Handler()
                mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
            }, { e ->
                progressBar.visibility = View.INVISIBLE
                e.printStackTrace()
            })
    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }

}


