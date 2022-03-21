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
import kotlinx.android.synthetic.main.activity_update_profile.*

class UpdateProfileActivity : AppCompatActivity() {

    private val uactivity = this@UpdateProfileActivity
    private lateinit var loginDBAdapter: LoginDBAdapter
    var mUserName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)

        try {
            this.openFileInput(Constants.LOGGED_IN_USER_FILENAME).use { stream ->
                mUserName = stream.bufferedReader().use {
                    it.readLine()
                }
                tvusername.text = mUserName
                Log.i("username: ", mUserName)
            }
        }
        catch(e: java.io.FileNotFoundException) {
            Log.e("File not found: ", e.toString())
        }

        if(mUserName.isEmpty()) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        tvhomepage.setOnClickListener {
            val intent = Intent(this, HomepageActivity::class.java)
            startActivity(intent)
            finish()
        }

        tvlogout.setOnClickListener {
            val textToWrite = ""
            this.openFileOutput(Constants.LOGGED_IN_USER_FILENAME, Context.MODE_PRIVATE).
            use { output ->
                output.write(textToWrite.toByteArray())
            }

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        btupdateprofile.setOnClickListener {
            updateUser()
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

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
        var user = User(
            username = mUserName,
            name = etuname.text.toString(),
            email = etuemail.text.toString(),
            city = etucity.text.toString(),
            password = etupassword.text.toString())
        check = loginDBAdapter.updateUser(user)

        if (check > 0) {
            Toast.makeText(applicationContext, "Details updated", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(applicationContext, "Cannot Update, something went wrong!!", Toast.LENGTH_LONG).show()
        }
    }
}