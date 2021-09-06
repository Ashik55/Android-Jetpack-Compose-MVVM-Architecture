package com.eggroup.composearch.network

import com.eggroup.composearch.data.remote.Post


sealed class ApiState{

    class Success(val data:List<Post> ) : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    object Loading : ApiState()
    object Empty : ApiState()

}
