package com.web.dao;

import com.web.entity.backend.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

/**
 * Created by duyle on 20/02/2017.
 */
@Transactional
@Repository
public class MemberDAO implements IMemberDAO{

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public List<Members> getAllMembers() {
        String hql = "FROM Members as p ORDER BY p.id";
        
        hibernateTemplate.setMaxResults(0);
        return (List<Members>) hibernateTemplate.find(hql);
    }

    public Members getMemberById(int id) {
        
        hibernateTemplate.setMaxResults(0);
        return hibernateTemplate.get(Members.class,id);
    }

    public int addMember(Members members) {
        int id = (Integer) hibernateTemplate.save(members);
        return id;
    }

    public void updateMember(Members members) {
        Members m = getMemberById(members.getId());
        m.setFullName(members.getFullName());
        m.setDetail(members.getDetail());
        m.setAddress(members.getAddress());
        m.setBirthday(members.getBirthday());
        m.setPhoneNumber(members.getPhoneNumber());
        hibernateTemplate.merge(m);
    }

    public Members isMemberExistByMember(String fullName, Date birthday) {
        String hql = "FROM Members Where fullName = '" + fullName + "' and birthday = '" + birthday +"'";
        hibernateTemplate.setMaxResults(0);
        
        List<Members> membersList = (List<Members>) hibernateTemplate.find(hql);
        if(membersList.size() == 0){
            return null;
        } else {
            return membersList.get(0);
        }
    }
}
