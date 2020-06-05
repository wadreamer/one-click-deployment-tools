package com.cfg.deploytools.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: RedisUtils
 * Description: TODO Redis工具类，后续针对Reids的操作均通过该工具类
 * date: 2020/6/5 11:25
 *
 * @author CFG
 * @since JDK 1.8
 */
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    /*
     * @Author wadreamer
     * @Description //TODO 指定缓存失效时间
     * @Date 11:26 2020/6/5
     * @Param [key, time]
     * @return boolean
     **/
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * @Author wadreamer
     * @Description //TODO 根据key获取失效时间
     * @Date 11:27 2020/6/5
     * @Param [key]
     * @return long
     **/
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }


    /*
     * @Author wadreamer
     * @Description //TODO 判断key是否存在
     * @Date 11:27 2020/6/5
     * @Param [key]
     * @return boolean
     **/
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * @Author wadreamer
     * @Description //TODO 删除缓存，采用不定长参数，可传入多个值
     * @Date 11:28 2020/6/5
     * @Param [key]
     * @return boolean
     **/
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    //===================================String========================================

    /*
     * @Author wadreamer
     * @Description //TODO 普通缓存获取
     * @Date 11:40 2020/6/5
     * @Param [key]
     * @return java.lang.Object
     **/
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /*
     * @Author wadreamer
     * @Description //TODO 普通缓存注入
     * @Date 11:40 2020/6/5
     * @Param [key, value]
     * @return boolean
     **/
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * @Author wadreamer
     * @Description //TODO 普通缓存注入，并设置过期时间
     * @Date 11:41 2020/6/5
     * @Param [key, value, time]
     * @return boolean
     **/
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * @Author wadreamer
     * @Description //TODO 递增
     * @Date 11:41 2020/6/5
     * @Param [key, delta]
     * @return long
     **/
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /*
     * @Author wadreamer
     * @Description //TODO 递减
     * @Date 11:42 2020/6/5
     * @Param [key, delta]
     * @return long
     **/
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    //===================================Map========================================
    
    /*
     * @Author wadreamer
     * @Description //TODO 设置一组 Map 的键值对
     * @Date 11:47 2020/6/5
     * @Param [key, item]
     * @return java.lang.Object
     **/
    public Object hmset(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /*
     * @Author wadreamer
     * @Description //TODO 获取某个 key 对应的所有键值
     * @Date 11:51 2020/6/5
     * @Param [key]
     * @return java.util.Map<java.lang.Object,java.lang.Object>
     **/
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /*
     * @Author wadreamer
     * @Description //TODO 添加一个 map 类型的键值
     * @Date 11:52 2020/6/5
     * @Param [key, map]
     * @return boolean
     **/
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * @Author wadreamer
     * @Description //TODO 添加一个 map 类型的键值,并设置过期时间
     * @Date 11:53 2020/6/5
     * @Param [key, map, time]
     * @return boolean
     **/
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /*
     * @Author wadreamer
     * @Description //TODO 向一张 hash 表中放入数据，如果不存在将创建
     * @Date 11:53 2020/6/5
     * @Param [key, item, value]
     * @return boolean
     **/
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
    }
    }

    /*
     * @Author wadreamer
     * @Description //TODO 向一张hash表中放入数据,如果不存在将创建并设置过期时间
     * @Date 11:54 2020/6/5
     * @Param [key, item, value, time]
     * @return boolean
     **/
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * @Author wadreamer
     * @Description //TODO 删除hash表中的值
     * @Date 11:54 2020/6/5
     * @Param [key, item]
     * @return void
     **/
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /*
     * @Author wadreamer
     * @Description //TODO 判断hash表中是否有该项的值
     * @Date 11:55 2020/6/5
     * @Param [key, item]
     * @return boolean
     **/
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /*
     * @Author wadreamer
     * @Description //TODO 递增，如果不存在,就会创建一个 并把新增后的值返回
     * @Date 11:55 2020/6/5
     * @Param [key, item, by]
     * @return double
     **/
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /*
     * @Author wadreamer
     * @Description //TODO 递减，如果不存在,就会创建一个 并把新增后的值返回
     * @Date 11:55 2020/6/5
     * @Param [key, item, by]
     * @return double
     **/
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    //===================================set========================================

    /*
     * @Author wadreamer
     * @Description //TODO 根据key获取Set中的所有值
     * @Date 12:51 2020/6/5
     * @Param [key]
     * @return java.util.Set<java.lang.Object>
     **/
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * @Author wadreamer
     * @Description //TODO 根据value从一个set中查询,是否存在
     * @Date 12:52 2020/6/5
     * @Param [key, value]
     * @return boolean
     **/
    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * @Author wadreamer
     * @Description //TODO 将数据放入set缓存
     * @Date 12:52 2020/6/5
     * @Param [key, values]
     * @return long
     **/
    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /*
     * @Author wadreamer
     * @Description //TODO 将set数据放入缓存，并设置过期时间
     * @Date 12:52 2020/6/5
     * @Param [key, time, values]
     * @return long
     **/
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /*
     * @Author wadreamer
     * @Description //TODO 获取set缓存的长度
     * @Date 12:53 2020/6/5
     * @Param [key]
     * @return long
     **/
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /*
     * @Author wadreamer
     * @Description //TODO 移除值为value的
     * @Date 12:53 2020/6/5
     * @Param [key, values]
     * @return long
     **/
    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //===================================list========================================

    /*
     * @Author wadreamer
     * @Description //TODO 获取list缓存的内容
     * @Date 12:54 2020/6/5
     * @Param [key, start, end]
     * @return java.util.List<java.lang.Object>
     **/
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * @Author wadreamer
     * @Description //TODO 获取list缓存的长度
     * @Date 12:55 2020/6/5
     * @Param [key]
     * @return long
     **/
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /*
     * @Author wadreamer
     * @Description //TODO 通过索引 获取list中的值
     * @Date 12:55 2020/6/5
     * @Param [key, index]
     * @return java.lang.Object
     **/
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * @Author wadreamer
     * @Description //TODO 将list放入缓存
     * @Date 12:55 2020/6/5
     * @Param [key, value]
     * @return boolean
     **/
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * @Author wadreamer
     * @Description //TODO 将list放入缓存，并设置过期时间
     * @Date 12:55 2020/6/5
     * @Param [key, value, time]
     * @return boolean
     **/
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0)
                expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * @Author wadreamer
     * @Description //TODO 将list放入缓存
     * @Date 12:56 2020/6/5
     * @Param [key, value]
     * @return boolean
     **/
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * @Author wadreamer
     * @Description //TODO 将list放入缓存
     * @Date 12:57 2020/6/5
     * @Param [key, value, time]
     * @return boolean
     **/
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * @Author wadreamer
     * @Description //TODO 根据索引修改list中的某条数据
     * @Date 12:58 2020/6/5
     * @Param [key, index, value]
     * @return boolean
     **/
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * @Author wadreamer
     * @Description //TODO 移除N个值为value
     * @Date 12:58 2020/6/5
     * @Param [key, count, value]
     * @return long
     **/
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}

