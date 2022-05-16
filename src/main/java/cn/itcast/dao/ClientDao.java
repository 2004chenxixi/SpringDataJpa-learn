package cn.itcast.dao;

import cn.itcast.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/*
SpringDataJpa--接口
JpaRepository<T,Id>T=操作类实体类类范型，ID=实体类主键属性的类范型
                   T=主题的类名class后面的   ID主键的类型

JpaSpecificationExecutor<T>  T=操作类实体类类范型
 */
public interface ClientDao extends JpaRepository<Client, Integer>, JpaSpecificationExecutor<Client> {
    //在这里写方法，进行jpdl的查询
    //自己创建方法，写更具客户别的信息，查询
    //jdpl 更具名字查询 ----->  from Client  where name =?
    @Query(value = " select *from Client  where name = ?", nativeQuery = true)
    public Client OneselfFuncFindName(String name);

    //细节
    //举一反三，还可以根据，job，email...查找
    /*
    根据email查找---还可以写很多
    百度查--jpql的语法
     */

    @Query(value = "select  *from Client  where email =?", nativeQuery = true)
    public Client FindInformationEmail(String email);


}
