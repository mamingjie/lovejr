package com.mamingjie.lovejr.service;

import com.mamingjie.lovejr.model.Role;
import com.mamingjie.lovejr.model.RoleRepository;
import com.mamingjie.lovejr.model.User;
import com.mamingjie.lovejr.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(s);

        if (user == null) {
            throw new UsernameNotFoundException("User " + s + " not found");
        }

        return new DefaultUserDetails(user, roleRepository.findRolesByUserId(user.getId()));
    }

    public static class DefaultUserDetails implements UserDetails {

        public DefaultUserDetails(User user, List<Role> roles) {
            setUser(user);
            setAuthorities(roles);
        }

        private User user;

        private List<GrantedAuthority> authorities;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getName();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return user.getStatus() != 0;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return user.getStatus() == 2;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public void setAuthorities(List<Role> roles) {
            authorities = new ArrayList<>(roles.size());
            roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        }

    }
}
