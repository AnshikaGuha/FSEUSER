package com.fsec3grp1.fseuser.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.fsec3grp1.fseuser.DB.LoginDBAdapter
import com.fsec3grp1.fseuser.R
import com.fsec3grp1.fseuser.User
import kotlinx.android.synthetic.main.activity_forgot.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.imlogout
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_update_profile.*

class UpdateProfileActivity : AppCompatActivity(), View.OnClickListener {

    private val uactivity = this@UpdateProfileActivity
    private lateinit var loginDBAdapter:LoginDBAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)


        fun username(){
        val fileToRead = "LoggedInUsername"
        this.openFileInput(fileToRead).use { stream ->
            val text = stream.bufferedReader().use {
                it.readLine()
            }
            tvusername.text = "Dear $text please update your Details."
        }
        }
        println(username())

        imlogout.setOnClickListener {
            val intent6 = Intent(this, LoginActivity::class.java)
            startActivity(intent6)
            finish()
        }

//        btsubmit.setOnClickListener {
//            val intent = Intent(this, ProfileActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btsubmit -> updateUser()
        }
    }

    private fun updateUser() {
        var check : Int

        if (etuname.text.trim().isNotEmpty() || etuemail.text.trim().isNotEmpty() || etucity.text.trim().isNotEmpty() || etupassword.text.trim().isNotEmpty()) {
            Toast.makeText(applicationContext, "Update atleast one Profile Information", Toast.LENGTH_SHORT).show()
        } else {
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