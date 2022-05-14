package cn.itcast;

import cn.itcast.dao.clientDao;
import cn.itcast.domain.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)//spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:application.xml")//指定Spring容器的配置---对应到自己写的配置里面
//有了@ContextConfiguration(locations = "classpath:application.xml")这个，就可以从里面调出刚刚配置的数据库信息


public class clientDaoTest {
    @Autowired
    private clientDao clientDao1;

    /*
    FindOne--按照id查找
     */
    @Test
    public void testFindOne() {
//        Client client = clientDao1.findById(3);
//        System.out.println("client --> " +client);


        Optional<Client> client = clientDao1.findById(3);
        System.out.println(client.get());
    }

}
