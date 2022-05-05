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
public class ArmyRepositoryTests 
{
	@Autowired
	private ArmyRepository repo1;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateArmy()
	{
		Army army=new Army();
		army.setEmail("veerubhotlaharika@gmail.com");
		army.setName("Harika Veerubhotla");
		army.setMobile("7989935086");
		army.setAge("25");
		army.setCity("Vijayawada");
		
		Army savedArmy = repo1.save(army);
		Army existArmy = entityManager.find(Army.class, savedArmy.getId());
		
		assertThat(existArmy.getEmail()).isEqualTo(army.getEmail());
	}

}
