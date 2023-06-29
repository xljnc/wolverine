package com.wt.test.wolverine.util;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.domain.geo.Metrics;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * redis 工具类
 *
 * @author qiyu
 * @date 2020/12/29
 */
@Component
public class RedisUtil {

    @Resource
    @Qualifier("jacksonRedisTemplate")
    private RedisTemplate<String, Object> jacksonRedisTemplate;

    @Resource
    @Qualifier("stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @param key
     * @return boolean 如果key存在返回true，否则返回false
     * @description 检查Key是否存在
     */
    public boolean existsKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * @param key
     * @return boolean 删除key
     * @description
     */
    public boolean deleteKey(String key) {
        return stringRedisTemplate.delete(key);
    }

    /**
     * 通过前缀模糊匹配删除Key
     * 注意：这是个危险操作，因为keys命令的复杂度是O(n),执行速度非常慢
     * Redis的单线程模型决定了在执行完keys命令前是阻塞的
     * key数量大的情况下非常危险， 酌情使用
     *
     * @deprecated As of JDK version 8
     **/
    @Deprecated(since = "jdk8")
    public Long deleteByPrefix(String prefix) {
        Set<String> keys = stringRedisTemplate.keys(prefix + "*");
        return stringRedisTemplate.delete(keys);
    }

    /**
     * 通过后缀模糊匹配删除Key
     * 注意：这是个危险操作，因为keys命令的复杂度是O(n),执行速度非常慢
     * Redis的单线程模型决定了在执行完keys命令前是阻塞的
     * key数量大的情况下非常危险， 酌情使用
     *
     * @deprecated As of JDK version 8
     **/
    @Deprecated(since = "jdk8")
    public Long deleteBySuffix(String suffix) {
        Set<String> keys = stringRedisTemplate.keys("*" + suffix);
        return stringRedisTemplate.delete(keys);
    }


    /**
     * 获取String类型的Value
     **/
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * @param key
     * @param value
     * @return void
     * @description 设置String类型的Value
     */
    public void setString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置String类型的Value
     *
     * @param key
     * @param value
     * @param expireTime
     * @param timeUnit
     * @return void
     */
    public void setString(String key, String value, Long expireTime, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, value, expireTime, timeUnit);
    }

    /**
     * 获取过期时间
     *
     * @param key
     * @param timeUnit
     * @return java.lang.Long
     */
    public Long getExpireTime(String key, TimeUnit timeUnit) {
        return stringRedisTemplate.getExpire(key, timeUnit);
    }

    /**
     * 设置过期时间
     *
     * @param key        key
     * @param expireTime 过期时间
     * @param timeUnit   时间格式
     * @return java.lang.Boolean 是否成功
     */
    public Boolean setExpireTime(String key, Long expireTime, TimeUnit timeUnit) {
        return stringRedisTemplate.expire(key, expireTime, timeUnit);
    }

    /**
     * 存储可序列化对象
     *
     * @param key
     * @param value
     * @param expireTime
     * @param timeUnit
     */
    public void setObject(String key, Object value, Long expireTime, TimeUnit timeUnit) {
        jacksonRedisTemplate.opsForValue().set(key, value, expireTime, timeUnit);
    }

