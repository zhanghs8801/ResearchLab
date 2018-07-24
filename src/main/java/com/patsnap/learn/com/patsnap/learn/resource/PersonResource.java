package com.patsnap.learn.com.patsnap.learn.resource;

import com.patsnap.learn.com.patsnap.learn.bean.Person;
import com.patsnap.learn.com.patsnap.learn.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class PersonResource {
    private static final Logger LOG = LoggerFactory.getLogger(PersonResource.class);

    @Autowired
    private PersonService personService;

    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/findPerson/{id}")
    public Person findPerson(@PathVariable("id") Integer id) {
        LOG.info("receive a find person request");
        long start = System.currentTimeMillis();
        Person person = personService.findPerson(id);
        LOG.info("find person cost {}", (System.currentTimeMillis() - start));
        return person;
    }

    @GetMapping("/stats")
    public void stats() {
        Collection<String> cacheNames = cacheManager.getCacheNames();
        for (String cacheName : cacheNames) {
            GuavaCache guavaCache = (GuavaCache) cacheManager.getCache(cacheName);
        }
    }
}
