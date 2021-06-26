package com.example.sambang.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sambang.Api.SambangUtils
import com.example.sambang.Utils.Base
import com.example.sambang.Login.Data.ModelLogin
import com.example.sambang.Login.Data.ResponLogin
import com.example.sambang.Login.Presenter.LoginPresenter
import com.example.sambang.MainActivity
import com.example.sambang.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity(), LoginView  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        btn_login.setOnClickListener {
//            startActivity(Intent(this, MainActivity::class.java))
//        }

//        btn_login.setOnClickListener {
//            val username = in_email.text.toString().trim()
//            val password = in_password.text.toString().trim()
//
//            if(username.isEmpty()){
//                in_email.error = "Email required"
//                in_email.requestFocus()
//                return@setOnClickListener
//            }
//            if(password.isEmpty()){
//                in_password.error = "Password required"
//                in_password.requestFocus()
//                return@setOnClickListener
//            }
//
//            SambangUtils.service()
//                .loginUser(username, password)
//                .enqueue(object : Callback<ResponLogin>{
//                    override fun onResponse(
//                        call: Call<ResponLogin>,
//                        response: Response<ResponLogin>
//                    ) {
//                        if (response.body()?.status!!){
//                            val intentLogin = Intent(applicationContext, MainActivity::class.java)
//                            startActivity(intentLogin)
//                        }else {
//                            Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
//                        }
//                    }
//
//                    override fun onFailure(call: Call<ResponLogin>, t: Throwable) {
//                        TODO("Not yet implemented")
//                    }
//
//                })
//        }
        loginAction()
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

    override fun onSuccessLogin(user: ModelLogin?) {
        toast("Berhasil login").show()

//        startActivity<MainActivity>(Base.TAGS.USER to user)
        startActivity<MainActivity>()
    }

    override fun onErrorLogin(msg: String?) {
        toast(msg?:"").show()
    }
}