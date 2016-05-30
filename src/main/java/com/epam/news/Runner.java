package com.epam.news;

import com.epam.news.domain.User;
import com.epam.news.service.UserService;
import com.epam.news.service.impl.UserServiceImpl;
import com.epam.news.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Yauhen_Chaichyts on 5/27/2016.
 */
public class Runner {
    @Autowired
    private UserService service;

    public static void main(String[] args) {
        AbstractApplicationContext context =
                new ClassPathXmlApplicationContext("spring-config.xml");

        Runner obj = (Runner) context.getBean("runner");

        User user = new User();
        user.setUserName("test");
        user.setLogin("test");
        user.setPassword("test");
        try {
            obj.getService().add(user);
        } catch (ServiceException | RuntimeException e) {
            e.printStackTrace();
        }
    }


    public void setService(UserServiceImpl service) {
        this.service = service;
    }

    public UserService getService() {
        return service;
    }
}
