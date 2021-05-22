package me.shuang.insomnia_server.utils

import com.google.gson.GsonBuilder

/**
 * @author shuang
 * @date 2021-05-06
 */
private val gson = GsonBuilder().setPrettyPrinting().create()

val json: (obj: Any) -> String = { gson.toJson(it) }
