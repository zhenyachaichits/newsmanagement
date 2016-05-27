package com.epam.news;

import com.epam.news.service.UserService;
import com.epam.news.service.impl.UserServiceImpl;
import com.epam.news.service.exception.ServiceException;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Yauhen_Chaichyts on 5/27/2016.
 */
public class Runner {
    private UserService service;

    public static void main(String[] args) throws ServiceException {
        AbstractApplicationContext context =
                new ClassPathXmlApplicationContext("spring-config.xml");

        Runner obj = (Runner) context.getBean("runner");
        System.out.print(obj.getService());
    }


    public void setService(UserServiceImpl service) {
        this.service = service;
    }

    public UserService getService() {
        return service;
    }
}
