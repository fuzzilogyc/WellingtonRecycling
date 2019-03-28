package org.fuzz.wellyrecycling.di

import org.fuzz.wellyrecycling.MainActivityViewModel
import org.fuzz.wellyrecycling.results.DisplayResultsViewModel
import org.fuzz.wellyrecycling.search.SearchViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val activityModule = module {
    viewModel { SearchViewModel(get()) }
    viewModel { DisplayResultsViewModel(get()) }
    viewModel { MainActivityViewModel(get()) }
}