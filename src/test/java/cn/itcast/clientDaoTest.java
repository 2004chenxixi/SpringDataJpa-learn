package cn.itcast;
//增删该查

import cn.itcast.dao.ClientDao;
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
    /*
    把ClientDao私有化，给出一个clientDao出口，可以让大家调用ClientDao
     */
    @Autowired
    private ClientDao clientDao;

    /*
    查找
    FindById--按照id查找----查找
     */
    @Test//查找
    public void testFindById() {

        Optional<Client> client = clientDao.findById(3);
        System.out.println(client.get());
    }

    //和getOne差不多，都是根据id查找，当时执行的时机不一样-----查找
    @Test//查找
    public void TestGetOne() {
        Client client = clientDao.getById(4);
    }

    /*
    高级查找(全部)(----find
     */
    @Test//查找全部
    public void AllFind() {
        System.out.println("FindAll --> " + clientDao.findAll());
    }

    /*
    sava方法
    根据-也没有id分为-保存和更新
    没有id--保存（创建一条新的"数据"）---sava
    有id--更新（"修改"确定id的那一条数据）---update
     */
    @Test//创建or修改
    public void testSave() {
        Client client2 = new Client();
        client2.setId(8);
        client2.setName("老六");
        client2.setEmail("2564564@");
        client2.setJob("职业老六");
        client2.setNumber(466435);
        clientDao.save(client2);
        System.out.println("client2 --> " + client2);
    }

    /*
    删除delete
     */
    @Test//删除
    public void Testdelete() {
        clientDao.deleteById(7);
    }

    /*
    统计客户的个数==count
     */
    @Test//统计客户数
    public void TestCount() {
        long client = clientDao.count();
        System.out.println("client --> " + client);
    }

    /*
    查找id=？是否存在
     */
    @Test//查看是否存在该用户
    public void TestExists() {
        boolean exists = clientDao.existsById(4);
        System.out.println("id为4的客户，是否存在=" + exists);
    }


}
