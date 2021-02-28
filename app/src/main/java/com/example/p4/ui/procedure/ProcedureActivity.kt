package com.example.p4.ui.procedure

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import com.example.p4.R
import com.example.p4.common.Configuration
import com.example.p4.common.Configuration.RATING_BAR_STARS
import com.example.p4.common.Utilities
import com.example.p4.data.DataManager
import com.example.p4.data.network.model.ProcedureModel
import com.example.p4.ui.base.BaseActivity
import org.sufficientlysecure.htmltextview.HtmlResImageGetter
import org.sufficientlysecure.htmltextview.HtmlTextView
import java.text.SimpleDateFormat
import java.util.*


class ProcedureActivity : BaseActivity<ProcedureViewModel?>() {

    companion object {
        private const val EXTRA_PROCEDURE_ID = "EXTRA_PROCEDURE_ID"

        fun start(context: Context, procedureID: Int) {
            val intent = Intent(context, ProcedureActivity::class.java)
            intent.putExtra(EXTRA_PROCEDURE_ID, procedureID)
            context.startActivity(intent)
        }
    }

    // procedure id
    var procedureID: Int = 0

    // format date
    var originalDateFormat: SimpleDateFormat? = null

    // procedure name
    @JvmField
    @BindView(R.id.procedureName)
    var procedureName: TextView? = null

    // procedure preventive
    @JvmField
    @BindView(R.id.procedurePreventive)
    var procedurePreventive: TextView? = null

    // probability
    @JvmField
    @BindView(R.id.probability)
    var probability: TextView? = null

    // life potential
    @JvmField
    @BindView(R.id.lifePotential)
    var lifePotential: TextView? = null

    // personal message
    @JvmField
    @BindView(R.id.personalMessage)
    var personalMessage: TextView? = null

    // life potential progressbar
    @JvmField
    @BindView(R.id.progressbarLifePotentialContainer)
    var progressbarLifePotentialContainer: ConstraintLayout? = null

    @JvmField
    @BindView(R.id.progressbarLifePotentialComplete)
    var progressbarLifePotentialComplete: ImageView? = null

    @JvmField
    @BindView(R.id.progressbarLifePotentialMissing)
    var progressbarLifePotentialMissing: ImageView? = null

    @JvmField
    @BindView(R.id.progressbarLifePotentialGained)
    var progressbarLifePotentialGained: ImageView? = null

    @JvmField
    @BindView(R.id.progressbarLifePotentialDescription)
    var progressbarLifePotentialDescription: TextView? = null

    // risk factors
    @JvmField
    @BindView(R.id.riskFactors)
    var riskFactors: HtmlTextView? = null

    // last visit
    @JvmField
    @BindView(R.id.lastVisitDetails)
    var lastVisitDetails: TextView? = null

    @JvmField
    @BindView(R.id.lastVisitRating)
    var lastVisitRating: RatingBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_procedure)
        initGlobal()
        initData()
        initObserver()
    }

    override fun createViewModel(): ProcedureViewModel {
        //procedureID = 2 // TO DELETE - DUMMY CONTENT
        procedureID = intent.getIntExtra(EXTRA_PROCEDURE_ID, 0)

        val factory = ProcedureViewModelFactory(DataManager.instance!!.procedureService)
        return ViewModelProviders.of(this, factory).get(ProcedureViewModel::class.java)
    }

    private fun initGlobal() {

        // format date
        originalDateFormat = SimpleDateFormat(Configuration.DATETIME_ORIGINAL_PATTERN, Locale.ENGLISH)

        // bind views
        ButterKnife.bind(this)
    }

    private fun initObserver() {
        viewModel!!.procedureData.observe(this, ProcedureObserver())
    }

    private fun initData() {
        viewModel!!.getProcedure(procedureID)
    }

    private inner class ProcedureObserver : Observer<ProcedureModel?> {
        override fun onChanged(procedure: ProcedureModel?) {
            if (procedure == null) {
                return
            }

            // name
            procedureName!!.text = procedure.name

            // procedure preventive
            procedurePreventive!!.text = procedure.prevent

            // probability
            probability!!.text = String.format(resources!!.getString(R.string.procedure_probability_value), procedure.probability, procedure.probabilityScale)

            // life potential
            lifePotential!!.text = String.format(resources!!.getString(R.string.procedure_life_potential_value), procedure.lifePotential)

            // personal message
            personalMessage!!.text = procedure.personalMessage

            // life potential progressbar
            (progressbarLifePotentialComplete?.layoutParams as ConstraintLayout.LayoutParams)
                    .matchConstraintPercentWidth = (procedure.lifePotentialGoal?.div(100.0))!!.toFloat()

            (progressbarLifePotentialMissing?.layoutParams as ConstraintLayout.LayoutParams)
                    .matchConstraintPercentWidth = (procedure.lifePotential!!.div(100.0))!!.toFloat()

            (progressbarLifePotentialGained?.layoutParams as ConstraintLayout.LayoutParams)
                    .matchConstraintPercentWidth = (procedure.lifePotentialGained!!.div(100.0))!!.toFloat()

            progressbarLifePotentialDescription!!.text = String.format(
                    resources!!.getString(R.string.procedure_progressbar_life_potential_description),
                    procedure.lifePotentialGoal!!.minus(procedure.lifePotentialGained!!).minus(procedure.lifePotential!!))

            // risk factor
            var bufferRiskFactor = StringBuilder()
            bufferRiskFactor.append("<ul>")
            procedure.riskFactorList?.forEach {
                bufferRiskFactor.append("<li>${it}</li>")
            }
            bufferRiskFactor.append("</ul>")
            riskFactors?.setHtml(bufferRiskFactor.toString(), HtmlResImageGetter(this@ProcedureActivity));

            // last visit
            val visitCal = Calendar.getInstance()
            visitCal.time = originalDateFormat?.parse(procedure.lastVisit?.visitDateTime)

            lastVisitDetails?.text = String.format(
                    resources!!.getString(R.string.procedure_last_visit_details),
                    procedure.lastVisit?.doctorName,
                    DateFormat.format(Utilities.getDateTimeFormat(visitCal), visitCal.time).toString())

            // rating
            lastVisitRating?.rating = procedure.lastVisit!!.rate!!
            lastVisitRating?.numStars = RATING_BAR_STARS
        }
    }
}