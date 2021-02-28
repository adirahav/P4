package com.example.p4.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.p4.R
import com.example.p4.common.AppPreferences
import com.example.p4.common.AppPreferences.Companion.instance
import com.example.p4.common.Utilities
import com.example.p4.data.DataManager
import com.example.p4.data.network.model.FamilyMemberModel
import com.example.p4.data.network.model.ProcedureModel
import com.example.p4.ui.base.BaseActivity
import com.example.p4.ui.procedure.ProcedureActivity
import com.google.android.material.navigation.NavigationView

public class HomeActivity : BaseActivity<HomeFamilyMembersViewModel?>(), HomeProceduresAdapter.OnProcedureAdapter {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, HomeActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(intent)
        }
    }

    // shared preferences
    var preferences: AppPreferences? = null

    // adapter
    private var familyMembersAdapter: HomeFamilyMembersAdapter? = null
    private var proceduresAdapter: HomeProceduresAdapter? = null

    // loader
    @JvmField
    @BindView(R.id.loader)
    var loader: ProgressBar? = null

    // family members
    @JvmField
    @BindView(R.id.familyMembersList)
    var familyMembersList: RecyclerView? = null

    // procedures
    @JvmField
    @BindView(R.id.proceduresList)
    var proceduresList: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        ButterKnife.bind(this)
        initGlobal()
        initObserver()
    }

    public override fun onResume() {
        super.onResume()
        initData()
    }

    private fun initGlobal() {

        // shared preferences
        preferences = instance

    }

    private fun initObserver() {
        viewModel!!.familyMembers.observe(this, FamilyMemberObserver())
    }

    private fun initData() {
        // family members adapter
        familyMembersAdapter = HomeFamilyMembersAdapter(this@HomeActivity)
        familyMembersList!!.adapter = familyMembersAdapter
        viewModel!!.getFamilyMembers()

        // procedure adapter
        proceduresAdapter = HomeProceduresAdapter(this@HomeActivity, this)
        proceduresList!!.adapter = proceduresAdapter
    }

    override fun createViewModel(): HomeFamilyMembersViewModel {
        val factory = HomeFamilyMembersViewModelFactory(DataManager.instance!!.homeService)
        return ViewModelProviders.of(this, factory).get(HomeFamilyMembersViewModel::class.java)
    }

    override fun onProcedureClicked(procedure: ProcedureModel) {
        ProcedureActivity.start(this, procedure.procedureID!!)
    }

    // observers
    private inner class FamilyMemberObserver : Observer<List<FamilyMemberModel?>?> {
        override fun onChanged(familyMembers: List<FamilyMemberModel?>?) {
            if (familyMembers == null) {
                return
            }

            familyMembersAdapter!!.setItems(familyMembers)
            proceduresAdapter!!.setItems(familyMembers)
        }
    }
}