package com.example.financedemo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

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

        seek_year.setOnSeekBarChangeListener(seekYearChanged)
        edit_increase.addTextChangedListener(increaseSetChanged)
        edit_support.addTextChangedListener(supportSetChanged)
        edit_term.addTextChangedListener(termSetChanged)
        edit_cover.addTextChangedListener(coverSetChanged)
        edit_ew.addTextChangedListener(ewSetChanged)

    }

    private var seekYearChanged = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            try {
                p0?.let {
                    financeServiceImpl.year = it.progress
                    setYear(it.progress)
                    setMonth()
                }

            } catch (e: Exception) {}
        }
        override fun onStartTrackingTouch(p0: SeekBar?) {}
        override fun onStopTrackingTouch(p0: SeekBar?) {}
    }

    private var increaseSetChanged = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            try {
                financeServiceImpl.increase = p0.toString().toInt()
                setVatTotal(financeServiceImpl.calTotalVat())
            } catch (e: Exception) {}
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }

    private var supportSetChanged = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            try {
                financeServiceImpl.support = p0.toString().toDouble()
                setVatTotal(financeServiceImpl.calTotalVat())
            } catch (e: Exception) {}
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    }

    private var termSetChanged = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            try {
                financeServiceImpl.term = p0.toString().toDouble()
                setVatTotal(financeServiceImpl.calTotalVat())
            } catch (e: Exception) {}
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    }

    private var coverSetChanged = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            try {
                financeServiceImpl.cover = p0.toString().toDouble()
                setVatTotal(financeServiceImpl.calTotalVat())
            } catch (e: Exception) {}
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }

    private var ewSetChanged = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            try {
                financeServiceImpl.ew = p0.toString().toDouble()
                setVatTotal(financeServiceImpl.calTotalVat())
            } catch (e: Exception) {}
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }

    @SuppressLint("SetTextI18n")
    private fun setMonth() {
        txt_month.text = "${getString(R.string.month)} ${financeServiceImpl.calMonth()}"
    }

    @SuppressLint("SetTextI18n")
    private fun setVatTotal(totalVat: Double) {
        vat_total.text = totalVat.toString()
        txt_total_vat.text = "${getString(R.string.increase_total)} $totalVat"
    }

    @SuppressLint("SetTextI18n")
    private fun setYear(year: Int) {
        txt_year.text = "${getString(R.string.year)} ($year)"
    }


}
