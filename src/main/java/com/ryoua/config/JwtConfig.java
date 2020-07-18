package com.ryoua.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/18
 **/
@ConfigurationProperties(prefix = "jwt.config")
@Data
@Component
public class JwtConfig {
    /**
     * jwt 加密 key，默认值：huaruan.
     */
    private String key = "huaruan";

    /**
     * jwt 过期时间，默认值：36000000L {@code 600 分钟}.
     */
    private Long ttl = 3600000L;

    /**
     * 开启 记住我 之后 jwt 过期时间，默认值 604800000 {@code 7 天}
     */
    private Long remember = 604800000L;
}

