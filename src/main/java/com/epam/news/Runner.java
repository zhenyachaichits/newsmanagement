package com.epam.news;

import com.epam.news.service.impl.UserServiceImpl;
import com.epam.news.service.exception.ServiceException;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Yauhen_Chaichyts on 5/27/2016.
 */
public class Runner {
    private UserServiceImpl service;

    public static void main(String[] args) throws ServiceException {
        AbstractApplicationContext context =
                new ClassPathXmlApplicationContext("spring-config.xml");

        Runner obj = (Runner) context.getBean("runner");
        obj.getService().test();
    }


    public void setService(UserServiceImpl service) {
        this.service = service;
    }

    public UserServiceImpl getService() {
        return service;
    }
}
