package com.barriosartola.awesomeapp.inject

import android.preference.PreferenceManager
import com.barriosartola.awesomeapp.data.controller.AuthController
import com.barriosartola.awesomeapp.data.controller.RetrofitController
import com.barriosartola.awesomeapp.data.helper.adapter.ZonedDateTimeAdapter
import com.barriosartola.awesomeapp.data.helper.networking.NetworkingManager
import com.barriosartola.awesomeapp.data.repository.NotesSourceDataRepository
import com.barriosartola.awesomeapp.data.repository.NotesSourceRepository
import com.barriosartola.awesomeapp.data.repository.notes.NotesDataStoreFactory
import com.barriosartola.awesomeapp.data.service.AuthService
import com.barriosartola.awesomeapp.data.service.NoteService
import com.barriosartola.awesomeapp.data.source.AppDatabase
import com.barriosartola.awesomeapp.presentation.view.auth.LoginViewModel
import com.barriosartola.awesomeapp.presentation.view.home.notes.MoviesViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.threeten.bp.ZonedDateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var networkModule = module {
    single { NetworkingManager(get()) }
    single<GsonConverterFactory> {
        GsonConverterFactory.create(
            GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(ZonedDateTime::class.java, ZonedDateTimeAdapter())
                .create()
        )
    }

    single { RetrofitController(get()) }
    single<Retrofit> { get<RetrofitController>().initRetrofit() }
    single<NoteService> { get<Retrofit>().create(NoteService::class.java) }
    single<AuthService> { get<Retrofit>().create(AuthService::class.java) }
}

var databaseModule = module {
    single { AppDatabase.getInstance(get()).noteDao() }
}

var notesModule = module {
    single { NotesDataStoreFactory(get(), get(), get()) }
    single<NotesSourceRepository> { NotesSourceDataRepository(get()) }

    viewModel { MoviesViewModel(get()) }
}

var loginModule = module {
    single { AuthController(get(), get(), get()) }
    single { PreferenceManager.getDefaultSharedPreferences(get()) }

    viewModel { LoginViewModel(get()) }
}
