package me.shuang.insomnia_server.utils

import org.apache.logging.log4j.util.Strings
import org.springframework.util.DigestUtils

fun md5Encrypt(input: String?) = if (Strings.isNotEmpty(input)) {
    DigestUtils.md5DigestAsHex(input!!.encodeToByteArray())
} else {
    null
}
