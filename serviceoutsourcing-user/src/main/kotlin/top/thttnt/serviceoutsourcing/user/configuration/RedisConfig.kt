package top.thttnt.serviceoutsourcing.user.configuration

import com.alibaba.fastjson.JSON
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Configuration
@AutoConfigureAfter(RedisAutoConfiguration::class)
class RedisConfig {
    /**
     * 配置自定义redisTemplate
     * @return
     */
    @Bean
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, Any> {
        val template = RedisTemplate<String, Any>()
        //使用FastJson来序列化和反序列化redis的value值
        val serializer = FastJson2JsonRedisSerializer(Any::class.java)
        template.valueSerializer = serializer
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.keySerializer = StringRedisSerializer()
        template.hashKeySerializer = StringRedisSerializer()
        template.hashValueSerializer = serializer
        template.setConnectionFactory(redisConnectionFactory)
        template.afterPropertiesSet()
        return template
    }
}

class FastJson2JsonRedisSerializer<T>(private val clazz: Class<T>) : RedisSerializer<T> {


    override fun serialize(obj: T?): ByteArray? {
        if (obj == null) {
            return byteArrayOf()
        }

        return JSON.toJSONString(obj).toByteArray(charset("UTF-8"))
    }

    override fun deserialize(bytes: ByteArray?): T? {
        if (bytes == null || bytes.isEmpty()) {
            return null
        }

        return JSON.parseObject(String(bytes, charset("UTF-8")),clazz)
    }

}