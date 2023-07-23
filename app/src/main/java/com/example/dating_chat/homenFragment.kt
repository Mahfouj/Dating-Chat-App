package com.example.dating_chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.load

import com.example.dating_chat.databinding.FragmentHomenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class homenFragment : Fragment(),UserAdapter.UserListener {

       lateinit var  binding: FragmentHomenBinding
       lateinit var userdb:DatabaseReference
       lateinit var adapter: UserAdapter
        var userList:MutableList<User> = mutableListOf()

         lateinit var firebaseUser: FirebaseUser
          private   val  bundle=Bundle()
          private  var currentUser:User?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

  binding= FragmentHomenBinding.inflate(layoutInflater,container,false)


        userdb = FirebaseDatabase.getInstance().reference
        binding.logOutBtn.setOnClickListener {

            val auth=FirebaseAuth.getInstance()

          auth.signOut().apply {
              findNavController().navigate(R.id.action_homenFragment_to_loginFragment)
          }

        }

        binding.profileImage.setOnClickListener {

            currentUser?.let {

                bundle.putString(profileFragment.USERID,it.userId)

              findNavController().navigate(R.id.action_homenFragment_to_profileFragment,bundle)
            }


        }



          FirebaseAuth.getInstance().currentUser?.let {
                firebaseUser=it
          }





        adapter= UserAdapter (this@homenFragment)

        binding.chatRecyclerView.adapter = adapter

        getAllAvailableUser()


                 return binding.root
    }

    private fun getAllAvailableUser() {

         userdb.child(DBNODES.USER).addValueEventListener(

             object:ValueEventListener {

                 override fun onDataChange(snapshot: DataSnapshot) {
                     userList.clear()

                     snapshot.children.forEach {

                         val user: User = it.getValue(User::class.java)!!

                         if (firebaseUser.uid != user.userId) {
                             userList.add(user)

                         } else {

                             currentUser=user

                             user.profileImage?.let { it1 -> setProfile(it1) }

                         }
                     }

                     adapter.submitList(userList)

                 }

                 override fun onCancelled(error: DatabaseError) {

                 }




                 })
             }

    private fun setProfile(imageLink:String) {

        currentUser?.let {
            binding.profileImage.load(imageLink)
        }
    }

    override fun userItemClick(user: User) {



        bundle.putString(profileFragment.USERID,user.userId)


          findNavController().navigate(R.id.action_homenFragment_to_profileFragment,bundle)
    }


}
