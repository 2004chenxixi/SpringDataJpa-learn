package cn.itcast;
//这里是--动态查询--使用的是-JpaSpecificationExecutor
//使用-下面这样的格式，来查询
/*
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
 */

import cn.itcast.dao.ClientDao;
import cn.itcast.domain.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;
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
    //进行模糊查询
    @Test
    public void TestSpec3() {//动态-多个查询
        Specification<Client> spec = new Specification<Client>() {
            public Predicate toPredicate(Root<Client> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> name = root.get("name");
                Predicate predicate1 = cb.like(name.as(String.class), "老%");
                /*
                模糊查询和直接查询区别
               cb.equal(name, "李")-----cb.like(name.as(String.class),"老%")
                 */
                return predicate1;
            }
        };
        List<Client> client = clientDao.findAll(spec);
        System.out.println("client --> " + client);
    }

    //4。
    //模糊查询+结果的排序
    @Test
    public void TestSpec4() {//动态-多个查询
        Specification<Client> spec = new Specification<Client>() {
            public Predicate toPredicate(Root<Client> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> name = root.get("name");
                Predicate like = cb.like(name.as(String.class), "老%");
                /*
                模糊查询和直接查询区别
               cb.equal(name, "李")-----cb.like(name.as(String.class),"老%")
                 */
                return like;
            }
        };
        /*
        添加排序
        创建一个排序对象，需要调用构造方法实例化sort对象
        1。第一个参数，排序的顺序（倒叙，正序）
        sort.Direction.DESC--倒叙
        sort.Direction.ASC--正序
        2。第二个参数，排序的属性--需要按什么排序
         */
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Client> client = clientDao.findAll(spec, sort);
        System.out.println("client --> " + client);
    }

    //5.--分页
    /*
    分页
    findAll（Specification，Pageable）：带有条件的分页
    findAll（Pageable）：没有条件的分页
     */
    @Test
    public void TestSpec5() {
        Specification spec = null;
        /*
        PageRequest 是Pageable是Pageable接口的实现类
        PageRequest--要传入两个参数
        第一个参数：当前页（0表示第一页）
        第二个参数：每页查询的数字
         */
        Pageable pageable = PageRequest.of(0, 2);
        Page<Client> all = clientDao.findAll((Specification<Client>) null, pageable);
        System.out.println("得到数据集合列表 -->" + all.getContent());
        System.out.println("得到总条数 -->" + all.getTotalElements());
        System.out.println("得到总页数 -->" + all.getTotalPages());
    }


}


