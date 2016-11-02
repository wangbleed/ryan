package com.ryan.commons.cache;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.annotation.AnnotationUtils;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


/**
 * 控制cache注解生效的切面/**
 * Created by xudongwang on 2016/9/12.
 */
@Aspect
public class CacheServiceAspect {

    private static final Logger logger = LoggerFactory.getLogger(CacheServiceAspect.class);

    private static final String CACHE_KEY_SUFFIX = ".end";

    private RedisClusterClient redisClusterClient;

    public void setRedisClusterClient(RedisClusterClient redisClusterClient) {
        this.redisClusterClient = redisClusterClient;
    }

    @Pointcut("@annotation(Cache)")
    public void cachePoint() {
    }

    @Around("cachePoint()")
    public Object cache(final ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        Cache cache = findAnnotation(method, point.getTarget(), Cache.class);
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        Object[] arguments = point.getArgs();
        Class<?>[] paramTypes = method.getParameterTypes();

        if (cache == null || cache.time() <= 0 || StringUtils.isEmpty(cache.version())) {
            return point.proceed();
        }

        String key = getCacheKey(cache.version(), className, methodName, arguments, paramTypes);
        logger.info("cache key:{}", key);
        Object value = redisClusterClient.getObjectFromRedis(key);

        if (null == value) {
            value = point.proceed();
            if(value instanceof Serializable)
                redisClusterClient.persitenceObject(key, cache.time(), value);
            else
                logger.error("This bean {} does not implement the Serializable ", value != null ? value.getClass().getSimpleName() : "");
        }
        return value;
    }

    private String getCacheKey(String version, String className, String methodName, Object[] arguments, Class<?>[] paramTypes) {
        StringBuilder builder = new StringBuilder(256);
        builder.append(version).append(".");
        builder.append(className).append(".");
        builder.append(methodName).append(".");
        String paramStr = getSignature(paramTypes);
        if (StringUtils.isNotEmpty(paramStr)) {
            builder.append(paramStr).append(".");
        }
        String argValues = getArgumentValues(arguments);
        if (StringUtils.isNotEmpty(argValues)) {
            builder.append(argValues);
        }
        builder.append(CACHE_KEY_SUFFIX);
        return builder.toString();
    }

    private String getArgumentValues(Object[] arguments) {
        if (arguments == null || arguments.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(32);
        int i = 0;
        for (Object object : arguments) {
            ++i;
            sb.append(i);
            sb.append("-");
            if (object == null) {
                continue;
            }
            sb.append(object.toString());
        }
        return sb.toString();
    }

    private String getSignature(Class<?>[] parameterTypes) {
        if (parameterTypes == null || parameterTypes.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(32);
        if (parameterTypes.length > 0) {
            boolean first = true;
            for (Class<?> type : parameterTypes) {
                if (first) {
                    first = false;
                } else {
                    sb.append(".");
                }
                sb.append(type.getSimpleName());
            }
        }
        return sb.toString();
    }

    private <A extends Annotation> A findAnnotation(Method method, Object target, Class<A> annotationType) {
        A annotation = AnnotationUtils.findAnnotation(method, annotationType);
        if (annotation == null) {
            annotation = AnnotationUtils.findAnnotation(AopUtils.getMostSpecificMethod(method, target.getClass()), annotationType);
        }
        return annotation;
    }

}
