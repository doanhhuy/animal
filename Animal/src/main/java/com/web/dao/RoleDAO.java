package com.web.dao;

import com.web.entity.backend.Role;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by duyle on 21/02/2017.
 */
@Transactional
@Repository
public class RoleDAO implements IRoleDAO {

    @Autowired
    HibernateTemplate hibernateTemplate;

    public List<Role> getAllRoles() {
        String hql = "FROM Role as p ORDER BY p.id";
        
        hibernateTemplate.setMaxResults(0);
        return (List<Role>) hibernateTemplate.find(hql);
    }

    public Role getRoleById(int id) {
        
        hibernateTemplate.setMaxResults(0);
        return hibernateTemplate.get(Role.class, id);
    }

    public int addRole(Role role) {
        return (Integer) hibernateTemplate.save(role);
    }

    public void updateRole(Role role) {
        Role r = getRoleById(role.getId());
        r.setRoleName(role.getRoleName());
        r.setDetail(role.getRoleName());
        hibernateTemplate.merge(r);
    }

    public boolean isRoleExistByRole(Role role) {
        String hql = "FROM Role Where roleName = '" + role.getRoleName() +"' and id <> " + role.getId();
        
        hibernateTemplate.setMaxResults(0);
        List<Role> roleList = (List<Role>) hibernateTemplate.find(hql);
        if(roleList.size() == 0){
            return false;
        } else {
            return true;
        }
    }

    public Role getRoleByRoleName(String roleName) {
        String hql = "FROM Role Where roleName = '" + roleName +"'";
        
        hibernateTemplate.setMaxResults(0);
        List<Role> roleList = (List<Role>) hibernateTemplate.find(hql);
        if(roleList.size() == 0){
            return null;
        } else {
            return roleList.get(0);
        }
    }
}
