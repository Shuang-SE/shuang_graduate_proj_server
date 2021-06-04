package me.shuang.insomnia_server.utils

import com.google.gson.GsonBuilder
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import java.sql.Timestamp

/**
 * @author shuang
 * @date 2021-05-06
 */
private val TIMESTAMP_SERIALIZER: JsonSerializer<Timestamp> =
    JsonSerializer { src, _, _ -> JsonPrimitive(src.time) }

private val GSON = GsonBuilder()
    .setPrettyPrinting()
    .registerTypeAdapter(Timestamp::class.java, TIMESTAMP_SERIALIZER)
    .create()

val json: (obj: Any) -> String = { GSON.toJson(it) }
