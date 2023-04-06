package com.hospitaltask.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hospitaltask.entity.SuperAdmin;

public interface SuperAdminRepo extends JpaRepository<SuperAdmin, Long> {
    String deleteByEmail(String id);

    //TODO...
    //    @Query(value = " select * from tbl_super_user",nativeQuery = true)
    @Query(value = " select * from tbl_super_user where flag=true", nativeQuery = true)
    List<SuperAdmin> findAll();

    @Query(value = "select * from tbl_super_user where email=:email ", nativeQuery = true)
    SuperAdmin findByEmail(String email);


    @Query(value = "select * from tbl_super_user d where d.id =:id and d.flag=:aBoolean", nativeQuery = true)
    List<SuperAdmin> findSuperAdminByFlag(Integer id, Boolean aBoolean);

    @Query(value = "select * from tbl_super_user d where d.email =:id and d.flag=:aBoolean", nativeQuery = true)
    List<SuperAdmin> findSuperAdminByEmailAndFlag(String id, Boolean aBoolean);

    @Query(value = "select Id from tbl_super_user where email=:email", nativeQuery = true)
    Long findUserIDByEmail(String email);

    
    
    @Query(value = "select * from tbl_super_user d where d.email =:email and d.confirmation_token=:token",nativeQuery = true)
    SuperAdmin accountVerify(String email,String token);


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
