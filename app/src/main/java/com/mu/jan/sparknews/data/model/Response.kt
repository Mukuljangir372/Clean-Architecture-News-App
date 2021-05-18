package com.mu.jan.sparknews.data.model

/**
 * <Out T> means Response class extends T
 */
data class Response<out T >(val status: Status,
                            val data: T? = null,
                            val message: String? = null){
    companion object{
        fun error(message: String?) = Response(Status.ERROR,null,message)
        fun loading() = Response(Status.LOADING,null,null)
        fun <T> success(data: T?) = Response(Status.SUCCESS,data,"success")
    }
}
enum class Status{
    ERROR,
    SUCCESS,
    LOADING
}