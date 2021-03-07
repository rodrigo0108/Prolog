package com.heyoh.prolog.module

import com.heyoh.appdata.data.datastore.BannerDataStore
import com.heyoh.appdata.data.datastore.BookDataStore
import com.heyoh.appdata.data.datastore.LoginDataStore
import com.heyoh.appdata.data.repository.BannerRepositoryImpl
import com.heyoh.appdata.data.repository.BookRepositoryImpl
import com.heyoh.appdata.data.repository.LoginRepositoryImpl
import com.heyoh.appdata.data.source.remote.BannerDataStoreImpl
import com.heyoh.appdata.data.source.remote.BookDataStoreImpl
import com.heyoh.appdata.data.source.remote.LoginDataStoreImpl
import com.heyoh.appdata.domain.repository.BannerRepository
import com.heyoh.appdata.domain.repository.BookRepository
import com.heyoh.appdata.domain.repository.LoginRepository
import com.heyoh.appdata.domain.usecase.GetBannerUseCase
import com.heyoh.appdata.domain.usecase.GetBooksUseCase
import com.heyoh.appdata.domain.usecase.LoginUseCase
import com.heyoh.prolog.feature.login.LoginViewModel
import com.heyoh.prolog.feature.recentbooks.RecentBookViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    //Domain
    single<LoginDataStore> { LoginDataStoreImpl() }
    single<BannerDataStore> { BannerDataStoreImpl() }
    single<BookDataStore> { BookDataStoreImpl() }

    single<LoginRepository> { LoginRepositoryImpl(get()) }
    single<BannerRepository> { BannerRepositoryImpl(get()) }
    single<BookRepository> { BookRepositoryImpl(get()) }
    //Data
    factory { LoginUseCase(get()) }
    factory { GetBannerUseCase(get()) }
    factory { GetBooksUseCase(get()) }
    //View
    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { RecentBookViewModel(get()) }
}