package com.example.financedemo


import org.hamcrest.CoreMatchers
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class FinanceServiceImplTest {
    private lateinit var financeService: FinanceServiceImpl

    @Before
    fun setUp() {
        financeService = FinanceServiceImpl()
    }

    @Test
    fun calTotalVat() {
        assertThat(financeService.calTotalVat(), CoreMatchers.equalTo(0.0))
        financeService.increase = 6.6
        financeService.support = 1.0
        financeService.term = 1.0
        financeService.cover = 1.0
        financeService.ew = 1.0
        assertThat(financeService.calTotalVat(), CoreMatchers.equalTo(10.6))
    }

    @Test
    fun calMonth() {
        assertThat(financeService.calMonth(), CoreMatchers.equalTo(0))
        financeService.year = 7
        assertThat(financeService.calMonth(), CoreMatchers.equalTo(84))
    }

    @Test
    fun calTransferSec2() {

    }

    @Test
    fun calPricePerMonth() {
    }

    @Test
    fun calPricePerMonthWithVat() {
    }

    @Test
    fun calPayCheck() {
    }

    @Test
    fun calCom() {
    }

    @Test
    fun calIncreaseEw() {
    }

    @Test
    fun calIncreaseChubb() {
    }

    @Test
    fun calChubbTotal() {
    }

    @Test
    fun calChubbLife() {
    }
}