package cn.itcast;
//这里是--动态查询--使用的是-JpaSpecificationExecutor

import cn.itcast.dao.ClientDao;
import cn.itcast.domain.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)//spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:application.xml")//指定Spring容器的配置---对应到自己写的配置里面
//有了@ContextConfiguration(locations = "classpath:application.xml")这个，就可以从里面调出刚刚配置的数据库信息


public class SpecTest {
    /*
    把ClientDao私有化，给出一个clientDao出口，可以让大家调用ClientDao
     */
    @Autowired
    private ClientDao clientDao;

    //1。
    @Test//动态-单个查询
    public void TestSpec() {
        //匿名内部类
        /*
        1.实现specification接口（提供范型，查询的对象类型）
        2.toPredicate--构造查询条件
        3.需要借助方法参数中的两个参数（
        root：获取需要查询的对象属性
         CriteriaBuilder：构造查询条件，内部封装了很多查询方法
        ）
         */
        Specification<Client> spec = new Specification<Client>() {
            public Predicate toPredicate(Root<Client> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                // 1.获取比较的属性
                Path<Object> name = root.get("name");
                // 2. 构造查询条件  select * from cst_customer where  name = '张三'

                /**
                 *   第一个参数：需要比较的属性（path对象）
                 *   第二个参数：当前需要比较的取值
                 */
                Predicate predicate = cb.equal(name, "李"); // 进行精准匹配（比较的属性，比较的属性的取值）
                return predicate;
            }
        };
        Optional<Client> client = clientDao.findOne(spec);
        System.out.println("client --> " + client);
    }


    //2。
    @Test
    public void TestSpec2() {//动态-多个查询
        Specification<Client> spec = new Specification<Client>() {
            public Predicate toPredicate(Root<Client> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> name = root.get("name");
                Path<Object> job = root.get("job");
                Predicate predicate1 = cb.equal(name, "老东西"); // 进行精准匹配（比较的属性，比较的属性的取值）
                Predicate predicate2 = cb.equal(job, "混子");
                Predicate And = cb.and(predicate1, predicate2);
                return And;
            }
        };
        Optional<Client> client = clientDao.findOne(spec);
        System.out.println("client --> " + client);
    }

    //3。


}


