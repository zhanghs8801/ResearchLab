package com.patsnap.learn.com.patsnap.learn.resource;

import com.patsnap.learn.com.patsnap.learn.bean.EntityResponse;
import com.patsnap.learn.com.patsnap.learn.bean.Person;
import com.patsnap.learn.com.patsnap.learn.service.PersonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonResource {
    private static final Logger LOG = LoggerFactory.getLogger(PersonResource.class);

    @Autowired
    private PersonService personService;

    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/find_person/{id}")
    public EntityResponse<Person> findPerson(@PathVariable("id") Integer id) {
        LOG.info("receive a find person request");
        long start = System.currentTimeMillis();
        Person person = personService.findPerson(id);
        long cost = System.currentTimeMillis() - start;
        LOG.info("find person cost {}", cost);
        return new EntityResponse<>(person, cost);
    }

    @GetMapping("/clean_cache")
    public EntityResponse<String> cleanCache() {
        long start = System.currentTimeMillis();
        if (personService.cleanCache()) {
            return new EntityResponse<>("clean cache successfully", System.currentTimeMillis() - start);
        }
        return new EntityResponse<>("clean cache failure", System.currentTimeMillis() - start);
    }
}
