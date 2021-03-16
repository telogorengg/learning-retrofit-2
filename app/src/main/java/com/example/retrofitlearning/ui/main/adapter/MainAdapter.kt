package com.example.retrofitlearning.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitlearning.R
import com.example.retrofitlearning.data.model.User
import kotlinx.android.synthetic.main.list_user.view.*

class MainAdapter(private val users: ArrayList<User>) : RecyclerView.Adapter<MainAdapter.DataViewHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_user, parent, false))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    fun addUsers(users: List<User>)
    {
        this.users.apply {
            clear()
            addAll(users)
        }
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        fun bind(user: User)
        {
            itemView.apply {
                Glide.with(avatarIv.context)
                    .load(user.userAvatar)
                    .into(avatarIv)

                userNameTv.text = user.userName
                userEmailTv.text = user.userEmail
            }
        }
    }
}