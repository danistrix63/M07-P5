package com.example.m07_p5

import android.os.Bundle

class ConsumptionDatesActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumption_dates)

        setupBottomNavigation(R.id.bottom_navigation, R.id.nav_dates)
    }
}
