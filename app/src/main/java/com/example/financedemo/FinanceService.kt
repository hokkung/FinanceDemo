package com.example.financedemo

interface FinanceService {
    fun calTotalVat(): Double
    fun calMonth(): Int
    fun calTransferSec2() : Double
    fun calPricePerMonth(): Double
    fun calPricePerMonthWithVat(): Double
    fun calPayCheck(cover: Double): Double
    fun calCom(): Double
    fun calIncreaseEw(): Double
    fun calIncreaseChubb(increase: Double): Double
    fun calChubbTotal(): Double
    fun calChubbLife(): Double
}