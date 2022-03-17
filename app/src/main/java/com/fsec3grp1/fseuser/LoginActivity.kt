package com.fsec3grp1.fseuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fsec3grp1.fseuser.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btlogin.setOnClickListener {
            val intent= Intent(this,ProfileActivity::class.java)
            startActivity(intent)
//            finish()
        }
        tvsignup.setOnClickListener {
            val intent2=Intent(this,RegisterActivity::class.java)
            startActivity(intent2)
            finish()
        }
        tvfpassword.setOnClickListener {
            val intent4 = Intent(this,ForgotActivity::class.java)
            startActivity(intent4)
            finish()
        }
    }
}