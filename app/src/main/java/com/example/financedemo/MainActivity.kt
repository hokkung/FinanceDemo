package com.example.financedemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.section_1.*
import kotlinx.android.synthetic.main.section_2.*
import kotlinx.android.synthetic.main.section_3.*
import kotlinx.android.synthetic.main.section_4.*
import kotlinx.android.synthetic.main.section_5.*

class MainActivity : AppCompatActivity() {

    private var financeServiceImpl: FinanceServiceImpl = FinanceServiceImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        bindView()
    }

    private fun bindView() {
        edit_price.addTextChangedListener(editPriceChanged)
        seek_year.setOnSeekBarChangeListener(seekYearChanged)
        edit_increase.addTextChangedListener(increaseSetChanged)
        edit_support.addTextChangedListener(supportSetChanged)
        edit_term.addTextChangedListener(termSetChanged)
        edit_cover.addTextChangedListener(coverSetChanged)
        edit_ew.addTextChangedListener(ewSetChanged)
        sec2_edit_cover.addTextChangedListener(sec2Cover)
        sec4_edit_increase.addTextChangedListener(increaseChubb)
    }

    private var increaseChubb = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            try {
                p0?.let {
                    sec4_increase.text = financeServiceImpl.calIncreaseChubb(p0.toString().toDouble()).toString()
                    sec4_total.text = financeServiceImpl.calChubbTotal().toString()
                }
            } catch (e: Exception) {}
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    }

    private var sec2Cover = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            try{
                p0?.let {
                    val cover = p0.toString().toDouble()
                    total_sec2.text = financeServiceImpl.calPayCheck(cover).toString()
                }
            } catch (e: Exception) {}
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    }

    private var editPriceChanged = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            try {
                p0?.let {
                    val text = p0.toString()
                    financeServiceImpl.price = text.toDouble()
                    sec2_price.text = text
                    sec3_price.text = text
                    sec5_price.text = text
                    sec2_edit_transfer.setText(financeServiceImpl.calTransferSec2().toString())
                    setCom()
                }
            } catch (e: Exception) {}
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    }

    private var seekYearChanged = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {}
        override fun onStartTrackingTouch(p0: SeekBar?) {}
        override fun onStopTrackingTouch(p0: SeekBar?) {
            try {
                p0?.let {
                    financeServiceImpl.year = it.progress
                    setYear(it.progress)
                    setMonth()
                    setPricePerMonth(financeServiceImpl.calPricePerMonth())
                    setPricePerMonthWithVat(financeServiceImpl.calPricePerMonthWithVat())
                    setChubbLife()
                }

            } catch (e: Exception) {}
        }
    }

    private var increaseSetChanged = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            try {
                p0?.let {
                    val increase = if (it.toString() != "") it.toString().toDouble() else 0.0
                    financeServiceImpl.increase = increase
                    setIncreaseEwSec3()
                    setVatTotal(financeServiceImpl.calTotalVat())
                    setCom()
                }
            } catch (e: Exception) {}
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }

    private var supportSetChanged = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            try {
                p0?.let {
                    val sp = if (it.toString() != "") it.toString().toDouble() else 0.0
                    financeServiceImpl.support = sp
                    setVatTotal(financeServiceImpl.calTotalVat())
                }
            } catch (e: Exception) {}
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    }

    private var termSetChanged = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            try {
                p0?.let {
                    val term = if (it.toString() != "") it.toString().toDouble() else 0.0
                    financeServiceImpl.term = term
                    setVatTotal(financeServiceImpl.calTotalVat())
                }
            } catch (e: Exception) {}
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    }

    private var coverSetChanged = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            try {
                p0?.let {
                    val cover = if (it.toString() != "") it.toString().toDouble() else 0.0
                    financeServiceImpl.cover = cover
                    setVatTotal(financeServiceImpl.calTotalVat())
                }

            } catch (e: Exception) {}
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }

    private var ewSetChanged = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            try {
                p0?.let {
                    val ew = if (it.toString() != "") it.toString().toDouble() else 0.0
                    financeServiceImpl.ew = ew
                    setIncreaseEwSec3()
                    setVatTotal(financeServiceImpl.calTotalVat())
                    setCom()
                }
            } catch (e: Exception) {}
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }

    @SuppressLint("SetTextI18n")
    private fun setMonth() {
        txt_month.text = financeServiceImpl.calMonth().toString()
        sec4_count_per_month.text = financeServiceImpl.calMonth().toString()
        sec5_month.text = financeServiceImpl.calMonth().toString()
    }

    @SuppressLint("SetTextI18n")
    private fun setVatTotal(totalVat: Double) {
        vat_total.text = totalVat.toString()
        txt_total_vat.text = totalVat.toString()
        sec5_increase.text = totalVat.toString()
    }

    @SuppressLint("SetTextI18n")
    private fun setYear(year: Int) {
        txt_year.text = "${getString(R.string.year)} $year"
    }

    @SuppressLint("SetTextI18n")
    private fun setPricePerMonth(price: Double) {
        txt_per_month.text = price.toString()
    }

    @SuppressLint("SetTextI18n")
    private fun setPricePerMonthWithVat(price: Double) {
        txt_per_month_total_vat.text = price.toString()
        sec4_per_month_total_vat.text = price.toString()
        sec5_per_month_total_vat.text = price.toString()
    }

    private fun setCom() {
        sec3_total_price.text = financeServiceImpl.calCom().toString()
    }

    private fun setIncreaseEwSec3() {
        sec3_increase.text = financeServiceImpl.calIncreaseEw().toString()
    }

    private fun setChubbLife() {
        sec5_total_price.text = financeServiceImpl.calChubbLife().toString()
    }

}
