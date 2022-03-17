package com.fsec3grp1.fseuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.view.View
import kotlinx.android.synthetic.main.activity_profile.*
import android.content.Intent
import com.fsec3grp1.fseuser.DB.LoginDBAdapter
import kotlinx.android.synthetic.main.activity_register.*


class ProfileActivity : AppCompatActivity(), View.OnClickListener   {

    private val pactivity = this@ProfileActivity
    private lateinit var loginDBAdapter:LoginDBAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frag_display,WelcomeFragment.newInstance("",""))
            commit()
        }

        imlogout.setOnClickListener {
            val intent6 = Intent(this, LoginActivity::class.java)
            startActivity(intent6)
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

//    private fun displaySingleuser() {
//
//        if (etID.text.toString().isEmpty()) {
//            Toast.makeText(applicationContext, "Please enter ID", Toast.LENGTH_SHORT).show()
//        } else {
//            etPassword.setText("")
//            etUsername.setText("")
//            val userToDisplay: UserLogin = loginDBAdapter.displayUser(etID.text.toString())
//            if (userToDisplay.id == -1) {
//                Toast.makeText(applicationContext,
//                    "Details not present", Toast.LENGTH_SHORT).show()
//
//            } else {
//                etUsername.setText(userToDisplay.username)
//                etPassword.setText(userToDisplay.password)
//            }
//        }
//    }
}