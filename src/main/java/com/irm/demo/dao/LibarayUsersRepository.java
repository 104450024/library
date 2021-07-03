package com.irm.demo.dao;

import com.irm.demo.po.LibraryUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LibarayUsersRepository extends JpaRepository<LibraryUsers,Integer> {


    LibraryUsers findByUsernameAndPassword(String username,String password);

    LibraryUsers findByStatus(Integer status);

    LibraryUsers findByUsername(String username);

    LibraryUsers findByStatus(int status);

    @Query("update LibraryUsers l set l.status=l.status-2 where l.status = ?1")
    int updateStatus(long status);


}

