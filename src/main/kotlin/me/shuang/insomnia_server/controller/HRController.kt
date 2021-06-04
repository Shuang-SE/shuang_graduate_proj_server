package me.shuang.insomnia_server.controller

import me.shuang.insomnia_server.bean.HumanResource
import me.shuang.insomnia_server.service.HRService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RestController

/**
 * @author shuang
 * @date 2021/6/4
 */
@RestController
class HRController {

    @Autowired
    private lateinit var hrService: HRService

    @RequestMapping(value = ["/open/hr/{id}"], method = [GET])
    fun getOne(@PathVariable id: Int) = hrService.getById(id)

    @RequestMapping(value = ["/open/hr/companyId/{companyId}"], method = [GET])
    fun getAllByCompanyId(@PathVariable companyId: Int): MutableList<HumanResource> = hrService.getAllByCompanyId(companyId)
}
