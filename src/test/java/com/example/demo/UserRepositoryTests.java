package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
 
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
 
    @Autowired
    private TestEntityManager entityManager;
     
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private RoleRepository roleRepo;
	
	@Test
	public void testCreateUser()
	{
		User user = new User();
	    user.setEmail("veerubhotlaharika@gmail.com");
	    user.setPassword("harika282002");
	    user.setFirstName("harikasri");
	    user.setLastName("veerubhotla");
	    User savedUser = userRepo.save(user);
	     
	    User existUser = entityManager.find(User.class, savedUser.getId());
	     
	    assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
	}
	
	@Test
	public void testFindUserByEmail()
	{
		String email="harika@gmail.com";
		User user=userRepo.findByEmail(email);
		assertThat(user).isNotNull();
	}
	
	@Test
	public void testAddRoleToNewUser() {
	User user = new User();
	    user.setEmail("harikaveerubhotla28.com");
	    user.setPassword("harika28");
	user.setFirstName("harika");
	user.setLastName("veerubhotla");
	
	Role roleUser = roleRepo.findByName("User");
	user.addRole(roleUser);
    User savedUser = userRepo.save(user);
    assertThat(savedUser.getRoles().size()).isEqualTo(1);

	}
	
	
	@Test
	public void testAddRoleToExistingUser() {
	    User user = userRepo.findById(1L).get();
	    Role roleUser = roleRepo.findByName("User");  
	    user.addRole(roleUser);
	   
	    Role roleAdmin = new Role(2L);
	    
	    user.addRole(roleAdmin);
	     
	    User savedUser = userRepo.save(user);
	     
	    assertThat(savedUser.getRoles().size()).isEqualTo(2);      
	}
}

