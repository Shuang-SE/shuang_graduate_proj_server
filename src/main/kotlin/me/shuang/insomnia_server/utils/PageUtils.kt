package me.shuang.insomnia_server.utils

import org.springframework.data.domain.Page

/**
 * @author shuang
 * @date 2021/6/2
 */
fun paginate(page: Page<*>): Map<String, Any> {
    return page.let {
        linkedMapOf(
            "content" to it.content,
            "crt_page" to it.number,
            "crt_page_size" to it.content.size,
            "page_size" to it.size,
            "total_element_size" to it.totalElements,
            "total_pages" to it.totalPages
        )
    }
}
