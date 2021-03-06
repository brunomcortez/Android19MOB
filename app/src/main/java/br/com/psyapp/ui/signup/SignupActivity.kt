package br.com.psyapp.ui.signup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import br.com.concrete.canarinho.watcher.TelefoneTextWatcher
import br.com.concrete.canarinho.watcher.evento.EventoDeValidacao
import br.com.psyapp.KEY_EMAIL
import br.com.psyapp.KEY_PASSWORD
import br.com.psyapp.R
import br.com.psyapp.models.NewUser
import br.com.psyapp.models.RequestState
import br.com.psyapp.ui.extensions.adjustSystemLayout
import br.com.psyapp.ui.extensions.showMessage
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.activity_signup.pb

class SignupActivity : AppCompatActivity() {

    private val signupViewModel by viewModels<SignupViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adjustSystemLayout()

        setContentView(R.layout.activity_signup)

        initView()
        registerObserver()
    }

    private fun registerObserver() {
        this.signupViewModel.signUpState.observe(this, Observer {
            when (it) {
                is RequestState.Success -> {
                    hideLoading()
                    showMessage("Usuário criado com sucesso")
                    val intent = Intent()
                    val bundle = Bundle()
                    bundle.putString(KEY_EMAIL, etEmailSignUp.text.toString())
                    bundle.putString(KEY_PASSWORD, etPasswordSignUp.text.toString())
                    intent.putExtras(bundle)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
                is RequestState.Error -> {
                    hideLoading()
                    showMessage(it.throwable.message)
                }
                is RequestState.Loading -> showLoading()
            }
        })
    }

    private fun showLoading() {
        pb?.visibility = View.VISIBLE
        btCreateAccount?.visibility = View.GONE
    }

    private fun hideLoading() {
        pb?.visibility = View.GONE
        btCreateAccount?.visibility = View.VISIBLE
    }

    private fun initView() {
        etPhoneSignUp.addTextChangedListener(TelefoneTextWatcher(object : EventoDeValidacao {
            override fun totalmenteValido(valorAtual: String?) {}
            override fun invalido(valorAtual: String?, mensagem: String?) {}
            override fun parcialmenteValido(valorAtual: String?) {}
        }))
        btCreateAccount.setOnClickListener {
            val newUser = NewUser(
                etUserNameSignUp.text.toString(),
                etEmailSignUp.text.toString(),
                etPhoneSignUp.text.toString(),
                etPasswordSignUp.text.toString()
            )
            signupViewModel.signUp(
                newUser
            )
        }

        btLoginSignUp.setOnClickListener {
            finish()
        }
    }
}
