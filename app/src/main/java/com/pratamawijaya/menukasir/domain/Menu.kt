package com.pratamawijaya.menukasir.domain

import java.math.BigDecimal

data class Menu(
        val id: Int,
        val name: String,
        val price: BigDecimal,
        val image: String
)