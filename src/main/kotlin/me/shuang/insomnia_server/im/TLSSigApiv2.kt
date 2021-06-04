package me.shuang.insomnia_server.im

import org.json.JSONObject
import java.nio.charset.StandardCharsets
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.util.*
import java.util.zip.Deflater
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

/**
 * @author shuang
 * @date 2021/6/3
 */
class TLSSigAPIv2(private val sdkAppId: Long, private val key: String) {
    /**
     * 【功能说明】用于签发 TRTC 和 IM 服务中必须要使用的 UserSig 鉴权票据
     *
     *
     * 【参数说明】
     *
     * @param userid - 用户id，限制长度为32字节，只允许包含大小写英文字母（a-zA-Z）、数字（0-9）及下划线和连词符。
     * @param expire - UserSig 票据的过期时间，单位是秒，比如 86400 代表生成的 UserSig 票据在一天后就无法再使用了。
     * @return usersig -生成的签名
     */
    fun genUserSig(userid: String, expire: Long): String {
        return genUserSig(userid, expire, null)
    }

    /**
     * 【功能说明】
     * 用于签发 TRTC 进房参数中可选的 PrivateMapKey 权限票据。
     * PrivateMapKey 需要跟 UserSig 一起使用，但 PrivateMapKey 比 UserSig 有更强的权限控制能力：
     * - UserSig 只能控制某个 UserID 有无使用 TRTC 服务的权限，只要 UserSig 正确，其对应的 UserID 可以进出任意房间。
     * - PrivateMapKey 则是将 UserID 的权限控制的更加严格，包括能不能进入某个房间，能不能在该房间里上行音视频等等。
     * 如果要开启 PrivateMapKey 严格权限位校验，需要在【实时音视频控制台】/【应用管理】/【应用信息】中打开“启动权限密钥”开关。
     *
     *
     * 【参数说明】
     *
     * @param userid       - 用户id，限制长度为32字节，只允许包含大小写英文字母（a-zA-Z）、数字（0-9）及下划线和连词符。
     * @param expire       - PrivateMapKey 票据的过期时间，单位是秒，比如 86400 生成的 PrivateMapKey 票据在一天后就无法再使用了。
     * @param roomid       - 房间号，用于指定该 userid 可以进入的房间号
     * @param privilegeMap - 权限位，使用了一个字节中的 8 个比特位，分别代表八个具体的功能权限开关：
     * - 第 1 位：0000 0001 = 1，创建房间的权限
     * - 第 2 位：0000 0010 = 2，加入房间的权限
     * - 第 3 位：0000 0100 = 4，发送语音的权限
     * - 第 4 位：0000 1000 = 8，接收语音的权限
     * - 第 5 位：0001 0000 = 16，发送视频的权限
     * - 第 6 位：0010 0000 = 32，接收视频的权限
     * - 第 7 位：0100 0000 = 64，发送辅路（也就是屏幕分享）视频的权限
     * - 第 8 位：1000 0000 = 200，接收辅路（也就是屏幕分享）视频的权限
     * - privilegeMap == 1111 1111 == 255 代表该 userid 在该 roomid 房间内的所有功能权限。
     * - privilegeMap == 0010 1010 == 42  代表该 userid 拥有加入房间和接收音视频数据的权限，但不具备其他权限。
     * @return usersig - 生成带userbuf的签名
     */
    fun genPrivateMapKey(userid: String, expire: Long, roomid: Long, privilegeMap: Long): String {
        val userbuf = genUserBuf(userid, roomid, expire, privilegeMap, 0, "") //生成userbuf
        return genUserSig(userid, expire, userbuf)
    }

