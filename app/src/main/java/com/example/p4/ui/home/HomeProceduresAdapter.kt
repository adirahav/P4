package com.example.p4.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.p4.R
import com.example.p4.data.network.model.FamilyMemberModel
import com.example.p4.data.network.model.ProcedureModel
import com.squareup.picasso.Picasso
import java.util.*

class HomeProceduresAdapter(
        private val context: Context,
        private val listener: OnProcedureAdapter) : RecyclerView.Adapter<HomeProceduresAdapter.ViewHolder>() {

    private var procedures: List<FamilyMemberModel>

    interface OnProcedureAdapter {
        fun onProcedureClicked(procedure: ProcedureModel)
    }

    inner class ViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        // container
        @JvmField
        @BindView(R.id.procedureContainer)
        var procedureContainer: CardView? = null

        // avatar
        @JvmField
        @BindView(R.id.procedureAvatar)
        var procedureAvatar: de.hdodenhof.circleimageview.CircleImageView? = null

        // name
        @JvmField
        @BindView(R.id.procedureName)
        var procedureName: TextView? = null

        // score
        @JvmField
        @BindView(R.id.procedureValue)
        var procedureValue: TextView? = null

        init {
            // bind views
            ButterKnife.bind(this, view!!)
        }
    }

    init {
        procedures = ArrayList()
    }

    fun setItems(procedures: List<FamilyMemberModel?>) {
        this.procedures = procedures as List<FamilyMemberModel>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_procedure, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val procedure = procedures[position]

        // container
        holder.procedureContainer!!.setOnClickListener {
            if (procedure.nextProcedure != null && procedure.nextProcedure!!.size > 0) {
                var nextProcedure = procedure.nextProcedure!!.get(0)
                nextProcedure.let { it -> listener.onProcedureClicked(it!!) }
            }
        }

        // avatar
        Picasso.with(context).load(procedure.avatarURL).into(holder.procedureAvatar);

        // name
        holder.procedureName!!.text = procedure.nextProcedure?.get(0)?.name

        // value
        holder.procedureValue!!.text = procedure.nextProcedure?.get(0)?.value.toString() + "%"

    }

    override fun getItemCount(): Int {
        return procedures.size
    }
}