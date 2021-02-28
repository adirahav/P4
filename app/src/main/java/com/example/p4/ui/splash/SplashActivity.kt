package com.example.p4.ui.splash

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import com.example.p4.R
import com.example.p4.data.DataManager
import com.example.p4.data.network.model.SplashModel
import com.example.p4.ui.base.BaseActivity

public class SplashActivity : BaseActivity<SplashViewModel?>() {

    // loader
    @JvmField
    @BindView(R.id.loader)
    var loader: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // remove title bar
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // remove action bar
        supportActionBar?.hide()

        setContentView(R.layout.activity_splash)
        ButterKnife.bind(this)

        initGlobal()
        initObserver()
    }

    public override fun onResume() {
        super.onResume()
        initData()
    }

    private fun initGlobal() {

        // bind views
        ButterKnife.bind(this)
    }

    private fun initObserver() {
        viewModel!!.loadingStatus.observe(this, LoadingObserver())
        viewModel!!.splashData.observe(this, SplashObserver())
    }

    private fun initData() {
        viewModel!!.getSplashData()
    }

    override fun createViewModel(): SplashViewModel {
        val factory = SplashViewModelFactory(this@SplashActivity, DataManager.instance!!.splashService)
        return ViewModelProviders.of(this, factory).get(SplashViewModel::class.java)
    }

    // observers
    private inner class LoadingObserver : Observer<Boolean?> {
        override fun onChanged(isLoading: Boolean?) {
            if (isLoading == null) {
                return
            }
            loader!!.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private inner class SplashObserver : Observer<SplashModel?> {
        override fun onChanged(splashData: SplashModel?) {
            if (splashData == null) {
                return
            }
        }
    }
}