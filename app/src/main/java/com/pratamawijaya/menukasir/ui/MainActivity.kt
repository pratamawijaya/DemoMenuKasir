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

    private val vm: MainViewModel by inject()

    private var menuAdapter = GroupAdapter<ViewHolder>()
    private var menuSelectedAdapter = GroupAdapter<ViewHolder>()

    val GRID_SIZE = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMenu.apply {
            adapter = menuAdapter
            layoutManager = GridLayoutManager(this@MainActivity, GRID_SIZE)
            addItemDecoration(GridItemDecoration(2, GRID_SIZE))
        }

        rvMenuSelected.apply {
            adapter = menuSelectedAdapter
            layoutManager = GridLayoutManager(this@MainActivity, GRID_SIZE)
            addItemDecoration(GridItemDecoration(2, GRID_SIZE))
        }

        vm.loadMenu()
        vm.listSelectedMenuState.observe(this, selectedMenuObserver)
        vm.listMenuState.observe(this, listMenuStateObserver)
    }

    override fun onMenuSelected(menu: Menu) {
        vm.addMenu(menu)
    }

    private val listMenuStateObserver = Observer<List<Menu>> { data ->
        data?.map {
            menuAdapter.add(MenuItem(it, this))
        }
    }

    private val selectedMenuObserver = Observer<Set<Menu>> { data ->
        menuSelectedAdapter.clear()
        data?.map {
            menuSelectedAdapter.add(MenuItem(it, null))
        }
    }
}
