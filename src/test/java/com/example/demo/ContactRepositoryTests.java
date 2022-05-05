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
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class ContactRepositoryTests 
{
	@Autowired
	private ContactRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testcreatecontact()
	{
		Contact contact=new Contact();
		contact.setEmail("harikaveerubhotla28@gmail.com");
		contact.setName("harika");
		contact.setMessage("hello");
		
		Contact savedContact = repo.save(contact);
		
		
		Contact existContact = entityManager.find(Contact.class,savedContact.getId());
		
		assertThat(existContact.getEmail()).isEqualTo(contact.getEmail());
	}
}
