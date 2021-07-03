package com.irm.demo.service;

import com.irm.demo.dao.LibarayUsersRepository;
import com.irm.demo.dao.UserRepository;
import com.irm.demo.po.LibraryUsers;
import com.irm.demo.po.User;
import com.irm.demo.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Service
public class LibraryUserServiceImpl implements LibarayUserService {

    private final LibarayUsersRepository libarayUsersRepository;

    @Autowired
    public LibraryUserServiceImpl(LibarayUsersRepository libarayUsersRepository) {
        this.libarayUsersRepository = libarayUsersRepository;
    }

    @Override
    public LibraryUsers checkLibarayUsers(String username, String password) {
        LibraryUsers libraryuser = libarayUsersRepository.findByUsernameAndPassword(username,password);
        return libraryuser;
    }



    @Override
    public LibraryUsers saveUserTime(LibraryUsers libraryUsers) {
        Date date = new Date();
        libraryUsers.setUpdateTime(date);

        return libarayUsersRepository.save(libraryUsers);
    }

    @Override
    public LibraryUsers savelibrary(LibraryUsers libraryUsers) {

        return  libarayUsersRepository.save(libraryUsers);

    }

    @Override
    public LibraryUsers getByUsernmae(String username) {


        return libarayUsersRepository.findByUsername(username);
    }


    @Override
    public LibraryUsers updateStatus(int status) {

        LibraryUsers libraryUsers=new LibraryUsers();
        libarayUsersRepository.updateStatus(status);

        return libraryUsers;
    }


}
