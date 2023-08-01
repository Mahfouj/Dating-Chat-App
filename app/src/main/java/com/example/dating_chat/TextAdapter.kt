package com.example.dating_chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TextAdapter(var userIDSelf: String,
                  private val chatList :MutableList<TextMessage>):
    RecyclerView.Adapter<TextAdapter.TextViewHolder>(){

    private val LEFT: Int = 1
    private val RIGHT: Int = 2



    class TextViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
        var messageTV: TextView = itemView.findViewById(R.id.chatTv)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {

     return   if (viewType == RIGHT) {

             val view = LayoutInflater.from(parent.context)

                .inflate(R.layout.ittim_right_chat, parent, false)

            TextViewHolder(view)

        }else{


                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.ittim_left_chat, parent, false)

                return TextViewHolder(view)
        }


    }
    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {

        val message = chatList[position]
        holder.messageTV.text = message.text

    }

    override fun getItemViewType(position: Int): Int {

    return if (chatList[position].senderId == userIDSelf) {

             RIGHT

        }else {
               LEFT

        }
    }

    override fun getItemCount(): Int {

        return chatList.size
    }


}
