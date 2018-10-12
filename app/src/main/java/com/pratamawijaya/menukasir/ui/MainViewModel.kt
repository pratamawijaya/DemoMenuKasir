package com.pratamawijaya.menukasir.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.pratamawijaya.menukasir.domain.Menu
import java.math.BigDecimal

class MainViewModel : ViewModel() {

    var listSelectedMenuState = MutableLiveData<Set<Menu>>()

    var listMenuState = MutableLiveData<List<Menu>>()
    private var listSelectedMenu = mutableSetOf<Menu>()

    init {
        listSelectedMenuState.value = emptySet()
    }

    fun addMenu(menu: Menu) {
        listSelectedMenu.add(menu)
        listSelectedMenuState.value = listSelectedMenu
    }

    fun loadMenu() {
        val listMenu = listOf(
                Menu(
                        id = 1,
                        name = "Nasi Ayam",
                        price = BigDecimal(20000),
                        image = "https://previews.123rf.com/images/akulamatiau/akulamatiau1504/akulamatiau150402728/39351226-beliebte-malaysia-gericht-nasi-ayam-oder-huhn-reis-mit-gebackenen-h%C3%A4hnchenteile-tomaten-gurken-salat-s.jpg"
                ),
                Menu(
                        id = 2,
                        name = "Mie Ayam",
                        price = BigDecimal(15000),
                        image = "https://cdns.klimg.com/merdeka.com/i/w/news/2017/10/16/898237/670x335/3-resep-dan-cara-membuat-mie-ayam-sederhana-yang-enak-kln.jpg"
                ),

                Menu(
                        id = 3,
                        name = "Nasi Ayam 1",
                        price = BigDecimal(20000),
                        image = "https://previews.123rf.com/images/akulamatiau/akulamatiau1504/akulamatiau150402728/39351226-beliebte-malaysia-gericht-nasi-ayam-oder-huhn-reis-mit-gebackenen-h%C3%A4hnchenteile-tomaten-gurken-salat-s.jpg"
                ),
                Menu(
                        id = 4,
                        name = "Mie Ayam 2",
                        price = BigDecimal(15000),
                        image = "https://cdns.klimg.com/merdeka.com/i/w/news/2017/10/16/898237/670x335/3-resep-dan-cara-membuat-mie-ayam-sederhana-yang-enak-kln.jpg"
                )
        )
        listMenuState.value = listMenu
    }
}