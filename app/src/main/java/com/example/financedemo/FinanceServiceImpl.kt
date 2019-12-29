package com.example.financedemo

import kotlin.math.ceil
import kotlin.math.round

class FinanceServiceImpl : FinanceService {

    init {

    }

    var number_control: Double = 0.0
    var price: Double = 0.0
    var increase: Double = 0.0
    var support: Double = 0.0
    var term: Double = 0.0
    var cover: Double = 0.0
    var ew: Double = 0.0
    var vat_total: Double = 0.0
    var year: Int = 0
    var increaseChubb: Double = 0.0

    override fun calTotalVat(): Double {
        return increase + support + term + cover + ew
    }

    override fun calMonth(): Int {
        return year * 12
    }

    override fun calTransferSec2(): Double {
        return ((price * 0.5) / 100) + 1500
    }

    override fun calPricePerMonth(): Double {
        val vat = ((price * (increase + support + term + cover + ew)) / 100) * year
        val month = year * 12
        val totalPrice = price + vat
        return totalPrice / month
    }

    override fun calPricePerMonthWithVat(): Double {
        val vat = (calPricePerMonth() * 7) / 100
        return ceil(calPricePerMonth() + vat)
    }

    override fun calPayCheck(cover: Double): Double {
        return price - cover - calTransferSec2()
    }

    override fun calCom(): Double {
        val percent = 8 * 1.0 / 100
        val increaseEwPercent = (increase + ew) * 1.0 / 100
        return price * percent * increaseEwPercent * 4
    }

    override fun calIncreaseEw(): Double {
        return increase + ew
    }

    override fun calIncreaseChubb(increase: Double): Double {
        increaseChubb = round((calPricePerMonthWithVat() * increase) / 100)
        return increaseChubb
    }

    override fun calChubbTotal(): Double {
        return calPricePerMonthWithVat() + increaseChubb
    }

    override fun calChubbLife(): Double {
        return calPricePerMonthWithVat() * calMonth()
    }

}