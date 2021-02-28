package com.example.p4.ui.base

import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import butterknife.BindView
import com.example.p4.R
import com.example.p4.common.AppPreferences
import com.example.p4.ui.home.HomeActivity
import com.example.p4.ui.signin.SigninActivity
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView


abstract class BaseActivity<VM : BaseViewModel?> : AppCompatActivity() {

    // shared preferences
    private var preferences: AppPreferences? = null

    // view model
    @JvmField
    protected var viewModel: VM? = null

    // drawer
    @JvmField
    @BindView(R.id.drawer)
    var drawer: DrawerLayout? = null

    @JvmField
    @BindView(R.id.drawerUserName)
    var drawerUserName: TextView? = null

    @JvmField
    @BindView(R.id.drawerLogout)
    var drawerLogout: TextView? = null

    @JvmField
    @BindView(R.id.navigation)
    var navigation: NavigationView? = null

    init {
        // shared preferences
        preferences = AppPreferences.instance
    }

    protected abstract fun createViewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    override fun onResume() {
        super.onResume()
        setCustomActionBar()
    }

    private fun setCustomActionBar() {
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(com.example.p4.R.layout.actionbar)
        val view: View = supportActionBar!!.customView
        val toolbar: Toolbar = view.parent as Toolbar
        toolbar.setContentInsetsAbsolute(0, 0)

        toolbar.navigationIcon = null

        // back
        val back = view.findViewWithTag<ImageView>("back")

        if (isTaskRoot) {
            back.visibility = View.GONE
        }
        else {
            back.visibility = View.VISIBLE

            back.setOnClickListener {
                super.onBackPressed()
            }
        }

        // drawer and avatar
        val avatar = view.findViewWithTag<de.hdodenhof.circleimageview.CircleImageView>("avatar")
        Picasso.with(applicationContext)
                .load(preferences?.getString("userAvatar", null))
                .error(R.drawable.picture_missing_avatar)
                .into(avatar)

        avatar.setOnClickListener(View.OnClickListener {
            if (this.drawer != null) {

                if (this.drawer!!.isDrawerOpen(GravityCompat.START)) {
                    this.drawer!!.closeDrawer(GravityCompat.START)
                }
                else {
                    this.drawer!!.openDrawer(GravityCompat.START)
                }
            }
        })

        drawerUserName?.text = String.format(resources!!.getString(R.string.drawer_user_name), preferences?.getString("userName", ""))

        drawerLogout?.setOnClickListener(View.OnClickListener {
            val editor: SharedPreferences.Editor = AppPreferences.sharedPreferences!!.edit()
            editor.remove("userAvatar")
            editor.remove("userName")

            SigninActivity.start(this)
        })

        // hide bottom shadow
        supportActionBar?.elevation = 0f

    }
/*
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.drawer_menu, menu)

        // user name
        val itemUserName: MenuItem = menu.findItem(R.id.drawerUserName)
        itemUserName.title = String.format(resources!!.getString(R.string.drawer_user_name), preferences?.getString("userName", ""))
        itemUserName.setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener {
            drawer!!.closeDrawer(GravityCompat.START)
            false
        })

        // logout
        val itemLogout: MenuItem = menu.findItem(R.id.drawerLogout)
        itemLogout.setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener {
            val editor: SharedPreferences.Editor = AppPreferences.sharedPreferences!!.edit()
            editor.remove("userAvatar")
            editor.remove("userName")

            SigninActivity.start(this)
            false
        })

        return super.onPrepareOptionsMenu(menu)
    }*/
}