package com.damgonzalez.githubapitest.ui.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import com.damgonzalez.githubapitest.R
import com.damgonzalez.githubapitest.core.db.LocalDB
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import android.support.v7.widget.Toolbar
import android.view.View
import com.damgonzalez.githubapitest.core.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail_content.*
import android.app.ActivityOptions



class DetailActivity : AppCompatActivity() {

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
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        fetchUserDataFromDb()
    }

    private fun fetchUserDataFromDb() {
        Observable.fromCallable({
            val db = LocalDB.getInstance(context = this)
            db?.userDataDao()?.getUserById(intent.getStringExtra(INTENT_USER_ID))!!.get(0)
        }).doOnNext({ user ->
            this.runOnUiThread(Runnable {
                initData(user)
            })
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    private fun initData(user : User){

        // User nick
        toolbar_layout.title = user.login

        // User avatar
        Picasso.get().load(user.avatarUrl).fit().centerCrop().into(imgDetailProfile)

        // User Name
        tvNumber0.text = user.name
        if(user.name.isNullOrEmpty()) lytName.visibility = View.GONE

        // User Mail
        tvNumber1.text = user.email
        if(user.email.isNullOrEmpty()) lytMail.visibility = View.GONE

        fab.setOnClickListener {
            if(!user.email.isNullOrEmpty()) {
                sendMail(user.email)
            } else {
                Snackbar.make(
                    coordinator, // Parent view
                    getString(R.string.send_mail_error), // Message to show
                    Snackbar.LENGTH_SHORT // How long to display the message.
                ).show()
            }
        }

        // User Location
        tvNumber2.text = user.location
        if(user.location.isNullOrEmpty()) lytLocation.visibility = View.GONE

        val map = "http://maps.google.com/maps?q=" + user.location
        tvNumber2.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(map))
            startActivity(i)
        }

        // User Bio
        tvNumber3.text = user.bio
        if(user.bio.isNullOrEmpty()) lytBio.visibility = View.GONE

        // User Links
        tvNumber4.text = user.url
        tvNumber5.text = user.websiteUrl
        if(user.websiteUrl.isNullOrEmpty()) lytPersonalSite.visibility = View.GONE

    }

    private fun sendMail(mail : String?) {
        val to = mail
        val intent = Intent(Intent.ACTION_SEND)
        val addressees = arrayOf(to)
        intent.putExtra(Intent.EXTRA_EMAIL, addressees)
        intent.setType("message/rfc822")
        val mailString: String = getString(R.string.send_mail)
        startActivity(Intent.createChooser(intent, mailString));
    }

}
