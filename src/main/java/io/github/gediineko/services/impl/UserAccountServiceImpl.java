package io.github.gediineko.services.impl;

import io.github.gediineko.repo.jpa.UserAccountRepo;
import io.github.gediineko.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by NazIsEvil on 15/10/2016.
 */
@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepo userAccountRepo;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userAccountRepo.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
