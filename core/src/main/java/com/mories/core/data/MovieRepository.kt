package com.mories.core.data

import com.mories.core.data.source.local.LocalDataSource
import com.mories.core.data.source.remote.RemoteDataSource
import com.mories.core.data.source.remote.network.ApiResponse
import com.mories.core.data.source.remote.response.ResponseMovie
import com.mories.core.domain.IMovieRepository
import com.mories.core.domain.model.MovieDomainModel
import com.mories.core.utils.AppExecutors
import com.mories.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getAllMovie(): Flow<Resource<List<MovieDomainModel>>> =
        object : NetworkBoundResource<List<MovieDomainModel>, List<ResponseMovie>>() {
            override fun loadFromDB(): Flow<List<MovieDomainModel>> {
                return localDataSource.getAllTourism().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<MovieDomainModel>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ResponseMovie>>> {
                return remoteDataSource.getAllFromRemote()
            }

            override suspend fun saveCallResult(data: List<ResponseMovie>) {
                val movie = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertTourism(movie)
            }

        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<MovieDomainModel>> {
        return localDataSource.getFavoriteTourism().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movieDomainModel: MovieDomainModel, state: Boolean) {
        val movieItemsEntity = DataMapper.mapDomainToEntity(movieDomainModel, state)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieItemsEntity) }
    }

}