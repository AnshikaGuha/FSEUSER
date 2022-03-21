package com.fsec3grp1.fseuser.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_forgot.*
import android.content.Intent
import com.fsec3grp1.fseuser.DB.LoginDBAdapter
import com.fsec3grp1.fseuser.R
import com.fsec3grp1.fseuser.model.User
import kotlinx.android.synthetic.main.activity_forgot.tvlogin
import kotlinx.android.synthetic.main.activity_forgot.tvhomepage

class ForgotActivity : AppCompatActivity(){
    private val fpactivity = this@ForgotActivity
    private lateinit var loginDBAdapter: LoginDBAdapter
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)
        tvlogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        tvhomepage.setOnClickListener {
            val intent = Intent(this, HomepageActivity::class.java)
            startActivity(intent)
            finish()
        }
        btfpsubmit.setOnClickListener {
            if(changePasswordValidation()) {
                updateUser()
            }
        }
        loginDBAdapter = LoginDBAdapter(fpactivity)
    }

    private fun changePasswordValidation(): Boolean {
        val username = etfusername.text.toString()
        val email = etfemail.text.toString()
        val password = etfpassword.text.toString()
        if(username.isEmpty()|| email.isEmpty() || password.isEmpty()) {
            Toast.makeText(applicationContext,"Provide all inputs !",Toast.LENGTH_LONG).show()
            return false
        }
        else {
            user = loginDBAdapter.displayUser(username)
            val match = (email == user.email)
            if(!match) {
                Toast.makeText(applicationContext, "Username & email doesn't match !", Toast.LENGTH_LONG).show()
            }
            return match
        }
    }

    private fun updateUser() {
        user.password = etfpassword.text.toString().trim()
        val check = loginDBAdapter.updateUser(user)
        if (check > 0) {
            Toast.makeText(applicationContext, "Password successfully changed !", Toast.LENGTH_LONG).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        else {
            Toast.makeText(applicationContext, "Cannot update password, something went wrong !", Toast.LENGTH_LONG).show()
        }
    }

}