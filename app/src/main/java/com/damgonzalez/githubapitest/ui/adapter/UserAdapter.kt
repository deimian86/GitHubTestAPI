package com.damgonzalez.githubapitest.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.damgonzalez.githubapitest.core.model.User
import com.damgonzalez.githubapitest.R
import com.damgonzalez.githubapitest.core.model.Node

class UserAdapter(var data : ArrayList<Node>, private val itemClickListener: (Int) -> Unit) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data.get(position)
        holder.bind(item.user!!,itemClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.list_row, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val userName = view.findViewById(R.id.txtResponse) as TextView

        fun bind(user:User ,itemClickListener:(Int)->Unit) {
            userName.text = user.id
            itemView.setOnClickListener { itemClickListener(adapterPosition) }

        }
    }
}

