package cn.itcast.dao;

import cn.itcast.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/*
SpringDataJpa--接口
JpaRepository<T,Id>T=操作类实体类类范型，ID=实体类主键属性的类范型
                   T=主题的类名class后面的   ID主键的类型

JpaSpecificationExecutor<T>  T=操作类实体类类范型
 */
public interface clientDao extends JpaRepository<Client, Integer>, JpaSpecificationExecutor<Client> {

}
