package com.test.flicker.ui.home.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.test.flicker.domain.photos.GetMoviesPhotosUseCase
import com.test.flicker.model.Photo
import retrofit2.HttpException
import java.io.IOException

class MoviesPagingResource(
    private val moviesPhotosUseCase: GetMoviesPhotosUseCase,
    private val pageCount: Int?
) : PagingSource<Int, Photo>() {
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response =
                moviesPhotosUseCase.getMoviesPhotos(
                    page = page,
                    pageCount = pageCount ?: DEFAULT_PAGE_SIZE,
                    apiKey = API_KEY
                )
            LoadResult.Page(
                response.photos.photo,
                prevKey = if (page == DEFAULT_PAGE_INDEX) null else page,
                nextKey = page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 20
        const val API_KEY = "e184e6334420e5b4021827775adcf1fa"
    }
}