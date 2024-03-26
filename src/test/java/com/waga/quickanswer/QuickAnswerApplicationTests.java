package com.waga.quickanswer;

import com.waga.quickanswer.mybatis.dao.LoginUserInfoDao;
import com.waga.quickanswer.mybatis.domain.LoginUserInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.Date;

@SpringBootTest
class QuickAnswerApplicationTests {
    private static Logger log = Logger.getLogger(String.valueOf(QuickAnswerApplicationTests.class));

    @Autowired
    private ApplicationContext applicationContext;
    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        String a = "123";
        String b = "123";
        System.out.println("hello world");
        log.info(a == b);
    }

    @Test
    void testApplicationEvent() {
        applicationContext.publishEvent(new OrderEvent(this, "hello"));
    }

    @Test
    void testMybatis() {
        SqlSession session=null;
        SqlSessionFactory factory = null;
        try {
            factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
            session = factory.openSession();
            LoginUserInfoDao loginUserInfoDao = session.getMapper(LoginUserInfoDao.class);
            LoginUserInfo loginUserInfo = new LoginUserInfo("waga", new byte[] {1,2,3}, "openId123", "token123",
                    new Date(),1, new Date(), new Date());
            loginUserInfoDao.insertSimple(loginUserInfo);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.commit();
                session.close();
            }
        }
    }

}
