/**
 * Created By Eng. Pius Obonyo
 * Date: 6/21/24
 * Time: 6:12 PM
 */

package com.dfcu.HR_Management_System.jwt;

import com.dfcu.HR_Management_System.entity.User;
import com.dfcu.HR_Management_System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmployeeNumber(username));
        return optionalUser.map(ourUserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist!"));
    }
}

