package com.example.financedemo

class FinanceServiceImpl : FinanceService {

    var number_control: Double = 0.0
    var price: Double = 0.0
    var increase: Int = 0
    var support: Double = 0.0
    var term: Double = 0.0
    var cover: Double = 0.0
    var ew: Double = 0.0
    var vat_total: Double = 0.0
    var year: Int = 0

    override fun calTotalVat(): Double {
        return increase + support + term + cover + ew
    }

    override fun calMonth(): Int {
        return year * 12
    }
}