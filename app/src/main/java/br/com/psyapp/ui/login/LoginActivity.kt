package br.com.psyapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import br.com.psyapp.R
import br.com.psyapp.RESULT_LOGIN
import br.com.psyapp.exceptions.EmailInvalidException
import br.com.psyapp.exceptions.PasswordInvalidException
import br.com.psyapp.extensions.isValidEmail
import br.com.psyapp.models.RequestState
import br.com.psyapp.ui.extensions.showMessage
import com.google.firebase.auth.FirebaseAuth
import br.com.psyapp.ui.signup.SignupActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    override fun onBackPressed() {
    }



    private fun initView() {
        btLogin?.setOnClickListener { loginPressed() }
        tvResetPassword?.setOnClickListener { resetPasswordPressed() }
        tvNewAccount?.setOnClickListener { createAccountPressed() }

        loginViewModel.resetPasswordState.observe(this, Observer {
            when(it) {
                is RequestState.Success -> {
                    hideLoading()
                    showMessage(it.data)
                }
                is RequestState.Error -> {
                    hideLoading()
                    showError(it.throwable)
                }

                is RequestState.Loading -> showLoading()
            }
        })
        loginViewModel.loginState.observe(this, Observer {
            when (it) {
                is RequestState.Loading -> {
                    showLoading()
                }
                is RequestState.Success -> {
                    hideLoading()
                    showSuccess()
                }
                is RequestState.Error -> {
                    hideLoading()
                    showError(it.throwable)
                }
            }
        })
    }

    private fun loginPressed() {
        val email = etEmailLogin?.text?.toString() ?: ""
        val password = etPasswordLogin?.text?.toString() ?: ""
        loginViewModel.signIn(email, password)
    }

    private fun resetPasswordPressed() {
        val email = etEmailLogin?.text?.toString() ?: ""
        loginViewModel.resetPassword(email)
    }

    private fun createAccountPressed() {
        startActivity(Intent(this, SignupActivity::class.java))
    }

    private fun showSuccess() {
        finish()
    }

    private fun showError(throwable: Throwable) {
        etPasswordLogin.error = null
        etEmailLogin.error = null

        when (throwable) {
            is EmailInvalidException -> {
                etEmailLogin.error = throwable.message
                etEmailLogin.requestFocus()
            }
            is PasswordInvalidException -> {
                etPasswordLogin.error = throwable.message
                etPasswordLogin.requestFocus()
            } else -> showMessage(throwable.message)

        }
    }

    private fun showLoading() {
        pb?.visibility = View.VISIBLE
        btLogin?.visibility = View.GONE
    }

    private fun hideLoading() {
        pb?.visibility = View.GONE
        btLogin?.visibility = View.VISIBLE
    }
}
