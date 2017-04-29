package com.web.service;

import com.web.entity.backend.Members;

import java.sql.Date;
import java.util.List;

/**
 * Created by duyle on 20/02/2017.
 */
public interface IMemberService {

    List<Members> getAllMembers();

    Members getMemberById(int id);

    int addMember(Members members);

    void updateMember(Members members);

    Members isMemberExistByMember(String fullName, Date birthday);

}
