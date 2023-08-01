//package com.example.dating_chat
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.constraintlayout.widget.Group
//import com.example.dating_chat.databinding.FragmentGroupsBinding
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//
//class groupsFragment : Fragment() {
//
//    lateinit var userDb: DatabaseReference
//    lateinit var binding: FragmentGroupsBinding
//
//    val groupList = mutableListOf<Group>()
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//
//        binding = FragmentGroupsBinding.inflate(layoutInflater,container,false)
//
//        userDb = FirebaseDatabase.getInstance().reference
//
//        return  binding.root
//
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//        userDb.child(DBNODES.Group) .addValueEventListener(
//            object : ValueEventListener {
//
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    snapshot.getValue( Group::class.java)?.let {
//
//                  groupList.add(it)
//
//                    }
//
//              val adapter=groupAdapter(groupList)
//
//                    binding.groupRcv.adapter=adapter
//
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//
//                }
//
//
//            })
//
//
//    }
//
//
//
//    }
