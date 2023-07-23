package com.example.dating_chat

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.dating_chat.R.id.action_loginFragment_to_homenFragment
import com.example.dating_chat.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
   lateinit var firebaseUser: FirebaseUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding= FragmentLoginBinding.inflate(inflater,container,false)

      FirebaseAuth.getInstance().currentUser?.let {
          findNavController().navigate( R.id.action_loginFragment_to_homenFragment)
      }
             binding.loginButton.setOnClickListener {

            val password =binding.passwordEditText.text.toString().trim()
           val email =binding.emailEditText.text.toString().trim()

            if (isEmailValid(email)&& isPasswordValid(password)) {
                loginUser (email,password)

            } else {
                Toast.makeText(requireContext(), "Invalid email or Password ", Toast.LENGTH_SHORT)
                    .show()
            }

            binding.signupTextView.setOnClickListener {

                findNavController().navigate(R.id.action_loginFragment_to_razisterFragment)

            }


       }
         return  binding.root
    }

    private fun isPasswordValid(password: String): Boolean {
       return password.length>=6
    }

  private fun isEmailValid(email:String): Boolean {

       return   Patterns.EMAIL_ADDRESS.matcher(email).matches()
  }

    private fun loginUser(email: String, password:  String) {

    val auth= FirebaseAuth.getInstance()

     auth.signInWithEmailAndPassword(email,password).

      addOnCompleteListener { task ->
         if (task.isSuccessful) {
             val user = auth.currentUser

             Toast.makeText(context, "Login Successful: ${user?.email}", Toast.LENGTH_SHORT)
                 .show()

                      findNavController().navigate(R.id.action_loginFragment_to_homenFragment)



      } else {
        Toast.makeText(context,"Login Failed:${task.exception?.message}",Toast.LENGTH_SHORT).show()


            }
     }
  }

}