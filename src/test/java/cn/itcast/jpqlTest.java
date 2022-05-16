package cn.itcast;
//测试自己写的方法
//自己使用jpql，sql来写方法---实现增删改查，一般在只带的方法没有的时候，再自己写

import cn.itcast.dao.ClientDao;
import cn.itcast.domain.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void TestJpql() {//根据自己写的--name查找
        Client client = clientDao.OneselfFuncFindName("老六");
        System.out.println("自己写的'查找'方法 --> " + client);
    }

    @Test
    public void TestJpql1() {//根据自己写的--name和id查找
        Client client = clientDao.FindNameAndId("张三", 1);
        System.out.println("自己写的'ID和Name查找'方法 --> " + client);
    }

    @Test//使用jpdl--进行修改
    @Transactional//添加--事务
    @Rollback(value = false)//事务的回滚-false就是不回来，true表示回滚，那值就不会改变
    public void TestJpql2() {//根据自己写的--修改数据
        clientDao.modification(1, "我不是张三");
    }

    @Test
    public void TestFuncName() {//根据--"方法命名查询"
        Client client = clientDao.findByName("我不是张三");
        System.out.println("方法命名查询 --> " + client);
    }

    @Test
    public void TestFuncNameLike() {
        //根据--"方法命名查询"--之模糊查询,因为是模糊查询，要用集合来接，不然回爆出- query did not return a unique result: 3
        List<Client> client = clientDao.findByNameLike("老%");
        System.out.println("方法命名查询之模糊查询 --> " + client);
    }

    @Test
    public void FindByNameLikeAndJob() {
        //使用-方法命名查询--之多条件查询
        //方法命名查询，findBy+实体类名字（首字母大写）+效果（Like模糊查询）+（连接符号and/or）+实体类名字+效果
        List<Client> client = clientDao.findByNameLikeAndJob("老%", "混子");
        System.out.println("方法命名查询之多条件查询 --> " + client);
    }


}
