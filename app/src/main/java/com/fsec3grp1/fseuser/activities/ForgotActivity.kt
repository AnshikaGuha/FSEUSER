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
    private lateinit var loginDBAdapter:LoginDBAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        loginDBAdapter = LoginDBAdapter(fpactivity)

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
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
//                finish()
                Toast.makeText(this, "Password Successfully Changed", Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText( this, "Username or email is incorrect !", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun changePasswordValidation(): Boolean {
        val username = etfusername.text.toString()
        val email = etfemail.text.toString()
        val password = etfpassword.text.toString()
        if(username.isEmpty()|| email.isEmpty() || password.isEmpty()) {
            return false
            Toast.makeText(this,"fields cannot be empty",Toast.LENGTH_LONG).show()
        }
        else {
            val user: User = loginDBAdapter.displayUser(username)
            return email == user.email
        }
    }
//        val funame = R.id.etfusername.toString()
//        val userToDisplay: User = loginDBAdapter.displayUser(funame)
//        val fname = userToDisplay.name
//        val fcity = userToDisplay.city
//        val fcpassword = userToDisplay.cpassword

    private fun updateUser() {
        val check : Int
        val user = User(
            username = etfusername.text.toString(),
            name = "",
            email = etfemail.text.toString(),
            city = "",
            password = etfpassword.text.toString()
        )
        check = loginDBAdapter.updateUser(user)

        if (check > 0) {
            Toast.makeText(applicationContext, "Details updated", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(applicationContext, "Cannot Update, something went wrong!!", Toast.LENGTH_LONG).show()
        }
    }

}