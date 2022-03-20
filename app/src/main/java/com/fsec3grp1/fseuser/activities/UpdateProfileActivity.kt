package com.fsec3grp1.fseuser.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.fsec3grp1.fseuser.DB.LoginDBAdapter
import com.fsec3grp1.fseuser.R
import com.fsec3grp1.fseuser.User
import kotlinx.android.synthetic.main.activity_forgot.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.imlogout
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_update_profile.*

class UpdateProfileActivity : AppCompatActivity() {

    private val uactivity = this@UpdateProfileActivity
    private lateinit var loginDBAdapter: LoginDBAdapter
    var mUserName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)

        val fileToRead = "LoggedInUsername"
        this.openFileInput(fileToRead).use { stream ->
            mUserName = stream.bufferedReader().use {
                it.readLine()
            }
            tvusername.text = mUserName
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

//        btupdate.setOnClickListener {
//            val intent = Intent(this, ProfileActivity::class.java)
//            startActivity(intent)
//            finish()
//        }

        loginDBAdapter = LoginDBAdapter(uactivity)
        displaySingleuser()
    }

    private fun displaySingleuser() {
        val userToDisplay: User = loginDBAdapter.displayUser(mUserName)
        etuname.setText(userToDisplay.name)
        etuemail.setText(userToDisplay.email)
        etucity.setText(userToDisplay.city)
        etupassword.setText(userToDisplay.password)
    }

    private fun updateUser() {
        var check : Int

        if (etuname.text.trim().isNotEmpty() || etuemail.text.trim().isNotEmpty() || etucity.text.trim().isNotEmpty() || etupassword.text.trim().isNotEmpty()) {
            Toast.makeText(applicationContext, "Update atleast one Profile Information", Toast.LENGTH_SHORT).show()
        }
        else {
            var user = User(
//                id = id.text.toString().toInt(),
                username = etusername.text.toString(),
                name = etuname.text.toString(),
                email = etuemail.text.toString(),
                city = etucity.text.toString(),
                password = etupassword.text.toString(),
                cpassword = etcpassword.text.toString()
            )
            check = loginDBAdapter.updateUser(user)

            if (check > 0) {
                Toast.makeText(applicationContext, "Details updated", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Cannot Update, something went wrong!!", Toast.LENGTH_LONG).show()
            }
        }
    }
}