    /**
     * 【功能说明】
     * 用于签发 TRTC 进房参数中可选的 PrivateMapKey 权限票据。
     * PrivateMapKey 需要跟 UserSig 一起使用，但 PrivateMapKey 比 UserSig 有更强的权限控制能力：
     * - UserSig 只能控制某个 UserID 有无使用 TRTC 服务的权限，只要 UserSig 正确，其对应的 UserID 可以进出任意房间。
     * - PrivateMapKey 则是将 UserID 的权限控制的更加严格，包括能不能进入某个房间，能不能在该房间里上行音视频等等。
     * 如果要开启 PrivateMapKey 严格权限位校验，需要在【实时音视频控制台】/【应用管理】/【应用信息】中打开“启动权限密钥”开关。
     *
     *
     * 【参数说明】
     *
     * @param userid       - 用户id，限制长度为32字节，只允许包含大小写英文字母（a-zA-Z）、数字（0-9）及下划线和连词符。
     * @param expire       - PrivateMapKey 票据的过期时间，单位是秒，比如 86400 生成的 PrivateMapKey 票据在一天后就无法再使用了。
     * @param roomstr      - 字符串房间号，用于指定该 userid 可以进入的房间号
     * @param privilegeMap - 权限位，使用了一个字节中的 8 个比特位，分别代表八个具体的功能权限开关：
     * - 第 1 位：0000 0001 = 1，创建房间的权限
     * - 第 2 位：0000 0010 = 2，加入房间的权限
     * - 第 3 位：0000 0100 = 4，发送语音的权限
     * - 第 4 位：0000 1000 = 8，接收语音的权限
     * - 第 5 位：0001 0000 = 16，发送视频的权限
     * - 第 6 位：0010 0000 = 32，接收视频的权限
     * - 第 7 位：0100 0000 = 64，发送辅路（也就是屏幕分享）视频的权限
     * - 第 8 位：1000 0000 = 200，接收辅路（也就是屏幕分享）视频的权限
     * - privilegeMap == 1111 1111 == 255 代表该 userid 在该 roomid 房间内的所有功能权限。
     * - privilegeMap == 0010 1010 == 42  代表该 userid 拥有加入房间和接收音视频数据的权限，但不具备其他权限。
     * @return usersig - 生成带userbuf的签名
     */
    fun genPrivateMapKeyWithStringRoomID(userid: String, expire: Long, roomstr: String, privilegeMap: Long): String {
        val userbuf = genUserBuf(userid, 0, expire, privilegeMap, 0, roomstr) //生成userbuf
        return genUserSig(userid, expire, userbuf)
    }

    private fun hmacsha256(identifier: String, currTime: Long, expire: Long, base64Userbuf: String?): String {
        var contentToBeSigned = """
            TLS.identifier:$identifier
            TLS.sdkappid:$sdkAppId
            TLS.time:$currTime
            TLS.expire:$expire
            
            """.trimIndent()
        if (null != base64Userbuf) {
            contentToBeSigned += "TLS.userbuf:$base64Userbuf\n"
        }
        return try {
            val byteKey = key.toByteArray(StandardCharsets.UTF_8)
            val hmac = Mac.getInstance("HmacSHA256")
            val keySpec = SecretKeySpec(byteKey, "HmacSHA256")
            hmac.init(keySpec)
            val byteSig = hmac.doFinal(contentToBeSigned.toByteArray(StandardCharsets.UTF_8))
            Base64.getEncoder().encodeToString(byteSig).replace("\\s*".toRegex(), "")
        } catch (e: NoSuchAlgorithmException) {
            ""
        } catch (e: InvalidKeyException) {
            ""
        }
    }

    private fun genUserSig(userid: String, expire: Long, userbuf: ByteArray?): String {
        val currTime = System.currentTimeMillis() / 1000
        val sigDoc = JSONObject()
        sigDoc.put("TLS.ver", "2.0")
        sigDoc.put("TLS.identifier", userid)
        sigDoc.put("TLS.sdkappid", sdkAppId)
        sigDoc.put("TLS.expire", expire)
        sigDoc.put("TLS.time", currTime)
        var base64UserBuf: String? = null
        if (null != userbuf) {
            base64UserBuf = Base64.getEncoder().encodeToString(userbuf).replace("\\s*".toRegex(), "")
            sigDoc.put("TLS.userbuf", base64UserBuf)
        }
        val sig = hmacsha256(userid, currTime, expire, base64UserBuf)
        if (sig.length == 0) {
            return ""
        }
        sigDoc.put("TLS.sig", sig)
        val compressor = Deflater()
        compressor.setInput(sigDoc.toString().toByteArray(StandardCharsets.UTF_8))
        compressor.finish()
        val compressedBytes = ByteArray(2048)
        val compressedBytesLength = compressor.deflate(compressedBytes)
        compressor.end()
        return String(
            base64EncodeUrl(
                compressedBytes.copyOfRange(0, compressedBytesLength)
            )
        ).replace("\\s*".toRegex(), "")
    }

