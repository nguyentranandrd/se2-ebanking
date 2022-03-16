package com.capt.ebankingbackend2022.security;


import com.capt.ebankingbackend2022.entity.AccountEntity;
import com.capt.ebankingbackend2022.entity.RoleEntity;
import com.capt.ebankingbackend2022.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class MyUserDetails implements UserDetailsService {

    @Autowired
    private AccountRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        final AccountEntity account = userRepository.findByPhoneNumber(phoneNumber);

        if (account == null) {
            throw new UsernameNotFoundException("User '" + phoneNumber + "' not found");
        }
        return User//
                .withUsername(phoneNumber)//
                .password(account.getPassword())//
                .authorities(mapRoleToAuthorities(account.getRoles()))//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }

    private Collection<? extends GrantedAuthority> mapRoleToAuthorities(Collection<RoleEntity> roles) {
        return roles.stream().map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getName())).collect(Collectors.toList());
    }

}
