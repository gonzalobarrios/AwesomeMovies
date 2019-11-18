package com.example.awesomemovies.mocks

import com.example.awesomemovies.data.dao.GenreDao
import com.example.awesomemovies.data.model.Genre

class GenreDaoMock: GenreDao {
    private val genre = Genre(7, "El genre papa")
    override suspend fun delete(genre: Genre) {
    }

    override suspend fun get(id: Int): Genre {
        return genre
    }

    override suspend fun getAll(): List<Genre> {
        return listOf(genre)
    }

    override suspend fun getAllGenresById(ids: List<Int>): List<Genre> {
        return listOf(genre)
    }

    override suspend fun insertAll(vararg genres: Genre) {
    }
}