    fun genUserBuf(
        account: String, dwAuthID: Long, dwExpTime: Long,
        dwPrivilegeMap: Long, dwAccountType: Long, RoomStr: String
    ): ByteArray {
        //视频校验位需要用到的字段,按照网络字节序放入buf中
        /*
         cVer    unsigned char/1 版本号，填0
         wAccountLen unsigned short /2   第三方自己的帐号长度
         account wAccountLen 第三方自己的帐号字符
         dwSdkAppid  unsigned int/4  sdkappid
         dwAuthID    unsigned int/4  群组号码
         dwExpTime   unsigned int/4  过期时间 ，直接使用填入的值
         dwPrivilegeMap  unsigned int/4  权限位，主播0xff，观众0xab
         dwAccountType   unsigned int/4  第三方帐号类型
         */
        val accountLength = account.length
        val roomStrLength = RoomStr.length
        var offset = 0
        var bufLength = 1 + 2 + accountLength + 20
        if (roomStrLength > 0) {
            bufLength += 2 + roomStrLength
        }
        val userbuf = ByteArray(bufLength)

        //cVer
        if (roomStrLength > 0) {
            userbuf[offset++] = 1
        } else {
            userbuf[offset++] = 0
        }

        //wAccountLen
        userbuf[offset++] = (accountLength and 0xFF00 shr 8).toByte()
        userbuf[offset++] = (accountLength and 0x00FF).toByte()

        //account
        while (offset < 3 + accountLength) {
            userbuf[offset] = account[offset - 3].toByte()
            ++offset
        }

        //dwSdkAppid
        userbuf[offset++] = (sdkAppId and -0x1000000 shr 24).toByte()
        userbuf[offset++] = (sdkAppId and 0x00FF0000 shr 16).toByte()
        userbuf[offset++] = (sdkAppId and 0x0000FF00 shr 8).toByte()
        userbuf[offset++] = (sdkAppId and 0x000000FF).toByte()

        //dwAuthId,房间号
        userbuf[offset++] = (dwAuthID and -0x1000000 shr 24).toByte()
        userbuf[offset++] = (dwAuthID and 0x00FF0000 shr 16).toByte()
        userbuf[offset++] = (dwAuthID and 0x0000FF00 shr 8).toByte()
        userbuf[offset++] = (dwAuthID and 0x000000FF).toByte()

        //expire，过期时间,当前时间 + 有效期（单位：秒）
        val currTime = System.currentTimeMillis() / 1000
        val expire = currTime + dwExpTime
        userbuf[offset++] = (expire and -0x1000000 shr 24).toByte()
        userbuf[offset++] = (expire and 0x00FF0000 shr 16).toByte()
        userbuf[offset++] = (expire and 0x0000FF00 shr 8).toByte()
        userbuf[offset++] = (expire and 0x000000FF).toByte()

        //dwPrivilegeMap，权限位
        userbuf[offset++] = (dwPrivilegeMap and -0x1000000 shr 24).toByte()
        userbuf[offset++] = (dwPrivilegeMap and 0x00FF0000 shr 16).toByte()
        userbuf[offset++] = (dwPrivilegeMap and 0x0000FF00 shr 8).toByte()
        userbuf[offset++] = (dwPrivilegeMap and 0x000000FF).toByte()

        //dwAccountType，账户类型
        userbuf[offset++] = (dwAccountType and -0x1000000 shr 24).toByte()
        userbuf[offset++] = (dwAccountType and 0x00FF0000 shr 16).toByte()
        userbuf[offset++] = (dwAccountType and 0x0000FF00 shr 8).toByte()
        userbuf[offset++] = (dwAccountType and 0x000000FF).toByte()
        if (roomStrLength > 0) {
            //roomStrLen
            userbuf[offset++] = (roomStrLength and 0xFF00 shr 8).toByte()
            userbuf[offset++] = (roomStrLength and 0x00FF).toByte()

            //roomStr
            while (offset < bufLength) {
                userbuf[offset] = RoomStr[offset - (bufLength - roomStrLength)].toByte()
                ++offset
            }
        }
        return userbuf
    }

}
