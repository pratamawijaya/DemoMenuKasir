package com.pratamawijaya.menukasir.di

import com.pratamawijaya.menukasir.ui.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    viewModel { MainViewModel() }
}