//package com.example.dating_chat
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.constraintlayout.widget.Group
//import androidx.recyclerview.widget.RecyclerView
//import coil.load
//import com.example.dating_chat.databinding.GroupItemViewBinding
//
//class groupAdapter(var groupList: MutableList<Group>)
//    :RecyclerView.Adapter<groupAdapter.ViewGroupHolder>(){
//
// class ViewGroupHolder(var binding: GroupItemViewBinding):RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewGroupHolder {
//
//        return ViewGroupHolder(GroupItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
//
//    }
//
//    override fun getItemCount(): Int {
// return groupList.size
//    }
//
//    override fun onBindViewHolder(holder: ViewGroupHolder, position: Int) {
//       val group=groupList [position]
//
//        group.apply {
////            holder.binding.fullName.text = group.groupName
////            holder.binding.GroupBio.text = group. groupBio
////            holder.binding.profileImage.load(group.groupImage)
//
//        }
//
//
//    }
//
//
//}