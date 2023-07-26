package uz.artikov.paging3recycleviewimportant.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import uz.artikov.paging3recycleviewimportant.models.UserData

interface ApiService {

    @GET("users")
    fun getUsers(@Query("page") page: Int): Call<UserData>

}