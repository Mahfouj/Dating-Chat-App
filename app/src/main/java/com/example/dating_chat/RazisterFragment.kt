package com.example.dating_chat

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.dating_chat.databinding.FragmentRazisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class RazisterFragment : Fragment() {

    lateinit var binding: FragmentRazisterBinding
    lateinit var userDb:DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentRazisterBinding.inflate(inflater, container, false)

        userDb = FirebaseDatabase.getInstance().reference


        binding.buttonRegister.setOnClickListener {

            val password = binding.editTextPassword.text.toString().trim()
            val email = binding.editTextEmail.text.toString().trim()
            val name = binding.editTextName.text.toString().trim()

            if (isEmailValid(email) && isPasswordValid(password)) {
                     registerUser(email, password, name)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Invalid email or Password ",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }

            return binding.root


    }

    private fun registerUser(email: String, password: String, name: String ) {
        val auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    findNavController().navigate(R.id.action_razisterFragment_to_homenFragment)

                    saveUserToDataBase(auth.currentUser?.uid, email, name )


                } else {
                    Toast.makeText(
                        context,
                        "Login Failed:${task.exception?.message}", Toast.LENGTH_SHORT
                    )
                        .show()


                }
            }
        }

      private fun saveUserToDataBase(uid: String?, email: String, name: String ) {

        uid?.let {

            val user = User(
                userId = uid, email = email, fullName = name

            )


            userDb.child(DBNODES.USER).child(it).setValue(user).addOnCompleteListener { task->
                if (task.isSuccessful) {

                    findNavController().navigate(R.id.action_razisterFragment_to_homenFragment)

                } else {
                    Toast.makeText(requireContext(), "${task.exception?.message}",Toast.LENGTH_SHORT).show()


                }

            }


        }


    }


    private fun isEmailValid(email: String): Boolean {
        return   Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length >=6
    }
}

