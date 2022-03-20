package com.fsec3grp1.fseuser.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_profile.*
import android.content.Intent
import android.widget.Toast
import com.fsec3grp1.fseuser.DB.LoginDBAdapter
import com.fsec3grp1.fseuser.R
import com.fsec3grp1.fseuser.User
import kotlinx.android.synthetic.main.activity_register.*

class ProfileActivity : AppCompatActivity(), View.OnClickListener   {

    private val pactivity = this@ProfileActivity
    private lateinit var loginDBAdapter:LoginDBAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val fileToRead = "LoggedInUsername"
        this.openFileInput(fileToRead).use { stream ->
            val text = stream.bufferedReader().use {
                it.readLine()
            }
            tvwmessage.text = "Welcome $text"
        }

        imlogout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        btupdate.setOnClickListener {
            val intent = Intent(this, UpdateProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

        tvprofile!!.setOnClickListener(this)
        loginDBAdapter = LoginDBAdapter(pactivity)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
//            R.id.btregister -> displaySingleuser()
        }
    }

    private fun displaySingleuser() {

        if (etusername.text.toString().isEmpty()) {
            Toast.makeText(applicationContext, "Please enter ID", Toast.LENGTH_SHORT).show()
        } else {
            etpassword.setText("")
            etusername.setText("")

            //Harika we cannot display all info in single tv
            val userToDisplay: User = loginDBAdapter.displayUser(etusername.text.toString())
            if (userToDisplay.id == -1) {
                Toast.makeText(applicationContext,
                    "Details not present", Toast.LENGTH_SHORT).show()

            } else {
                etusername.setText(userToDisplay.username)
                etpassword.setText(userToDisplay.password)
            }
        }
    }
}