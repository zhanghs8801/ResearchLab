package com.patsnap.learn.com.patsnap.learn.service;

import com.patsnap.learn.com.patsnap.learn.bean.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PersonService {

    public static final Logger LOG = LoggerFactory.getLogger(PersonService.class);
    private Map<Integer, Person> personDao = new HashMap<>();

    {
        personDao.put(1, new Person(1, "周瑜看小桥流水", "火星"));
        personDao.put(2, new Person(2, "嫦娥遇后羿射日", "月球"));
        personDao.put(3, new Person(3, "元帝惜昭君出塞", "金星"));
    }

    /**
     * 实现入口：{@link org.springframework.cache.interceptor.CacheInterceptor}
     * @param id
     * @return
     */
    @Cacheable(value = "FIND_PERSON", key = "#id")
    public Person findPerson(int id) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOG.info("fetch person with id {}", id);
        return personDao.get(id);
    }

    @CacheEvict(value = "FIND_PERSON", allEntries = true)
    public boolean cleanCache(){
        LOG.info("Evict all caches");
        return true;
    }
}
