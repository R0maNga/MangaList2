package com.example.mangalist.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.mangalist.databinding.FragmentAuthenticationBinding
import com.google.firebase.auth.FirebaseAuth

class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthenticationBinding
    private lateinit var auth: FirebaseAuth
    private var megaStatus = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthenticationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        checkUser()

        binding.btnLogIn.setOnClickListener {
            if (megaStatus) {
                logIn()

            }
            else{
                registration()

            }

        }
        binding.btnLogOut.setOnClickListener {
            auth.signOut()
            Toast.makeText(context, "Leaved", Toast.LENGTH_SHORT).show()
            checkUser()
        }
        binding.btnSignIn.setOnClickListener {
            reg(view)
        }

    }

    fun reg(view: View) {
        if (megaStatus) {
            if (binding.etEmail.text.toString().isEmpty() || binding.etPassword.text.toString()
                    .isEmpty()
            ) {
                Toast.makeText(context, "Password or Email empty", Toast.LENGTH_SHORT).show()
            } else {
                if (binding.etPassword.text.toString() != binding.etConfirmPassword.text.toString()) {
                    Toast.makeText(context, "Passwords are not the same", Toast.LENGTH_SHORT).show()
                } else {
                    auth.createUserWithEmailAndPassword(
                        binding.etEmail.text.toString(),
                        binding.etPassword.text.toString()
                    ).addOnSuccessListener {
                        Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show()
                        auth.signInWithEmailAndPassword(
                            binding.etEmail.text.toString(),
                            binding.etPassword.text.toString()
                        )
                        Navigation.findNavController(view).popBackStack()
                    }.addOnFailureListener {
                        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else {
            if (binding.etEmail.text.toString().isEmpty() || binding.etPassword.text.toString()
                    .isEmpty()
            ) {
                Toast.makeText(context, "Password or Email empty", Toast.LENGTH_SHORT).show()
            } else
                auth.signInWithEmailAndPassword(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                ).addOnSuccessListener {
                    Toast.makeText(context, "Logged", Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(view).popBackStack()
                }.addOnFailureListener {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
        }

    }


    override fun onStart() {
        super.onStart()
        checkUser()

    }

    fun checkUser() {
        if (auth.currentUser !== null) {
            binding.btnSignIn.isVisible = false
            binding.etConfirmPassword.isVisible = false
            binding.btnLogIn.isVisible = false
            binding.etPassword.isVisible = false
            binding.etEmail.isVisible = false
            binding.tvRegistrationLabel.text = "Logged"
            binding.btnLogOut.isVisible = true
        } else {
            binding.btnSignIn.isVisible = true
            binding.etConfirmPassword.isVisible = true
            binding.btnLogIn.isVisible = true
            binding.etPassword.isVisible = true
            binding.etEmail.isVisible = true
            binding.tvRegistrationLabel.text = "Registration"
            binding.btnLogOut.isVisible = false
        }
    }

    fun logIn()
    {
        megaStatus = false
        binding.btnSignIn.isVisible = true
        binding.btnSignIn.text = "Log In"
        binding.etConfirmPassword.isVisible = false
        binding.btnLogIn.isVisible = true
        binding.btnLogIn.text= "Registration"
        binding.etPassword.isVisible = true
        binding.etEmail.isVisible = true
        binding.tvRegistrationLabel.text = "Log In"
        binding.btnLogOut.isVisible = false
    }
    fun registration()
    {   megaStatus = true
        binding.btnSignIn.isVisible = true
        binding.etConfirmPassword.isVisible = true
        binding.btnLogIn.isVisible = true
        binding.etPassword.isVisible = true
        binding.etEmail.isVisible = true
        binding.tvRegistrationLabel.text = "Registration"
        binding.btnLogOut.isVisible = false
    }
}