package com.example.sambang.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.sambang.ui.main.MainActivity
import com.example.sambang.R
import com.example.sambang.data.login.remote.dto.LoginRequest
import com.example.sambang.databinding.ActivityLoginBinding
import com.example.sambang.ui.common.extension.makeAlert
import com.example.sambang.ui.common.extension.makeToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observe()
        login()
        isLoggedIn()

    }

    private fun isLoggedIn(){
        if(viewModel.isLoggedIn()){
            goToMainActivity()
        }
    }

    private fun observe(){
        viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleState(it) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state : LoginActivityState){
        when(state){
            is LoginActivityState.OnSuccessLogin -> {
                makeToast("Selamat datang ${state.loginEntity.username}")
                goToMainActivity()
            }
            is LoginActivityState.ShowToast -> makeAlert(state.message)
            is LoginActivityState.IsLoading -> handleLoading(state.isLoading)
            is LoginActivityState.Init -> Unit
        }
    }

    private fun handleLoading(isLoading : Boolean){
        binding.btnLogin.isEnabled = !isLoading
        binding.btnLogin.text = if(isLoading) "Tunggu sebentar..." else "Login"
    }

    private fun goToMainActivity(){
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }

    private fun login(){
        binding.btnLogin.setOnClickListener {
            val username = binding.inEmail.text.toString().trim()
            val password = binding.inPassword.text.toString().trim()
            if(validate(username, password)){
                viewModel.login(LoginRequest(username, password))
            }
        }
    }

    private fun validate(username: String, password: String) : Boolean {
        if (username.isEmpty()){
            binding.inEmail.error = getString(R.string.validator_username)
            binding.inEmail.requestFocus()
            return false
        }

        if(password.isEmpty()){
            binding.inPassword.error = getString(R.string.validator_password)
            binding.inPassword.requestFocus()
            return false
        }

        return true
    }

}