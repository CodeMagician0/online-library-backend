package com.codemagician.onlinelibrary.security.services;

import com.codemagician.onlinelibrary.domain.entity.UserDO;
import com.codemagician.onlinelibrary.dao.repo.UserRepository;
import com.codemagician.onlinelibrary.common.exception.AccessException;
import com.codemagician.onlinelibrary.util.RedisUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/8/29 17:05
 *
 *  Dedicated service of retrieving user details for Spring Security
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Resource
    private RedisUtil redisUtil;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws AccessException {
        UserDO user;
        if ((user = (UserDO)redisUtil.get(username)) == null) {
            user = userRepository.findByUsernameWithRoles(username).orElseThrow(() -> new AccessException("User Not Found with username: " + username));
            redisUtil.set(username, user);
        }

        return UserDetailsImpl.build(user);
    }
}
