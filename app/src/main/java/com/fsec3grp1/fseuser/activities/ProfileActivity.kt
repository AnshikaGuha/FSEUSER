package com.fsec3grp1.fseuser.activities

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
import kotlinx.android.synthetic.main.activity_register.*

class ProfileActivity : AppCompatActivity(), View.OnClickListener   {

    private val pactivity = this@ProfileActivity
    private lateinit var loginDBAdapter:LoginDBAdapter
    var mUserName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        mUserName = intent.getStringExtra("user_name").toString()
//        val fileToRead = "LoggedInUsername"
//        this.openFileInput(fileToRead).use { stream ->
//            val text = stream.bufferedReader().use {
//                it.readLine()
//            }
//            tvwmessage.text = "Welcome $text"
//
//            Log.i("user_name",user_pname.toString())
//        }
        tvwmessage.text = "Welcome $mUserName"

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
        displaySingleuser()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
//            R.id.btregister -> displaySingleuser()
        }
    }

    private fun displaySingleuser() {
        val userToDisplay: User = loginDBAdapter.displayUser(mUserName)
        tvFullname.text = tvFullname.text.toString()+userToDisplay.name
        tvCity.text = tvCity.text.toString()+userToDisplay.city
        tvEmail.text = tvEmail.text.toString()+userToDisplay.email
    }

}