package me.shuang.insomnia_server.controller

import me.shuang.insomnia_server.im.TLSSigAPIv2
import me.shuang.insomnia_server.utils.IM_SDK_APP_ID
import me.shuang.insomnia_server.utils.IM_SECRET_KEY
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * @author shuang
 * @date 2021/6/2
 */

@RestController
class IMController {

    @RequestMapping(value = ["/open/im/genUserSig/{userId}"], method = [RequestMethod.GET])
    fun getUserSig(@PathVariable userId: String): String? =
        TLSSigAPIv2(IM_SDK_APP_ID, IM_SECRET_KEY).genUserSig(userId, 86400 * 7)

}
