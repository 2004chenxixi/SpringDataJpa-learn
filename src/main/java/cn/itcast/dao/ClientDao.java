package cn.itcast.dao;

import cn.itcast.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/*
SpringDataJpa--接口
JpaRepository<T,Id>T=操作类实体类类范型，ID=实体类主键属性的类范型
                   T=主题的类名class后面的   ID主键的类型

JpaSpecificationExecutor<T>  T=操作类实体类类范型
 */
public interface ClientDao extends JpaRepository<Client, Integer>, JpaSpecificationExecutor<Client> {
    //1。
    //在这里写方法，进行jpdl的查询
    //自己创建方法，写更具客户别的信息，查询
    //jdpl 更具名字查询 ----->  select * from Client  where name =?
    @Query(value = " select *from Client  where name = ?", nativeQuery = true)
    public Client OneselfFuncFindName(String name);


    //2。细节
    //举一反三，还可以根据，job，email...查找
    /*
    根据email查找---还可以写很多
    百度查--jpql的语法
     */
    @Query(value = "select  *from Client  where email =?", nativeQuery = true)
    public Client FindInformationEmail(String email);


    //3。
    //更具-名字和id，来查询----->select * from Client  where name =? and id =?
    @Query(value = "select *from Client  where name =? and id =?", nativeQuery = true)
    public Client FindNameAndId(String name, int id);

    //4。
    /*
    修改和查询的不同处，1，在dao方法中+ @Modifying
                     2，在调用中，需要要1。开启事务@Transactional--2。停止回滚@Rollback(value = false)
     */
    //进行--"修改"操作
    //update Client set id = ? and name = ?
    @Query(value = "update Client set name = ?2 where id = ?1", nativeQuery = true)
    @Modifying //这个才表示--修改，@Query表示查找
    public void modification(int id, String name);

    //5。


}
