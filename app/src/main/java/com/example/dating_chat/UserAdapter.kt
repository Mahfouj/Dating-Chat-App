package com.example.dating_chat

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.example.dating_chat.databinding.IttiemViewBinding

class UserAdapter(private val userListener:UserListener):

   ListAdapter<User,UserAdapter.UserViewHolder>(COMPARATOR) {

    private lateinit var context: Context

    interface UserListener {

        fun userItemClick(user: User)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        context = parent.context
        return UserViewHolder(
            IttiemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        getItem(position)?.let {

            holder.binding.apply {
                fullName.text = it.fullName
                userBio.text = it.bio
                email.text = it.email

                Glide.with(context).load(it.profileImage).placeholder(R.drawable.dating5)
                    .into(profileImage)

                holder.itemView.setOnClickListener { _ ->

                    userListener.userItemClick(it)

                }
            }


        }
    }


    companion object {

        val COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.userId == newItem.userId


            }

        }

    }


    class UserViewHolder(val binding: IttiemViewBinding) : RecyclerView.ViewHolder(binding.root)

}



















