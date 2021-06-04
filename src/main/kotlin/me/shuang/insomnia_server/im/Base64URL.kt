package me.shuang.insomnia_server.im

import com.qiniu.util.Base64

/**
 * @author shuang
 * @date 2021/6/3
 */
fun base64EncodeUrl(input: ByteArray?): ByteArray {
    val base64 = String(Base64.encode(input, Base64.NO_WRAP)).toByteArray()
    for (i in base64.indices) {
        when (base64[i].toString()) {
            "+" -> base64[i] = "*".toByte()
            "/" -> base64[i] = "-".toByte()
            "=" -> base64[i] = "_".toByte()
            else -> {
            }
        }
    }
    return base64
}
