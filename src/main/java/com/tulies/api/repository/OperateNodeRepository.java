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

    OperateNode findByCid(String cid);
}
