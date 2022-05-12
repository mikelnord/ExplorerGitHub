package com.example.explorergithub.di.module

import com.example.explorergithub.data.api.GitHubRepository
import com.example.explorergithub.model.IRepository
import com.example.explorergithub.ui.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<IRepository> { GitHubRepository() }
}

val mainViewModel = module {
    viewModel { UsersViewModel(get()) }

}