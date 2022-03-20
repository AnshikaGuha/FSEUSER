package com.fsec3grp1.fseuser.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fsec3grp1.fseuser.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_profile.*

class LoginActivity : AppCompatActivity() {

    var mUserName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val fileToRead = "LoggedInUsername"
        try {
            this.openFileInput(fileToRead).use { stream ->
                mUserName = stream?.bufferedReader().use {
                    it?.readLine() ?: ""
                }
            }
        }
        catch(e: java.io.FileNotFoundException) {
            Log.e("file not found: ", e.toString())
        }

        if(!mUserName.isEmpty()) {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

        btlogin.setOnClickListener {
            val fileToWrite = "LoggedInUsername"
            val textToWrite = etlusername.text.toString()
            this.openFileOutput(fileToWrite, Context.MODE_PRIVATE).
                use { output ->
                    output.write(textToWrite.toByteArray())
            }

            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
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