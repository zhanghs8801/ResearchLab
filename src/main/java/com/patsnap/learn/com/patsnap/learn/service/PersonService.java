package com.patsnap.learn.com.patsnap.learn.service;

import com.patsnap.learn.com.patsnap.learn.bean.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PersonService {

    public static final Logger LOG = LoggerFactory.getLogger(PersonService.class);
    private Map<Integer, Person> personDao = new HashMap<>();

    {
        personDao.put(1, new Person(1, "后羿", "火星"));
        personDao.put(2, new Person(2, "嫦娥", "月球"));
        personDao.put(3, new Person(3, "老夫之", "金星"));
        personDao.put(4, new Person(4, "周瑜", "水星"));
        personDao.put(5, new Person(5, "花木兰", "土星"));
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
}
