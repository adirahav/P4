package com.example.p4.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.p4.R
import com.example.p4.data.network.model.FamilyMemberModel
import com.squareup.picasso.Picasso
import java.util.*

class HomeFamilyMembersAdapter(
        private val context: Context) : RecyclerView.Adapter<HomeFamilyMembersAdapter.ViewHolder>() {

    private var familyMembers: List<FamilyMemberModel>

    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        // avatar
        @JvmField
        @BindView(R.id.familyMemberAvatar)
        var familyMemberAvatar: de.hdodenhof.circleimageview.CircleImageView? = null

        // name
        @JvmField
        @BindView(R.id.familyMemberName)
        var familyMemberName: TextView? = null

        // score
        @JvmField
        @BindView(R.id.familyMemberScore)
        var familyMemberScore: TextView? = null

        init {
            // bind views
            ButterKnife.bind(this, view!!)
        }
    }

    init {
        familyMembers = ArrayList()
    }

    fun setItems(familyMembers: List<FamilyMemberModel?>) {
        this.familyMembers = familyMembers as List<FamilyMemberModel>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_family_member, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val familyMember = familyMembers[position]

        // avatar
        Picasso.with(context)
                .load(familyMember.avatarURL)
                .error(R.drawable.picture_missing_avatar)
                .into(holder.familyMemberAvatar);
        /*Glide.with(context)
                .load(familyMember.avatarURL)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.familyMemberAvatar!!);*/

        // name
        holder.familyMemberName!!.text = familyMember.name

        // score
        holder.familyMemberScore!!.text = familyMember.score.toString() + "%"

    }

    override fun getItemCount(): Int {
        return familyMembers.size
    }
}