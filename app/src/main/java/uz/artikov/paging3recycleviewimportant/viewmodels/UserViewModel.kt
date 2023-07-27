package uz.artikov.paging3recycleviewimportant.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import uz.artikov.paging3recycleviewimportant.retrofit.ApiClient

class UserViewModel : ViewModel() {

    val liveData = Pager(PagingConfig(pageSize = 2)) {
        UserDataSource(ApiClient.apiService)
    }.flow.cachedIn(viewModelScope).asLiveData()

}