package me.shuang.insomnia_server.utils

import me.shuang.insomnia_server.utils.model.Response

/**
 * 对接口进行统一格式管理的工具类
 * 格式:
 * 接口无错误，返回: {
 * code: 大于等于0的整数,
 * data: {
 * ... // 接口返回的数据
 * }
 * [, extra: "额外信息，非必需"]
 * }
 * <p>
 * 接口发生错误时，返回: {
 * code: 小于0的整数,
 * msg: "错误信息"
 * }
 */

fun error(msg: String?) = Response<Any>().apply {
    this.code = -1
    this.msg = msg
}

fun <T> success(data: T) = Response<T>().apply {
    this.code = 0
    this.data = data
}

fun errorToJson(msg: String) = json(error(msg))

fun <T> successToJson(data: T) = json(success(data))
