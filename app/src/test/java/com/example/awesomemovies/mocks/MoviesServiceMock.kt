package com.example.awesomemovies.mocks

import com.example.awesomemovies.data.model.Movie
import com.example.awesomemovies.data.service.MovieService
import com.example.awesomemovies.data.service.response.MoviesResultResponse

class MoviesServiceMock : MovieService {

    override suspend fun discoverMovies(vote_average_min: Int?, vote_average_max: Int?): MoviesResultResponse{
        return MoviesResultResponse(1,100,5, listOf())
    }

    override suspend fun searchMovies(query: String): MoviesResultResponse{
        return MoviesResultResponse(1,100,5, listOf())
    }

    override suspend fun getMovie(id: Int): Movie{
        return Movie(170,"Title",5.0, "path", "description", "10/05/2019", listOf())
    }
}