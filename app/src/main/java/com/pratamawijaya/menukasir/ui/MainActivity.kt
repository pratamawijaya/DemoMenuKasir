package com.pratamawijaya.menukasir.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.pratamawijaya.menukasir.R
import com.pratamawijaya.menukasir.domain.Menu
import com.pratamawijaya.menukasir.shared.GridItemDecoration
import com.pratamawijaya.menukasir.ui.rvitem.MenuItem
import com.pratamawijaya.menukasir.ui.rvitem.MenuListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.activity_main.rvMenu
import kotlinx.android.synthetic.main.activity_main.rvMenuSelected
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MenuListener {

    // init viewmodel dari Dependency injection KOIN
    private val vm: MainViewModel by inject()

    private var menuAdapter = GroupAdapter<ViewHolder>()
    private var menuSelectedAdapter = GroupAdapter<ViewHolder>()

    val GRID_SIZE = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setup rv untuk list menu
        rvMenu.apply {
            adapter = menuAdapter
            layoutManager = GridLayoutManager(this@MainActivity, GRID_SIZE)
            addItemDecoration(GridItemDecoration(2, GRID_SIZE))
        }

        // setup rv untuk selected menu
        rvMenuSelected.apply {
            adapter = menuSelectedAdapter
            layoutManager = GridLayoutManager(this@MainActivity, GRID_SIZE)
            addItemDecoration(GridItemDecoration(2, GRID_SIZE))
        }

        // load menu, untuk demo, menggunakan data statis, next bisa saja diganti dari database
        vm.loadMenu()

        // observe selected menu ke view model
        vm.listSelectedMenuState.observe(this, selectedMenuObserver)

        // observer list menu dari model
        vm.listMenuState.observe(this, listMenuStateObserver)
    }

    // listener ketika list menu diselec, maka menu tersebut dimasukkan kedalam Set selected menu
    override fun onMenuSelected(menu: Menu) {
        vm.addMenu(menu)
    }

    // observer untuk list menu
    private val listMenuStateObserver = Observer<List<Menu>> { data ->
        data?.map {
            menuAdapter.add(MenuItem(it, this))
        }
    }

    // observer untuk selected menu
    private val selectedMenuObserver = Observer<Set<Menu>> { data ->
        menuSelectedAdapter.clear()
        data?.map {
            menuSelectedAdapter.add(MenuItem(it, null))
        }
    }
}
