package com.fsec3grp1.fseuser.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_profile.*
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.fsec3grp1.fseuser.DB.LoginDBAdapter
import com.fsec3grp1.fseuser.R
import com.fsec3grp1.fseuser.User
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class ProfileActivity : AppCompatActivity()   {

    private val pactivity = this@ProfileActivity
    private lateinit var loginDBAdapter: LoginDBAdapter
    var mUserName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val fileToRead = "LoggedInUsername"
        this.openFileInput(fileToRead).use { stream ->
            mUserName = stream.bufferedReader().use {
                it.readLine()
            }
            tvwmessage.text = "Welcome $mUserName"
            Log.i("username: ", mUserName)
        }

        if(mUserName.isEmpty()) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        imlogout.setOnClickListener {
            val fileToWrite = "LoggedInUsername"
            val textToWrite = ""
            this.openFileOutput(fileToWrite, Context.MODE_PRIVATE).
            use { output ->
                output.write(textToWrite.toByteArray())
            }

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        btupdate.setOnClickListener {
            val intent = Intent(this, UpdateProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

        loginDBAdapter = LoginDBAdapter(pactivity)
        displaySingleuser()
    }

    private fun displaySingleuser() {
        val userToDisplay: User = loginDBAdapter.displayUser(mUserName)
        tvFullname.text = tvFullname.text.toString()+userToDisplay.name
        tvCity.text = tvCity.text.toString()+userToDisplay.city
        tvEmail.text = tvEmail.text.toString()+userToDisplay.email
    }

}