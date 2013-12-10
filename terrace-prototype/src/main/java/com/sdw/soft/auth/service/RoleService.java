package com.sdw.soft.auth.service;

import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.sdw.soft.auth.dao.PrivilegeDao;
import com.sdw.soft.auth.dao.RoleDao;
import com.sdw.soft.auth.dao.RoleR2PrivilegeDao;
import com.sdw.soft.auth.dao.UserR2RoleDao;
import com.sdw.soft.auth.entity.Privilege;
import com.sdw.soft.auth.entity.Role;
import com.sdw.soft.auth.entity.RoleR2Privilege;
import com.sdw.soft.auth.entity.UserR2Role;
import com.sdw.soft.core.dao.BaseDao;
import com.sdw.soft.core.pagination.GroupPropertyFilter;
import com.sdw.soft.core.service.BaseService;

/**
 * @author syd
 * @Date 2013年12月4日
 * @version 1.0.0
 * Copyright (c) 2013
 */
@Service
@Transactional
public class RoleService extends BaseService<Role, String> {
	@Autowired
    private RoleDao roleDao;

    @Autowired
    private PrivilegeDao privilegeDao;

    @Autowired
    private UserR2RoleDao userR2RoleDao;

    @Autowired
    private RoleR2PrivilegeDao roleR2PrivilegeDao;

    @Override
    protected BaseDao<Role, String> getEntityDao() {
        return roleDao;
    }
    
    @Transactional(readOnly = true)
    public List<Role> findAllCached() {
        return roleDao.findAllCached();
    }

    @Transactional(readOnly = true)
    public List<Role> findR2RolesForUser(String userId) {
        List<Role> roles = Lists.newArrayList();
        Iterable<UserR2Role> r2s = userR2RoleDao.findEnabledRolesForUser(userId);
        for (UserR2Role r2 : r2s) {
            roles.add(r2.getRole());
        }
        return roles;
    }
    
    @Transactional(readOnly = true)
    public Page<RoleR2Privilege> findRelatedRoleR2PrivilegesForRole(final String roleId,
            final GroupPropertyFilter groupFilter, Pageable pageable) {
        Specification<RoleR2Privilege> specification = new Specification<RoleR2Privilege>() {
            @Override
            public Predicate toPredicate(Root<RoleR2Privilege> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Predicate predicate = buildPredicatesFromFilters(groupFilter, root, query, builder);
                return builder.and(predicate,builder.equal(root.get("role").get("id"), roleId));
            }
        };
        return roleR2PrivilegeDao.findAll(specification, pageable);
    }

    //@CacheEvict(value="SpringSecurityCache",allEntries=true)
    public void addUnRelatedPrivilegeR2s(String roleId, Collection<String> privilegeIds) {
        Role role = roleDao.findOne(roleId);
        Iterable<Privilege> privileges = privilegeDao.findAll(privilegeIds);
        for (Privilege privilege : privileges) {
            RoleR2Privilege r2 = new RoleR2Privilege();
            r2.setRole(role);
            r2.setPrivilege(privilege);
            roleR2PrivilegeDao.save(r2);
        }
    }

    //@CacheEvict(value="SpringSecurityCache",allEntries=true)
    public void deleteRelatedPrivilegeR2s(String roleId, Collection<String> r2Ids) {
        Role role = roleDao.findOne(roleId);
        Iterable<RoleR2Privilege> r2s = roleR2PrivilegeDao.findAll(r2Ids);
        for (RoleR2Privilege r2 : r2s) {
            //确认检查传入的关联主键是否的确属于当前关联角色
            if (r2.getRole().equals(role)) {
                roleR2PrivilegeDao.delete(r2);
            }
        }
    }


    @Override
    //@CacheEvict(value="SpringSecurityCache",allEntries=true)
    public Role save(Role entity) {
        return super.save(entity);
    }

    @Override
   // @CacheEvict(value="SpringSecurityCache",allEntries=true)
    public void delete(Role entity) {
        super.delete(entity);
    }
    
    @Override
    //@CacheEvict(value="SpringSecurityCache",allEntries=true)
    public List<Role> save(Iterable<Role> entities) {
        return super.save(entities);
    }

    @Override
   // @CacheEvict(value="SpringSecurityCache",allEntries=true)
    public void delete(Iterable<Role> entities) {
        super.delete(entities);
    }
}
