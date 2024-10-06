/**
 * Created By Eng. Pius Obonyo
 * Date: 6/13/24
 * Time: 5:23 PM
 */
package com.dfcu.HR_Management_System.repository;

import com.dfcu.HR_Management_System.entity.Role;
import com.dfcu.HR_Management_System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);

    Boolean existsByEmployeeNumber(String employeeNumber);

    User findByEmployeeNumber(String accountNumber);

    Optional<User> findFirstByEmail(String username);

    User findByRole(Role roles);

    Optional<User> findByEmail(String loggedInEmail);
}
