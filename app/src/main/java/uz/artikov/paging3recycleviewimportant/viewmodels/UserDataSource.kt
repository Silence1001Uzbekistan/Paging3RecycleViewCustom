package uz.artikov.paging3recycleviewimportant.viewmodels

import androidx.paging.PagingSource
import androidx.paging.PagingState
import uz.artikov.paging3recycleviewimportant.models.Data
import uz.artikov.paging3recycleviewimportant.retrofit.ApiService

class UserDataSource(val apiService: ApiService) : PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {

        return state.anchorPosition

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {

        try {

            val nextPageNumber = params.key ?: 1
            val userData = apiService.getUsers2(nextPageNumber)
            return LoadResult.Page(userData.data, null, nextPageNumber + 1)

        } catch (e: Exception) {

            return LoadResult.Error(e)

        }

    }
}