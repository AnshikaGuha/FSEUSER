package com.fsec3grp1.fseuser.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_forgot.*
import android.content.Intent
import com.fsec3grp1.fseuser.DB.LoginDBAdapter
import com.fsec3grp1.fseuser.R
import com.fsec3grp1.fseuser.User
import kotlinx.android.synthetic.main.activity_forgot.tvlogin
import kotlinx.android.synthetic.main.activity_forgot.tvhomepage
import kotlinx.android.synthetic.main.activity_register.*

class ForgotActivity : AppCompatActivity(), View.OnClickListener  {

    private val fpactivity = this@ForgotActivity
    private lateinit var loginDBAdapter:LoginDBAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        btfpsubmit!!.setOnClickListener(this)
        loginDBAdapter = LoginDBAdapter(fpactivity)

        tvlogin.setOnClickListener {
            val intent5 = Intent(this, LoginActivity::class.java)
            startActivity(intent5)
            finish()
        }

        tvhomepage.setOnClickListener {
            val intent = Intent(this, HomepageActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btfpsubmit -> updateUser()
        }
    }

    private fun updateUser() {
        var check : Int

        if (etfusername.text.toString().isEmpty() || etfpassword.text.toString().isEmpty()) {
            Toast.makeText(applicationContext, "Please enter the details",Toast.LENGTH_SHORT).show()
        } else {
            var user = User(
                username = etfusername.text.toString(),
                password = etfpassword.text.toString(),
                name = etname.text.toString(),
                email = etemail.text.toString(),
                city = etcity.text.toString(),
                cpassword = etcpassword!!.text.toString()
            )
            check = loginDBAdapter.updateUser(user)

            if (check > 0) {
                Toast.makeText(applicationContext, "Details updated", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Cannot Update, something went wrong!!",Toast.LENGTH_LONG).show()
            }
        }
    }

}