package com.irm.demo.service;

import com.irm.demo.po.Blog;
import com.irm.demo.po.LibraryUsers;

public interface LibarayUserService {


    LibraryUsers checkLibarayUsers(String username,String password);

    LibraryUsers saveUserTime(LibraryUsers libraryUsers);

    LibraryUsers savelibrary(LibraryUsers libraryUsers);

    LibraryUsers getByUsernmae(String username);


    LibraryUsers updateStatus(int status);
}
