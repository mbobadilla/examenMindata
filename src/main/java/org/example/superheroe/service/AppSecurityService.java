package org.example.superheroe.service;

import org.example.superheroe.model.UserDB;
import org.example.superheroe.repository.UserDBRepository;
import org.example.superheroe.utils.CodigoErrorEnum;
import org.example.superheroe.utils.RolEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppSecurityService implements UserDetailsService {

    @Autowired
    private UserDBRepository appUserRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDB> userDb = appUserRepository.findByUsername(username);
        if (userDb.isPresent()) {
            List<GrantedAuthority> authorities = new ArrayList();
            if(userDb.get().getRol().equals(RolEnum.ADMIN.name())) {
                authorities=AuthorityUtils.createAuthorityList(RolEnum.ADMIN.name());
            }
            return new User(userDb.get().getUsername(),userDb.get().getPassword(),authorities);

        } else {
            throw new UsernameNotFoundException(CodigoErrorEnum.USER_NOT_FOUND.name());
        }
    }



}
