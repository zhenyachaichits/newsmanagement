package com.epam.news;

import com.epam.news.domain.criteria.NewsSearchCriteria;
import com.epam.news.service.NewsService;
import com.epam.news.service.UserService;
import com.epam.news.service.exception.ServiceException;
import com.epam.news.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yauhen_Chaichyts on 5/27/2016.
 */
public class Runner {
    @Autowired
    private UserService service;

    @Autowired
    private NewsService newsService;


    public static void main(String[] args) {
        AbstractApplicationContext context =
                new ClassPathXmlApplicationContext("spring-config.xml");

        Runner obj = (Runner) context.getBean("runner");

        NewsSearchCriteria criteria = new NewsSearchCriteria();
        Set<Long> idSet = new HashSet<>(5);
        idSet.add(5L);

        criteria.setAuthorIdSet(idSet);
        criteria.setTagIdSet(idSet);
        try {
            //obj.getService().add(user);
            System.out.print(obj.getNewsService().getNewsCount());
            obj.getNewsService().getNewsByCriteria(criteria);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }


    public void setService(UserServiceImpl service) {
        this.service = service;
    }

    public UserService getService() {
        return service;
    }

    public NewsService getNewsService() {
        return newsService;
    }

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }
}
