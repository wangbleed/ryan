package com.ryan.commons.cache;

import java.lang.annotation.*;

/**
 * Created by ryan on 2016/9/12.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Cache {

    /**
     * 需要cache的时间，单位为s
     * 0为不缓存
     */
    int time() default 0;

    /**
     * 指定cache的版本号，将拼接在key的前面
     */
    String version() default "CtripV1.0";
}
