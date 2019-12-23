package top.thttnt.serviceoutsourcing.common.interceptor

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.*
import kotlin.collections.LinkedHashMap

class HeaderInterceptor : RequestInterceptor{

    override fun apply(template: RequestTemplate) {
        val request = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
        val map = LinkedHashMap<String,String>()
        val enumeration = request.headerNames
        while (enumeration.hasMoreElements()){
            val key = enumeration.nextElement();
            val value = request.getHeader(key);
            template.header(key,value)
        }
    }

}