package cn.itcast;
//测试自己写的方法

import cn.itcast.dao.ClientDao;
import cn.itcast.domain.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)//spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:application.xml")//指定Spring容器的配置---对应到自己写的配置里面
//有了@ContextConfiguration(locations = "classpath:application.xml")这个，就可以从里面调出刚刚配置的数据库信息


public class jpqlTest {
    /*
    把ClientDao私有化，给出一个clientDao出口，可以让大家调用ClientDao
     */
    @Autowired
    private ClientDao clientDao;

    @Test
    public void TestJpql() {
        Client client = clientDao.OneselfFuncFindName("老六");
        System.out.println("自己写的'查找'方法 --> " + client);
    }


}
