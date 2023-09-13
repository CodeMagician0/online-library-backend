package com.codemagician.onlinelibrary.service.impl;

import com.codemagician.onlinelibrary.dao.repo.RoleRepository;
import com.codemagician.onlinelibrary.dao.repo.UserRepository;
import com.codemagician.onlinelibrary.domain.entity.RoleDO;
import com.codemagician.onlinelibrary.domain.entity.UserDO;
import com.codemagician.onlinelibrary.domain.req.SignupReq;
import com.codemagician.onlinelibrary.domain.rsp.JwtRsp;
import com.codemagician.onlinelibrary.domain.vo.UserVO;
import com.codemagician.onlinelibrary.enums.RoleEnum;
import com.codemagician.onlinelibrary.exception.BusinessException;
import com.codemagician.onlinelibrary.security.jwt.JwtUtils;
import com.codemagician.onlinelibrary.security.services.UserDetailsImpl;
import com.codemagician.onlinelibrary.service.UserService;
import com.codemagician.onlinelibrary.domain.req.LoginReq;
import com.codemagician.onlinelibrary.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Siuyun Yip
 * @version 1.0
 * @date 2023/9/2 14:14
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public JwtRsp authenticateUser(LoginReq req) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtRsp(userDetails.getId(),
                jwt,
                "Bearer",
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);

    }

    @Override
    public void registerUser(SignupReq req, boolean isAdmin) {
        if (userRepository.existsByUsername(req.getUsername())) {
            throw new BusinessException("Username was already taken.");
        }

        if (userRepository.existsByEmail(req.getEmail())) {
            throw new BusinessException("Email was already in use.");
        }

        UserDO user = new UserDO(req.getUsername(), req.getEmail(), encoder.encode(req.getPassword()));

        Set<RoleDO> roles = new HashSet();
        // default role: user role
        RoleDO userRole = roleRepository.findByName(RoleEnum.ROLE_USER)
                .orElseThrow(() -> new BusinessException("Role is not found"));
        roles.add(userRole);

        // register admin through backdoor
        if (isAdmin) {
            RoleDO adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN)
                    .orElseThrow(() -> new BusinessException("Role is not found"));
            roles.add(adminRole);
        }

        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public UserVO getUser(Long userId) {
        Optional<UserDO> userOption = userRepository.findById(userId);

        if (!userOption.isPresent()) {
            throw new BusinessException("User not exist");
        }

        return ObjectMapperUtils.map(userOption.get(), UserVO.class);
    }
}
