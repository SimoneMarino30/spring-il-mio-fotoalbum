package org.lessons.springilmiofotoalbum.repository;


import org.lessons.springilmiofotoalbum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // uso la convenzione dei nomi per un metodo che recupera uno user a partire dall' email(che sarebbe poi lo username)
    Optional<User> findByEmail(String email);
}
