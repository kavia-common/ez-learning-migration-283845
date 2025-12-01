package com.ezlearning.platform.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service that loads user details for Spring Security authentication.
 * Uses the database-backed repositories to fetch the user and associated roles.
 */
@Service
public class EzLearningUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthGroupRepository authGroupRepository;

    public EzLearningUserDetailsService(UserRepository userRepository, AuthGroupRepository authGroupRepository) {
        super();
        this.userRepository = userRepository;
        this.authGroupRepository = authGroupRepository;
    }

    // PUBLIC_INTERFACE
    /**
     * Loads a user by username from the persistent store and maps it into a Spring Security
     * {@link UserDetails} with roles derived from {@link AuthGroup}.
     *
     * @param username the username to lookup
     * @return a Spring Security {@link UserDetails} instance representing the user
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No se encuentra usuario:" + username);
        }
        List<AuthGroup> authGroups = this.authGroupRepository.findByUsername(username);
        return new UserPrincipal(user, authGroups);
    }
}
