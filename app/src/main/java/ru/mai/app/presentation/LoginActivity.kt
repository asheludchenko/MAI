package ru.mai.app.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import ru.mai.app.IS_STUDENT_KEY
import ru.mai.app.R
import ru.mai.app.SP_KEY

/**
 * Created by olegosipenko on 21.09.15.
 */
public class LoginActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_student.setOnClickListener {
            View ->
            savePerson()
        }
        btn_abitur.setOnClickListener {
            View ->
            savePerson(false)
        }
        if (getSharedPreferences(SP_KEY, Context.MODE_PRIVATE).contains(IS_STUDENT_KEY)) {
            startMainActivity()
        }
    }

    fun savePerson(isStudent: Boolean = true) {
        getSharedPreferences(SP_KEY, Context.MODE_PRIVATE).edit().putBoolean(IS_STUDENT_KEY, isStudent).apply()
        startMainActivity()
    }

    fun startMainActivity() {
        val startIntent: Intent = Intent(this, MainActivity :: class.java)
        startActivity(startIntent)
    }
}