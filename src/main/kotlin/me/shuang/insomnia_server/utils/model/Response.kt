package me.shuang.insomnia_server.utils.model

class Response<T> {
    var code: Long = 0

    var msg: String? = null

    var data: T? = null
}