    /**
     * 存储可序列化对象
     *
     * @param key
     * @param value
     */
    public void setObject(String key, Object value) {
        jacksonRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取对象
     **/
    public Object getObject(String key) {
        return jacksonRedisTemplate.opsForValue().get(key);
    }

    /**
     * 获取指定类型对象
     **/
    public <T> T getGenericObject(String key) {
        return (T) jacksonRedisTemplate.opsForValue().get(key);
    }

    /**
     * 扫描key
     *
     * @param pattern 扫描模式
     * @param count   每次扫描的数量
     * @return java.util.Set<java.lang.String>
     */
    public Set<String> scan(String pattern, long count) {
        Set<String> keys = new HashSet<>();
        Cursor<byte[]> cursor = null;
        try {
            cursor = stringRedisTemplate.executeWithStickyConnection(connection ->
                    connection.scan(ScanOptions.scanOptions().match(pattern).count(count).build())
            );
            while (cursor.hasNext()) {
                keys.add(new String(cursor.next()));
            }
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        return keys;
    }

    /**
     * 位操作, set
     *
     * @param key    key
     * @param offset 偏移量
     * @param value  true:1, false:0
     * @return java.lang.Boolean 是否成功
     */
    public Boolean setBit(String key, long offset, boolean value) {
        return stringRedisTemplate.opsForValue().setBit(key, offset, value);
    }

    /**
     * 位操作,get
     *
     * @param key    key
     * @param offset 偏移量
     * @return java.lang.Boolean true:1, false:0
     */
    public Boolean getBit(String key, long offset) {
        return stringRedisTemplate.opsForValue().getBit(key, offset);
    }

    /**
     * 位操作,统计被设置为1的位数
     *
     * @param key   key
     * @param start 起始偏移量
     * @param end   截止偏移量
     * @return 被设置为1的位数
     */
    public Long bitCount(String key, long start, long end) {
        return stringRedisTemplate.execute((RedisCallback<Long>) conn -> conn.bitCount(key.getBytes(), start, end));
    }

    /**
     * 存储Hash
     *
     * @param key     key
     * @param hashKey hash Key
     * @param value   hash value
     */
    public void setHash(String key, String hashKey, Object value) {
        stringRedisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 获取hash key存储值
     *
     * @param key     key
     * @param hashKey hash Key
     */
    public <T> T getHash(String key, String hashKey) {
        return (T) stringRedisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * @param key     key
     * @param hashKey hash键
     * @param value   hash值
     * @return java.lang.Boolean 是否成功
     */
    public Boolean setHashValueIfAbsent(String key, String hashKey, Object value) {
        return stringRedisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }

    /**
     * 是否存在 hash key
     *
     * @param key     key
     * @param hashKey hash键
     * @return java.lang.Boolean 是否存在key
     */
    public Boolean containsHashKey(String key, String hashKey) {
        return stringRedisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * 批量获取 hash key
     *
     * @param key      key
     * @param hashKeys hash键集合
     * @return
     */
    public List<Object> getMultiHashValues(String key, Collection<Object> hashKeys) {
        return stringRedisTemplate.opsForHash().multiGet(key, hashKeys);
    }

    /**
     * 扫描key
     *
     * @param key     key
     * @param pattern 扫描模式
     * @param count   每次扫描的数量
     * @return hash中的键值对
     */
    public Set<Map.Entry<Object, Object>> scanHash(String key, String pattern, long count) {
        Cursor<Map.Entry<Object, Object>> cursor = null;
        Set<Map.Entry<Object, Object>> entries = new HashSet<>();
        try {
            cursor = stringRedisTemplate.opsForHash().scan(key,
                    ScanOptions.scanOptions().match(pattern).count(count).build());
            while (cursor.hasNext()) {
                entries.add(cursor.next());
            }
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        return entries;
    }

    /**
     * 判断Set是否包含指定对象
     *
     * @param key       key
     * @param candidate 对象
     * @return java.lang.Boolean
     */
    public Boolean isSetMember(String key, String candidate) {
        return stringRedisTemplate.opsForSet().isMember(key, candidate);
    }

    /**
     * 扫描key
     *
     * @param key     key
     * @param pattern 扫描模式
     * @param count   每次扫描的数量
     * @return set中的值
     */
    public Set<String> scanSet(String key, String pattern, long count) {
        Cursor<String> cursor = null;
        Set<String> values = new HashSet<>();
        try {
            cursor = stringRedisTemplate.opsForSet().scan(key,
                    ScanOptions.scanOptions().match(pattern).count(count).build());
            while (cursor.hasNext()) {
                values.add(cursor.next());
            }
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
        return values;
    }

    /**
     * value加入到zset
     *
     * @param key   key
     * @param value value
     * @param score 分数
     * @return java.lang.Boolean 是否添加成功
     */
    public Boolean addZSetValue(String key, String value, double score) {
        return stringRedisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * value加入到HyperLogLog
     *
     * @param key    key
     * @param values values
     * @return java.lang.Long 添加成功的个数
     */
    public Long addHyperLogLogValue(String key, String... values) {
        return stringRedisTemplate.opsForHyperLogLog().add(key, values);
    }

    /**
     * HyperLogLog基数
     * 如果是多个HyperLogLog，则返回基数估值之和
     *
     * @param keys keys
     * @return java.lang.Long 基数估值之和
     */
    public Long sizeOfHyperLogLog(String... keys) {
        return stringRedisTemplate.opsForHyperLogLog().size(keys);
    }

    /**
     * 合并HyperLogLog
     *
     * @param destination 目标
     * @param sourceKeys  源
     * @return java.lang.Long 合并后的基数估值之和
     */
    public Long mergeHyperLogLog(String destination, String... sourceKeys) {
        return stringRedisTemplate.opsForHyperLogLog().union(destination, sourceKeys);
    }


    /**
     * 添加geo信息
     *
     * @param key    key
     * @param x      精度
     * @param y      维度
     * @param member geo对象
     * @return java.lang.Long
     */
    public Long addGeo(String key, double x, double y, String member) {
        Point point = new Point(x, y);
        return stringRedisTemplate.opsForGeo().add(key, point, member);
    }

    /**
     * 批量添加geo信息
     *
     * @param key     key
     * @param members geo对象
     * @return java.lang.Long 添加的数量
     */
    public Long batchAddGeo(String key, Map<String, double[]> members) {
        Map<String, Point> memberCoordinateMap = new HashMap<>();
        members.entrySet().stream().forEach(member -> memberCoordinateMap.put(member.getKey(),
                new Point(member.getValue()[0], member.getValue()[1])));
        return stringRedisTemplate.opsForGeo().add(key, memberCoordinateMap);
    }

    /**
     * 获取geo距离
     *
     * @param key        key
     * @param member1    geo对象
     * @param member2    geo对象
     * @param metricUnit 距离单位
     * @return java.lang.Double 距离
     */
    public Double geoDistance(String key, String member1, String member2, MetricUnit metricUnit) {
        Optional<Distance> distanceOptional = Optional.ofNullable(stringRedisTemplate.opsForGeo().distance(key, member1, member2, metricUnit.getMappedMetrics()));
        return distanceOptional.map(Distance::getValue).orElse(null);
    }

    /**
     * 获取geo信息
     *
     * @param key     key
     * @param members geo对象
     * @return List<Double [ 2 ]> 位置
     */
    public List<Double[]> geoPosition(String key, String... members) {
        List<Point> points = stringRedisTemplate.opsForGeo().position(key, members);
        if (CollectionUtils.isEmpty(points))
            return Collections.emptyList();
        return points.stream().map(point ->
                new Double[]{point.getX(), point.getY()}
        ).collect(Collectors.toList());
    }

    /**
     * 获取geo hash
     *
     * @param key     key
     * @param members geo对象
     * @return List<String> geo hash list
     */
    public List<String> geoHash(String key, String... members) {
        return stringRedisTemplate.opsForGeo().hash(key, members);
    }

    /**
     * 删除geo信息
     *
     * @param key     key
     * @param members geo对象
     * @return java.lang.Long 删除的数量
     */
    public Long removeGeo(String key, String... members) {
        return stringRedisTemplate.opsForGeo().remove(key, members);
    }

    public enum MetricUnit {
        METERS("m"), KILOMETERS("km"), MILES("mi"), FEET("ft");

        private final String abbreviation;

        MetricUnit(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        Metrics getMappedMetrics() {
            for (Metrics item : Metrics.values()) {
                if (item.getAbbreviation().equals(this.abbreviation))
                    return item;
            }
            throw new IllegalArgumentException("距离单位不合法");
        }
    }
}
