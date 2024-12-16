package com.example.handyhive

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class LoginActivity : AppCompatActivity() {

    private lateinit var loginUsername: EditText
    private lateinit var loginPassword: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginUsername = findViewById(R.id.login_username)
        loginPassword = findViewById(R.id.login_password)
        loginButton = findViewById(R.id.login_button)

        loginButton.setOnClickListener {
            val username = loginUsername.text.toString().trim()
            val password = loginPassword.text.toString().trim()

            if (username.isEmpty()) {
                loginUsername.error = "Username cannot be empty"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                loginPassword.error = "Password cannot be empty"
                return@setOnClickListener
            }

            // Call the checkUser function to validate login
            checkUser(username, password)
        }
    }

    private fun checkUser(username: String, password: String) {
        val reference: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")
        val checkUserDatabase: Query = reference.orderByChild("username").equalTo(username)

        checkUserDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    // User exists, check password
                    val passwordFromDB = snapshot.child(username).child("password").getValue(String::class.java)
                    if (passwordFromDB == password) {
                        // Password correct, navigate to DashboardActivity
                        val intent = Intent(this@LoginActivity, Dashboardactivity::class.java)
                        startActivity(intent)
                        finish() // Close the login activity
                    } else {
                        // Incorrect password
                        loginPassword.error = "Invalid credentials"
                    }
                } else {
                    // User does not exist
                    loginUsername.error = "User does not exist"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle any errors (optional)
                Toast.makeText(this@LoginActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }
}
