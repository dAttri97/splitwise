package com.attri.splitwise.dao.repo;

import com.attri.splitwise.dao.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends IBaseRepository<User, Long>{
}
