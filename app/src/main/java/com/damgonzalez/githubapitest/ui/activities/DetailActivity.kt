package com.damgonzalez.githubapitest.ui.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.damgonzalez.githubapitest.R
import com.damgonzalez.githubapitest.core.db.LocalDB
import com.damgonzalez.githubapitest.util.DbWorkerThread

class DetailActivity : AppCompatActivity() {

    private var mDb: LocalDB? = null
    private val mUiHandler = Handler()
    private lateinit var mDbWorkerThread: DbWorkerThread

    companion object {

        private val INTENT_USER_ID = "user_id"

        fun newIntent(context: Context, userId: String): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(INTENT_USER_ID, userId);
            return intent
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()
        mDb = LocalDB.getInstance(this)


    }

    private fun fetchUserDataFromDb() {
        val task = Runnable {
            val userData =
                mDb?.userDataDao()?.getAll()
                    mUiHandler.post({
                        userData?.size
                        Log.i("DEBUG", "userDataSize From Room = " + userData?.size);

            })
        }
        mDbWorkerThread.postTask(task)
    }
}
