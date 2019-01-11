package com.damgonzalez.githubapitest.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.damgonzalez.githubapitest.core.model.User
import com.damgonzalez.githubapitest.R
import com.damgonzalez.githubapitest.core.model.Node
import com.squareup.picasso.Picasso

class UserAdapter(var data : ArrayList<Node>, private val itemClickListener: (Int) -> Unit) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data.get(position)
        bind(holder, item.user!!,itemClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.list_row, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val userName = view.findViewById(R.id.txtName) as TextView
        val userBio = view.findViewById(R.id.txtBio) as TextView
        val userPhoto = view.findViewById(R.id.imgUser) as ImageView

    }

    fun bind(holder:ViewHolder, user:User, itemClickListener:(Int)->Unit) {

        // User name
        holder.userName.text = user.login

        // User bio
        holder.userBio.text = user.bio
        if(!user.bio.isNullOrBlank()) {
            holder.userBio.visibility = View.VISIBLE
        } else {
            holder.userBio.visibility = View.GONE
        }

        // User avatar
        Picasso.get().load(user.avatarUrl).fit().centerCrop().into(holder.userPhoto)

        // Click listener
        holder.itemView.setOnClickListener { itemClickListener(holder.adapterPosition) }

    }
}

