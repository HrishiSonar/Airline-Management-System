package com.app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.app.daos.UserDao;
import com.app.entities.UserDetailsEntity;
import com.app.entities.UserRole;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class UserEntityDaoTest {
	// dep
	@Autowired
	private UserDao userRepo;

	@Autowired
	private PasswordEncoder enc;

	@Test
	void testAddUsers() {
		List<UserDetailsEntity> list = List.of(
				new UserDetailsEntity("a1",  "a1@gmail.com", enc.encode("12345"), UserRole.ROLE_ADMIN),
				new UserDetailsEntity("a2",  "a2@gmail.com", enc.encode("2345"), UserRole.ROLE_USER),
				new UserDetailsEntity("a3", "a3@gmail.com", enc.encode("1345"), UserRole.ROLE_USER));
		List<UserDetailsEntity> list2 = userRepo.saveAll(list);
		assertEquals(3, list2.size());

	}

}
