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

import com.barriosartola.awesomeapp.presentation.view.home.movies.MoviesViewModel

import com.barriosartola.awesomeapp.presentation.view.home.notes.NotesViewModel
import com.example.awesomemovies.data.repository.GenreSourceDataRepository
import com.example.awesomemovies.data.repository.MoviesSourceDataRepository
import com.example.awesomemovies.data.repository.MoviesSourceRepository
import com.example.awesomemovies.data.repository.genres.CloudGenreDataStore
import com.example.awesomemovies.data.repository.genres.DatabaseGenreDataStore
import com.example.awesomemovies.data.repository.movies.DatabaseMovieGenreDataStore
import com.example.awesomemovies.data.repository.movies.MoviesDataStoreFactory
import com.example.awesomemovies.data.service.GenreService
import com.example.awesomemovies.data.service.MovieService
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
//    single<NoteService> { get<Retrofit>().create(NoteService::class.java) }
    single<MovieService> { get<Retrofit>().create(MovieService::class.java) }
    single<AuthService> { get<Retrofit>().create(AuthService::class.java) }
    single<GenreService> { get<Retrofit>().create(GenreService::class.java)}

}

var databaseModule = module {
    single {AppDatabase.getInstance(get()).movieDao()}
    single {AppDatabase.getInstance(get()).genreDao()}
    single {AppDatabase.getInstance(get()).movieGenreJoinDao()}
}

var moviesModule = module {
    single { MoviesDataStoreFactory(get(), get(), get(), get()) }
    single<MoviesSourceRepository> { MoviesSourceDataRepository(get()) }
    viewModel { MoviesViewModel(get()) }
}

var movieGenreModule = module {
    single { DatabaseMovieGenreDataStore(get()) }
}

var genreModule = module {
    single { DatabaseGenreDataStore(get()) }
    single {GenreSourceDataRepository(get())}
    single { CloudGenreDataStore(get(), get())}
}

var loginModule = module {
    single { AuthController(get(), get(), get()) }
    single { PreferenceManager.getDefaultSharedPreferences(get()) }
    viewModel { LoginViewModel(get()) }
}
