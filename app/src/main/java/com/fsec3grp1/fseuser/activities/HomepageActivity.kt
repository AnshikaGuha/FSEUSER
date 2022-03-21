package com.fsec3grp1.fseuser.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.fsec3grp1.fseuser.R
import com.fsec3grp1.fseuser.fragments.AboutFragment
import com.fsec3grp1.fseuser.fragments.ContactFragment
import com.fsec3grp1.fseuser.fragments.HelpFragment
import kotlinx.android.synthetic.main.activity_homepage.*
import com.fsec3grp1.fseuser.utils.Constants

class HomepageActivity : AppCompatActivity() {
    var mUserName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        try {
            this.openFileInput(Constants.LOGGED_IN_USER_FILENAME).use { stream ->
                mUserName = stream?.bufferedReader().use {
                    it?.readLine() ?: ""
                }
                tvwmessage.text = "Welcome $mUserName"
                Log.i("username: ", mUserName)
            }
        }
        catch(e: java.io.FileNotFoundException) {
            Log.e("File not found: ", e.toString())
        }

        if(mUserName.isEmpty()) {
            btlogin.visibility = View.VISIBLE
            btprofile.visibility = View.INVISIBLE
            btregister.visibility = View.VISIBLE
            btlogout.visibility = View.INVISIBLE
        }
        else {
            btlogin.visibility = View.INVISIBLE
            btprofile.visibility = View.VISIBLE
            btregister.visibility = View.INVISIBLE
            btlogout.visibility = View.VISIBLE
        }

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frag,AboutFragment.newInstance("",""))
            commit()
        }

        btabout.setOnClickListener {
            ivhomeicon.setBackgroundResource(R.drawable.bitslogo)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frag,AboutFragment.newInstance("",""))
                commit()
                addToBackStack(null)
            }
        }

        bthelp.setOnClickListener {
            ivhomeicon.setBackgroundResource(R.drawable.fseteam)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frag,HelpFragment.newInstance("",""))
                commit()
                addToBackStack(null)
            }
        }

        btcontact.setOnClickListener {
            ivhomeicon.setBackgroundResource(R.drawable.contactfseteam)
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frag,ContactFragment.newInstance("",""))
                commit()
                addToBackStack(null)
            }
        }

        btlogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        btprofile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

        btregister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        btlogout.setOnClickListener {
            val textToWrite = ""
            this.openFileOutput(Constants.LOGGED_IN_USER_FILENAME, Context.MODE_PRIVATE).
            use { output ->
                output.write(textToWrite.toByteArray())
            }

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}