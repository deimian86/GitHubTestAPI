package com.damgonzalez.githubapitest.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.damgonzalez.githubapitest.core.model.User
import com.damgonzalez.githubapitest.R

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    var data: ArrayList<User>  = ArrayList()
    lateinit var context:Context

    fun UserAdapter(data : ArrayList<User>, context: Context){
        this.data = data
        this.context = context
    }

    fun updateData(data: ArrayList<User>?) {
        Log.d("DEBUG", "data.count = " + data!!.count())
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data.get(position)
        holder.bind(item, context)
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

        fun bind(user:User, context: Context){
            userName.text = user.name
            itemView.setOnClickListener(View.OnClickListener {
                Toast.makeText(context, user.id, Toast.LENGTH_SHORT).show()
            })
        }
    }
}

