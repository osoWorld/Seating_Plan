package com.example.teacherapp.splashScreen

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.teacherapp.StudentPanel.activities.StudentDashboardActivity
import com.example.teacherapp.R
import com.example.teacherapp.databinding.ActivityMySplashScreenBinding
import com.example.teacherapp.StudentPanel.activities.SignupActivity
import com.example.teacherapp.StudentPanel.activities.StudentsDashboardActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MySplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMySplashScreenBinding
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val currentUser: FirebaseUser? = auth.currentUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = resources.getColor(R.color.white)

        binding.getStarted.setOnClickListener {
//            if (currentUser != null){
//                startActivity(Intent(this, StudentsDashboardActivity::class.java))
//                finish()
//            }else
//            {
//                startActivity(Intent(this,
//                    SignupActivity::class.java))
//                finish()
//            }
            startActivity(Intent(this,SignupActivity::class.java))
        }
    }
}