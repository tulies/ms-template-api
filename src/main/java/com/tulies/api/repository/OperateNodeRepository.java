package com.tulies.api.repository;

import com.tulies.api.entity.OperateNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OperateNodeRepository extends JpaRepository<OperateNode,Integer>, JpaSpecificationExecutor {
    //上下线
    @Modifying
    @Query("update OperateNode a set a.status=:status where a.id=:id")
    int changeStatus(@Param("id") Integer id, @Param("status") Integer status);

    OperateNode findByNid(Integer nid);

    @Modifying
    @Query("update OperateNode a set a.namePath = replace( a.namePath, :namePath, :mewNamePath ) where a.namePath like concat(:namePath, '%')")
    int updateChildNodeNamePath(@Param("namePath") String namePath, @Param("mewNamePath") String mewNamePath);

//    @Modifying
//    @Query(value = "UPDATE operate_node SET name_path= REPLACE(name_path,'/门户首页/国庆版大首页','/门户首页/国庆版大首页2') WHERE name_path LIKE '/门户首页/国庆版大首页%'",nativeQuery = true)
//    int updateChildNodeNamePath(String namePath, String mewNamePath);
}
