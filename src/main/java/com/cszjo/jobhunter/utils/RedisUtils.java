package com.cszjo.jobhunter.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.exceptions.JedisException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Han on 2017/4/20.
 */
@Component
public class RedisUtils {

    private  final Logger LOOGER = LoggerFactory.getLogger(RedisUtils.class);

    @Autowired
    private  JedisPool jedisPool;

    /**
     * Returns all the keys matching the glob-style pattern as space separated strings.
     *
     * @param pattern
     * @return
     */
    public  Set<String> keys(String pattern) {
        Set<String> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            value = jedis.keys(pattern);
            LOOGER.debug("getSet {} = {}", pattern, value);
        } catch (Exception e) {
            LOOGER.warn("keys {} = {}", pattern, value, e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * Delete all the keys of the currently selected DB. This command never fails.
     *
     * @return
     */
    public  String flushDB() {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            value = jedis.flushDB();
            LOOGER.debug("flushDB: {}", value);
        } catch (Exception e) {
            LOOGER.warn("flushDB error: {}", e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }


    /**
     * Return the number of keys in the currently selected database.
     *
     * @return
     */
    public  Long dbSize() {
        Long value = 0L;
        Jedis jedis = null;
        try {
            jedis = getResource();
            value = jedis.dbSize();
            LOOGER.debug("flushDB: {}", value);
        } catch (Exception e) {
            LOOGER.warn("flushDB error: {}", e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * Get the value of the specified key. If the key does not exist null is returned.
     *
     * @param key
     * @return
     */
    public  String get(String key) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.get(key);
                value = StringUtils.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
                LOOGER.debug("get {} = {}", key, value);
            }
        } catch (Exception e) {
            LOOGER.warn("get {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    public  long setnx(String key, String val){
        Long res = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            res = jedis.setnx(key, val);
        } catch (Exception e) {
            LOOGER.warn("get {} = {}", key, res, e);
        } finally {
            returnResource(jedis);
        }
        return res == null ? 0 : res;
    }

    public  String getset(String key, String val){
        String value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            value = jedis.getSet(key, val);
        } catch (Exception e) {
            LOOGER.warn("get {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    public  long expire(String key, int seconds){
        Long value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            value = jedis.expire(key, seconds);
        } catch (Exception e) {
            LOOGER.warn("get {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return value == null ? 0 : value;
    }

    /**
     * Get the object value of the specified key. If the key does not exist null is returned.
     *
     * @param key
     * @return
     */
    public  Object getObject(String key) {
        Object value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                value = toObject(jedis.get(getBytesKey(key)));
                LOOGER.debug("getObject {} = {}", key, value);
            }
        } catch (Exception e) {
            LOOGER.warn("getObject {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * Set the string value as value of the key.
     *
     * @param key
     * @param value
     * @param cacheSeconds expire time in seconds, no expire if 0
     * @return
     */
    public  String set(String key, String value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.set(key, value);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            LOOGER.debug("set {} = {}", key, value);
        } catch (Exception e) {
            LOOGER.warn("set {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * Set the object value as value of the key.
     *
     * @param key
     * @param value
     * @param cacheSeconds expire time in seconds, no expire if 0
     * @return
     */
    public  String setObject(String key, Object value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.set(getBytesKey(key), toBytes(value));
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            LOOGER.debug("setObject {} = {}", key, value);
        } catch (Exception e) {
            LOOGER.warn("setObject {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * Return the specified elements of the list stored at the specified key.
     *
     * @param key
     * @return
     */
    public  List<String> getList(String key) {
        List<String> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.lrange(key, 0, -1);
                LOOGER.debug("getList {} = {}", key, value);
            }
        } catch (Exception e) {
            LOOGER.warn("getList {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * Return the specified elements of the list stored at the specified key.
     *
     * @param key
     * @return
     */
    public  List<Object> getObjectList(String key) {
        List<Object> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                List<byte[]> list = jedis.lrange(getBytesKey(key), 0, -1);
                value = Lists.newArrayList();
                for (byte[] bs : list) {
                    value.add(toObject(bs));
                }
                LOOGER.debug("getObjectList {} = {}", key, value);
            }
        } catch (Exception e) {
            LOOGER.warn("getObjectList {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * Add the string value to the head (LPUSH) or tail (RPUSH) of the list stored at key.
     *
     * @param key
     * @param value
     * @param cacheSeconds expire time in seconds, no expire if 0
     * @return
     */
    public  long setList(String key, List<String> value, int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            result = jedis.lpush(key, (String[]) value.toArray());
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            LOOGER.debug("setList {} = {}", key, value);
        } catch (Exception e) {
            LOOGER.warn("setList {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * Add the object value to the head (LPUSH) or tail (RPUSH) of the list stored at key.
     *
     * @param key
     * @param value
     * @param cacheSeconds expire time in seconds, no expire if 0
     * @return
     */
    public  long setObjectList(String key, List<Object> value, int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                jedis.del(key);
            }
            List<byte[]> list = Lists.newArrayList();
            for (Object o : value) {
                list.add(toBytes(o));
            }
            result = jedis.lpush(getBytesKey(key), (byte[][]) list.toArray());
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            LOOGER.debug("setObjectList {} = {}", key, value);
        } catch (Exception e) {
            LOOGER.warn("setObjectList {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * Add the string value to the head (LPUSH) or tail (RPUSH) of the list stored at key.
     *
     * @param key
     * @param value
     * @return
     */
    public  long listAdd(String key, String... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.lpush(key, value);
            LOOGER.debug("listAdd {} = {}", key, value);
        } catch (Exception e) {
            LOOGER.warn("listAdd {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * Add the object value to the head (LPUSH) or tail (RPUSH) of the list stored at key.
     *
     * @param key
     * @param value
     * @return
     */
    public  long listObjectAdd(String key, Object... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            List<byte[]> list = Lists.newArrayList();
            for (Object o : value) {
                list.add(toBytes(o));
            }
            result = jedis.lpush(getBytesKey(key), (byte[][]) list.toArray());
            LOOGER.debug("listObjectAdd {} = {}", key, value);
        } catch (Exception e) {
            LOOGER.warn("listObjectAdd {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * Return all the members (elements) of the set value stored at key.
     *
     * @param key
     * @return
     */
    public  Set<String> getSet(String key) {
        Set<String> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.smembers(key);
                LOOGER.debug("getSet {} = {}", key, value);
            }
        } catch (Exception e) {
            LOOGER.warn("getSet {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * Return all the members (elements) of the set value stored at key.
     *
     * @param key
     * @return
     */
    public  Set<Object> getObjectSet(String key) {
        Set<Object> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                value = Sets.newHashSet();
                Set<byte[]> set = jedis.smembers(getBytesKey(key));
                for (byte[] bs : set) {
                    value.add(toObject(bs));
                }
                LOOGER.debug("getObjectSet {} = {}", key, value);
            }
        } catch (Exception e) {
            LOOGER.warn("getObjectSet {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * Add the specified member to the set value stored at key.
     *
     * @param key
     * @param value
     * @param cacheSeconds expire time in seconds, no expire if 0
     * @return
     */
    public  long setSet(String key, Set<String> value, int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            result = jedis.sadd(key, (String[]) value.toArray());
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            LOOGER.debug("setSet {} = {}", key, value);
        } catch (Exception e) {
            LOOGER.warn("setSet {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * Add the specified member to the set value stored at key.
     *
     * @param key
     * @param value
     * @param cacheSeconds expire time in seconds, no expire if 0
     * @return
     */
    public  long setObjectSet(String key, Set<Object> value, int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                jedis.del(key);
            }
            Set<byte[]> set = Sets.newHashSet();
            for (Object o : value) {
                set.add(toBytes(o));
            }
            result = jedis.sadd(getBytesKey(key), (byte[][]) set.toArray());
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            LOOGER.debug("setObjectSet {} = {}", key, value);
        } catch (Exception e) {
            LOOGER.warn("setObjectSet {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * Add the specified member to the set value stored at key.
     *
     * @param key
     * @param value
     * @return
     */
    public  long setSetAdd(String key, String... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.sadd(key, value);
            LOOGER.debug("setSetAdd {} = {}", key, value);
        } catch (Exception e) {
            LOOGER.warn("setSetAdd {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * Add the specified member to the set value stored at key.
     *
     * @param key
     * @param value
     * @return
     */
    public  long setSetObjectAdd(String key, Object... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            Set<byte[]> set = Sets.newHashSet();
            for (Object o : value) {
                set.add(toBytes(o));
            }
            result = jedis.lpush(getBytesKey(key), (byte[][]) set.toArray());
            LOOGER.debug("setSetObjectAdd {} = {}", key, value);
        } catch (Exception e) {
            LOOGER.warn("setSetObjectAdd {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * Return all the fields and associated values in a hash.
     *
     * @param key
     * @return
     */
    public  Map<String, String> getMap(String key) {
        Map<String, String> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.hgetAll(key);
                LOOGER.debug("getMap {} = {}", key, value);
            }
        } catch (Exception e) {
            LOOGER.warn("getMap {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * Return all the fields and associated values in a hash.
     *
     * @param key
     * @return
     */
    public  Map<String, Object> getObjectMap(String key) {
        Map<String, Object> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                value = Maps.newHashMap();
                Map<byte[], byte[]> map = jedis.hgetAll(getBytesKey(key));
                for (Map.Entry<byte[], byte[]> e : map.entrySet()) {
                    value.put(StringUtils.toEncodedString(e.getKey(), null), toObject(e.getValue()));
                }
                LOOGER.debug("getObjectMap {} = {}", key, value);
            }
        } catch (Exception e) {
            LOOGER.warn("getObjectMap {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * Set the respective fields to the respective values.
     *
     * @param key
     * @param value
     * @param cacheSeconds expire time in seconds, no expire if 0
     * @return
     */
    public  String setMap(String key, Map<String, String> value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            result = jedis.hmset(key, value);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            LOOGER.debug("setMap {} = {}", key, value);
        } catch (Exception e) {
            LOOGER.warn("setMap {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * Set the respective fields to the respective values.
     *
     * @param key
     * @param value
     * @param cacheSeconds expire time in seconds, no expire if 0
     * @return
     */
    public  String setObjectMap(String key, Map<String, Object> value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                jedis.del(key);
            }
            Map<byte[], byte[]> map = Maps.newHashMap();
            for (Map.Entry<String, Object> e : value.entrySet()) {
                map.put(getBytesKey(e.getKey()), toBytes(e.getValue()));
            }
            result = jedis.hmset(getBytesKey(key), map);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            LOOGER.debug("setObjectMap {} = {}", key, value);
        } catch (Exception e) {
            LOOGER.warn("setObjectMap {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * Set the respective fields to the respective values.
     *
     * @param key
     * @param value
     * @return
     */
    public  String mapPut(String key, Map<String, String> value) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hmset(key, value);
            LOOGER.debug("mapPut {} = {}", key, value);
        } catch (Exception e) {
            LOOGER.warn("mapPut {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * Set the respective fields to the respective values.
     *
     * @param key
     * @param value
     * @return
     */
    public  String mapObjectPut(String key, Map<String, Object> value) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            Map<byte[], byte[]> map = Maps.newHashMap();
            for (Map.Entry<String, Object> e : value.entrySet()) {
                map.put(getBytesKey(e.getKey()), toBytes(e.getValue()));
            }
            result = jedis.hmset(getBytesKey(key), map);
            LOOGER.debug("mapObjectPut {} = {}", key, value);
        } catch (Exception e) {
            LOOGER.warn("mapObjectPut {} = {}", key, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * Remove the specified field from an hash stored at key.
     *
     * @param key
     * @param mapKey
     * @return
     */
    public  long mapRemove(String key, String mapKey) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hdel(key, mapKey);
            LOOGER.debug("mapRemove {}  {}", key, mapKey);
        } catch (Exception e) {
            LOOGER.warn("mapRemove {}  {}", key, mapKey, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * Remove the specified field from an hash stored at key.
     *
     * @param key
     * @param mapKey
     * @return
     */
    public  long mapObjectRemove(String key, String mapKey) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hdel(getBytesKey(key), getBytesKey(mapKey));
            LOOGER.debug("mapObjectRemove {}  {}", key, mapKey);
        } catch (Exception e) {
            LOOGER.warn("mapObjectRemove {}  {}", key, mapKey, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * Test for existence of a specified field in a hash.
     *
     * @param key
     * @param mapKey
     * @return
     */
    public  boolean mapExists(String key, String mapKey) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hexists(key, mapKey);
            LOOGER.debug("mapExists {}  {}", key, mapKey);
        } catch (Exception e) {
            LOOGER.warn("mapExists {}  {}", key, mapKey, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * Test for existence of a specified field in a hash.
     *
     * @param key
     * @param mapKey
     * @return
     */
    public  boolean mapObjectExists(String key, String mapKey) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hexists(getBytesKey(key), getBytesKey(mapKey));
            LOOGER.debug("mapObjectExists {}  {}", key, mapKey);
        } catch (Exception e) {
            LOOGER.warn("mapObjectExists {}  {}", key, mapKey, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * Remove the specified key.
     *
     * @param key
     * @return
     */
    public  long del(String key) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                result = jedis.del(key);
                LOOGER.debug("del {}", key);
            } else {
                LOOGER.debug("del {} not exists", key);
            }
        } catch (Exception e) {
            LOOGER.warn("del {}", key, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * Remove the specified key.
     *
     * @param key
     * @return
     */
    public  long delObject(String key) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                result = jedis.del(getBytesKey(key));
                LOOGER.debug("delObject {}", key);
            } else {
                LOOGER.debug("delObject {} not exists", key);
            }
        } catch (Exception e) {
            LOOGER.warn("delObject {}", key, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * Test if the specified key exists.
     *
     * @param key
     * @return
     */
    public  boolean exists(String key) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.exists(key);
            LOOGER.debug("exists {}", key);
        } catch (Exception e) {
            LOOGER.warn("exists {}", key, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * Return the type of the value stored at key in form of a string.
     *
     * @param key
     * @return
     */
    public  String type(String key) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.type(getBytesKey(key));
            LOOGER.debug("type {}", key);
        } catch (Exception e) {
            LOOGER.warn("type {}", key, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * Test if the specified key exists.
     *
     * @param key
     * @return
     */
    public  boolean existsObject(String key) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.exists(getBytesKey(key));
            LOOGER.debug("existsObject {}", key);
        } catch (Exception e) {
            LOOGER.warn("existsObject {}", key, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * Fetch a jedis instance from pool.
     *
     * @return
     * @throws JedisException
     */
    public  Jedis getResource() throws JedisException {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            // LOOGER.debug("getResource.", jedis);
        } catch (JedisException e) {
            LOOGER.warn("getResource.", e);
            // returnBrokenResource(jedis);
            throw e;
        }
        return jedis;
    }

    /**
     * Returns the jedis instance to pool.
     *
     * @param jedis
     */
    public  void returnBrokenResource(Jedis jedis) {
        if (jedis != null) {
            jedisPool.close();
        }
    }

    /**
     * Returns the jedis instance to pool.
     *
     * @param jedis
     */
    public  void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    /**
     * Transform Object key to Bytes.
     */
    public  byte[] getBytesKey(Object object) {
        if (object instanceof String) {
            return ((String) object).getBytes();
        } else {
            return SerializationUtils.serialize((Serializable) object);
        }
    }

    /**
     * Serialize Object to Bytes.
     *
     * @param object
     * @return
     */
    public  byte[] toBytes(Object object) {
        return SerializationUtils.serialize((Serializable) object);
    }

    /**
     * Serialize bytes to Object.
     *
     * @param bytes
     * @return
     */
    public  Object toObject(byte[] bytes) {
        return SerializationUtils.deserialize(bytes);
    }

    public  String rpop(String key) {
        String vlaue = null;
        Jedis jedis = null;
        try {
            LOOGER.debug("rpop 1");
            jedis = getResource();
            LOOGER.debug("rpop 2 {}", jedis);
            vlaue = jedis.rpop(key);
            LOOGER.debug("rpop 3 {}", vlaue);
        } catch (Exception e) {
            LOOGER.warn("rpop = {}", key, e);
        } finally {
            returnResource(jedis);
        }
        return vlaue;
    }

    /**
     * 在名称为key的list头添加一个值为value的元素
     *
     * @param key
     * @param value
     * @return
     */
    public  long lpush(String key, String value) {
        Jedis jedis = null;
        long result = 0;
        try {
            jedis = jedisPool.getResource();
            result = jedis.lpush(key, value);
//      LOOGER.debug("lpush {}", key);
        } catch (Exception e) {
            LOOGER.warn("lpush = {}", key, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }


    /**
     * 在名称为key的list尾添加一个值为value的元素
     *
     * @param key
     * @param value
     * @return
     */
    public  long rpush(String key, Object value) {
        Jedis jedis = null;
        long result = 0;
        try {
            jedis = jedisPool.getResource();
            result = jedis.rpush(getBytesKey(key), toBytes(value));
//      LOOGER.debug("lpush {}", key);
        } catch (Exception e) {
            LOOGER.warn("lpush = {}", key, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 返回并删除名称为srckey的list的尾元素，并将该元素添加到名称为dstkey的list的头部
     *
     * @param srckey
     * @param dstkey
     * @return
     */
    public  String rpoplpush(String srckey, String dstkey) {
        Jedis jedis = null;
        String result = "";
        try {
            jedis = jedisPool.getResource();
            result = jedis.rpoplpush(srckey, dstkey);
//      LOOGER.debug("rpoplpush srckey={},dstkey={}", srckey, dstkey);
        } catch (Exception e) {
            LOOGER.warn("rpoplpush srckey={},dstkey={}", srckey, dstkey, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 从列表中弹出一个值，它推到另一个列表并返回它，或阻塞直到有可用
     * @param srckey
     * @param dstkey
     * @return
     */
    public  String brpoplpush(String srckey, String dstkey) {
        Jedis jedis = null;
        String result = "";
        try {
            jedis = jedisPool.getResource();
            result = jedis.brpoplpush(srckey, dstkey,0);
//      LOOGER.debug("brpoplpush srckey={},dstkey={}", srckey, dstkey);
        } catch (Exception e) {
            LOOGER.warn("brpoplpush srckey={},dstkey={}", srckey, dstkey, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 根据参数 count 的值，移除列表中与参数 value 相等的元素。
     *
     * @param key
     * @param count
     * @param value
     * @return
     */
    public  Long lrem(String key, long count, String value) {
        Jedis jedis = null;
        Long result = 0l;
        try {
            jedis = jedisPool.getResource();
            result = jedis.lrem(key, count, value);
//      LOOGER.debug("lrem key={},count={},value={}", key, count, value);
        } catch (Exception e) {
            LOOGER.warn("lrem key={},count={},value={}", key, count, value, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    public  Long publish(final String channel, final String message) {
        long result = 0L;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.publish(channel, message);
//      LOOGER.debug("publish {} {}", channel, message);
        } catch (Exception e) {
            LOOGER.warn("publish = {} {}", channel, message, e);
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    public  void psubscribe(final JedisPubSub jedisPubSub, final String... patterns) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.psubscribe(jedisPubSub, patterns);
//      LOOGER.debug("psubscribe {}", Objects.toString(patterns));
        } catch (Exception e) {
            LOOGER.warn("psubscribe = {}", Objects.toString(patterns));
        } finally {
            returnResource(jedis);
        }
    }
}
