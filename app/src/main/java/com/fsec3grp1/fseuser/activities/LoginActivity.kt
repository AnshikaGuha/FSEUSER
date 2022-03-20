package com.fsec3grp1.fseuser.activities

import android.content.Context
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
//            val fileToWrite = "LoggedInUsername"
//            val textToWrite: String = etlusername.text.toString()
//            this.openFileOutput(fileToWrite, Context.MODE_PRIVATE).
//                use { output ->
//                    output.write(textToWrite.toByteArray())
//            }

            val username: String = etlusername.text.toString()
            val intent= Intent(this, ProfileActivity::class.java)
            intent.putExtra("user_name",username)
            startActivity(intent)
//            finish()
        }

        tvsignup.setOnClickListener {
            val intent=Intent(this, RegisterActivity::class.java)
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
    }
}