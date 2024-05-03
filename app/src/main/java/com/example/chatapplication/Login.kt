package com.example.chatapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWeakPasswordException


class Login : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

            supportActionBar?.hide()     //Hide the top bar
            
            mAuth = FirebaseAuth.getInstance()

            edtEmail = findViewById(R.id.edt_email)
            edtPassword = findViewById(R.id.edt_password)
            btnLogin = findViewById(R.id.btnLogin)
            btnSignUp = findViewById(R.id.btnSignUp)

            btnSignUp.setOnClickListener{
                val intent = Intent (this, SignUp::class.java)      //Intent-is a message that allows one application component to interact with another component
                startActivity(intent)                                               //In this case when we click on SigUp we are redirected to the SignUp view

            }

            btnLogin.setOnClickListener{
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()

                login(email, password);
            }


    }

    private fun login(email: String, password: String) {
        //login for logging user

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for logging in user
                    val intent = Intent(this@Login, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@Login, "Login failed. User does not exist or wrong password.", Toast.LENGTH_SHORT).show()
                }
            }
    }

}