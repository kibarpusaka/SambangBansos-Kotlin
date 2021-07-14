package com.example.sambang.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sambang.Login.Data.ModelLogin
import com.example.sambang.Login.Presenter.LoginPresenter
import com.example.sambang.MainActivity
import com.example.sambang.R
import com.example.sambang.SessionManager
import com.example.sambang.Utils.Base
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class Login : AppCompatActivity(), LoginView  {
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginAction()
        sessionManager = SessionManager(this)

    }

   private fun loginAction(){
        btn_login.onClick {
            val user = ModelLogin()
            user.username = in_email.text.toString()
            user.password = in_password.text.toString()

            if(user.username!!.isEmpty()){
                in_email.error = "Username required"
                in_email.requestFocus()
                return@onClick
            }
            if(user.password!!.isEmpty()){
                in_password.error = "Password required"
                in_password.requestFocus()
                return@onClick
            }

            LoginPresenter(this@Login).login(user)
        }
    }

    override fun onSuccessLogin(token: String, user: ModelLogin) {
        toast("Berhasil login").show()
        sessionManager.saveUserData(user)
        sessionManager.saveAuthToken(token)
        startActivity<MainActivity>(Base.TAGS.USER to user)
        finish()
//        startActivity<MainActivity>()
    }

    override fun onErrorLogin(msg: String?) {
        toast("Tidak Bisa Masuk").show()
    }
}