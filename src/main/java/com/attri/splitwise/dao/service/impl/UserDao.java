package com.attri.splitwise.dao.service.impl;

import com.attri.splitwise.dao.entity.User;
import com.attri.splitwise.dao.repo.IUserRepository;
import com.attri.splitwise.dao.service.IUserDao;
import org.springframework.stereotype.Service;

@Service
public class UserDao extends AbstractDaoImpl<User, Long, IUserRepository> implements IUserDao {

}
