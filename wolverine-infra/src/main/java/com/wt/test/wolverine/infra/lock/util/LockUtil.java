package com.wt.test.wolverine.infra.lock.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 分布式锁工具类，Redission实现
 *
 * @author qiyu
 * @since 2023/7/3
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class LockUtil {
    
    private final RedissonClient redissonClient;
    
    private static final long DEFAULT_LOCK_LEASE_TIME = -1L;
    
    private static final TimeUnit DEFAULT_LOCK_LEASE_TIME_UNIT = TimeUnit.SECONDS;
    
    
    /**
     * @param lockName
     * @param function
     * @param param
     * @return R
     * @throws
     */
    public <T, R> R lockAndExecute(String lockName, Function<T, R> function, T param) throws RuntimeException {
        return lockAndExecute(lockName, function, param, DEFAULT_LOCK_LEASE_TIME, DEFAULT_LOCK_LEASE_TIME_UNIT);
    }
    
    public <T, R> R lockAndExecute(String lockName, Function<T, R> function, T param, long leaseTime, TimeUnit timeUnit) throws RuntimeException {
        RLock rLock = getLock(lockName);
        if (Objects.isNull(rLock)) {
            String msg = String.format("获取RedissionLock异常,lockName:%s", lockName);
            log.error(msg);
            throw new RuntimeException(msg);
        }
        boolean lockSuccess = false;
        try {
            lockSuccess = rLock.tryLock(leaseTime, timeUnit);
            if (lockSuccess) {
                return function.apply(param);
            } else {
                String msg = String.format("LockUtil获取锁异常,lockName:%s", lockName);
                log.error(msg);
                throw new RuntimeException(msg);
            }
        } catch (InterruptedException e) {
            String msg = String.format("LockUtil#lockAndExecute异常,lockName:%s", lockName);
            log.error(msg, e);
            throw new RuntimeException(msg);
        } finally {
            if (lockSuccess) {
                unlock(rLock);
            }
        }
    }
    
    public RLock getLock(String lockName) {
        return redissonClient.getLock(lockName);
    }
    
    public void unlock(RLock rLock) {
        if (Objects.nonNull(rLock) && rLock.isLocked()) {
            rLock.unlock();
        }
    }
    
}
