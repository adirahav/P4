package com.example.p4.ui.signin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import com.example.p4.R
import com.example.p4.common.Utilities
import com.example.p4.data.DataManager
import com.example.p4.data.network.model.UserModel
import com.example.p4.ui.base.BaseActivity

public class SigninActivity : BaseActivity<SigninViewModel?>() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, SigninActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(intent)
        }
    }

    // email
    @JvmField
    @BindView(R.id.email)
    var email: EditText? = null

    // email error
    @JvmField
    @BindView(R.id.emailError)
    var emailError: TextView? = null

    // password
    @JvmField
    @BindView(R.id.password)
    var password: EditText? = null

    // password error
    @JvmField
    @BindView(R.id.passwordError)
    var passwordError: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // remove action bar
        supportActionBar?.hide()

        setContentView(R.layout.activity_signin)
        ButterKnife.bind(this)

        initGlobal()
        initObserver()
    }

    public override fun onResume() {
        super.onResume()
    }

    private fun initGlobal() {

        // bind views
        ButterKnife.bind(this)
    }

    private fun initObserver() {
        viewModel!!.signinData.observe(this, SigninObserver())
    }

    override fun createViewModel(): SigninViewModel {
        val factory = SigninViewModelFactory(this@SigninActivity, DataManager.instance!!.signinService)
        return ViewModelProviders.of(this, factory).get(SigninViewModel::class.java)
    }

    // events
    fun submitForm(view: View) {
        var isValid = true

        // email
        if (!Utilities.isEmailValid(email?.text.toString())) {
            emailError?.visibility = View.VISIBLE
            isValid = false
        }
        else {
            emailError?.visibility = View.INVISIBLE
        }

        // password
        if (password?.text.toString().isEmpty()) {
            passwordError?.visibility = View.VISIBLE
            isValid = false
        }
        else {
            passwordError?.visibility = View.INVISIBLE
        }

        if (isValid) {
            viewModel!!.submitLogin(email?.text.toString(), password?.text.toString())
        }
    }

    // observers
    private inner class SigninObserver : Observer<UserModel?> {
        override fun onChanged(signinData: UserModel?) {
            if (signinData == null) {
                return
            }
        }
    }
}