package com.hospitaltask.repository;

import com.hospitaltask.entity.SuperAdmin;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperAdminRepo extends JpaRepository<SuperAdmin, Long> {
    String deleteByEmail(String id);
    //TODO...
    //    @Query(value = " select * from tbl_super_user",nativeQuery = true)
    @Query(value = " select * from tbl_super_user where flag=true", nativeQuery = true)
    List<SuperAdmin> findAll();
    @Query(value = "select * from tbl_super_user where email=:email",nativeQuery = true)
    SuperAdmin findByEmail(String email);

   /* @Query(value = "update tbl_super_user set flag=false where id=1",nativeQuery=true)
    void disableById(long id);
    @Modifying
    @Query(value = "update SuperAdmin sa set sa.flag=true where sa.id=:id")
    void enableById(@Param("id") long id);

    @Query(value = "update tbl_super_user set flag=false where email=:id",nativeQuery=true)
    void disableByEmail(String id);

    @Query(value = "update tbl_super_user set flag=true where email=:id",nativeQuery=true)
    void enableByEmail(String id);
*/

}
