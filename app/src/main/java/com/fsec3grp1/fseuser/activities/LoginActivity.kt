package com.fsec3grp1.fseuser.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.fsec3grp1.fseuser.DB.LoginDBAdapter
import com.fsec3grp1.fseuser.R
import com.fsec3grp1.fseuser.model.User
import com.fsec3grp1.fseuser.utils.Constants
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val lactivity = this@LoginActivity
    private lateinit var loginDBAdapter: LoginDBAdapter
    var mUserName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        try {
            this.openFileInput(Constants.LOGGED_IN_USER_FILENAME).use { stream ->
                mUserName = stream?.bufferedReader().use {
                    it?.readLine() ?: ""
                }
            }
        }
        catch(e: java.io.FileNotFoundException) {
            Log.e("File not found: ", e.toString())
        }

        if(!mUserName.isEmpty()) {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

        btlogin.setOnClickListener {
            if(checkLoginValidation()) {
                val username = etlusername.text.toString()
                this.openFileOutput(Constants.LOGGED_IN_USER_FILENAME, Context.MODE_PRIVATE).
                use { output ->
                    output.write(username.toByteArray())
                }

                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                finish()
            }
            else {
                Toast.makeText(applicationContext, "Wrong username or password !", Toast.LENGTH_LONG).show()
            }
        }

        tvsignup.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        tvfpassword.setOnClickListener {
            val intent = Intent(this, ForgotActivity::class.java)
            startActivity(intent)
            finish()
        }

        tvhomepage.setOnClickListener {
            val intent = Intent(this, HomepageActivity::class.java)
            startActivity(intent)
            finish()
        }

        loginDBAdapter = LoginDBAdapter(lactivity)
    }

    private fun checkLoginValidation(): Boolean {
        val username = etlusername.text.toString()
        val password = etlpassword.text.toString()
        if(username.isEmpty() || password.isEmpty()) {
            Toast.makeText(applicationContext, "Username or password cannot be empty !", Toast.LENGTH_LONG).show()
            return false
        }
        else {
            val user: User = loginDBAdapter.displayUser(username)
            return password == user.password
        }

    }
}