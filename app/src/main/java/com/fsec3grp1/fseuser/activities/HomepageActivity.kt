package com.fsec3grp1.fseuser.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fsec3grp1.fseuser.R
import com.fsec3grp1.fseuser.fragments.AboutFragment
import com.fsec3grp1.fseuser.fragments.ContactFragment
import com.fsec3grp1.fseuser.fragments.HelpFragment
import kotlinx.android.synthetic.main.activity_homepage.*
import kotlinx.android.synthetic.main.activity_homepage.tvlogin
import kotlinx.android.synthetic.main.activity_register.*

class HomepageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frag,AboutFragment.newInstance("",""))
            commit()
        }

        btabout.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frag,AboutFragment.newInstance("",""))
                commit()
                addToBackStack(null)
            }
        }

        bthelp.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frag,HelpFragment.newInstance("",""))
                commit()
                addToBackStack(null)
            }
        }

        btcontact.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frag,ContactFragment.newInstance("",""))
                commit()
                addToBackStack(null)
            }
        }

        tvlogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}