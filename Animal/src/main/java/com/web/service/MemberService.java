package com.web.service;

import com.web.dao.IMemberDAO;
import com.web.entity.backend.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * Created by duyle on 20/02/2017.
 */

@Service
public class MemberService implements IMemberService {

    @Autowired
    IMemberDAO memberDAO;

    public List<Members> getAllMembers() {
        return memberDAO.getAllMembers();
    }

    public Members getMemberById(int id) {
        return memberDAO.getMemberById(id);
    }

    public int addMember(Members members) {
        return memberDAO.addMember(members);
    }

    public void updateMember(Members members) {
        memberDAO.updateMember(members);
    }

    public Members isMemberExistByMember(String fullName, Date birthday) {
        return memberDAO.isMemberExistByMember(fullName, birthday);
    }
}
