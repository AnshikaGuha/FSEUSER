package com.fsec3grp1.fseuser.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register.*
import android.content.Intent
import com.fsec3grp1.fseuser.DB.LoginDBAdapter
import com.fsec3grp1.fseuser.R
import com.fsec3grp1.fseuser.model.User

class RegisterActivity : AppCompatActivity(), View.OnClickListener  {

    private val ractivity = this@RegisterActivity
    private lateinit var loginDBAdapter:LoginDBAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btregister!!.setOnClickListener(this)
        loginDBAdapter = LoginDBAdapter(ractivity)

        tvlogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
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
            R.id.btregister -> addNewUser()
        }
    }

    private fun addNewUser() {
        var check : Int = -1

        if (etusername.text.trim().isEmpty() || etname.text.trim().isEmpty() || etemail.text.trim().isEmpty() || etcity.text.trim().isEmpty() || etpassword.text.trim().isEmpty() || etcpassword.text.trim().isEmpty()) {
            Toast.makeText(applicationContext,"Provide all inputs !", Toast.LENGTH_LONG).show()
        }
        else if(etpassword.text.toString() != etcpassword.text.toString()) {
            Toast.makeText(applicationContext,"Both passwords are not same !", Toast.LENGTH_LONG).show()
        }
        else {
            var user = User(
                username = etusername!!.text.toString().trim(),
                name = etname!!.text.toString().trim(),
                email = etemail!!.text.toString().trim(),
                city = etcity!!.text.toString().trim(),
                password = etpassword!!.text.toString().trim())
            check = loginDBAdapter.insertUser(user)
        }
        if (check != -1) {
            Toast.makeText(applicationContext, "Registered successfully !", Toast.LENGTH_LONG).show()
            etusername.setText("")
            etname.setText("")
            etemail.setText("")
            etcity.setText("")
            etpassword.setText("")
            etcpassword.setText("")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        else {
            Toast.makeText(applicationContext, "Cannot registered, something went wrong !", Toast.LENGTH_LONG).show()
        }
    }
}