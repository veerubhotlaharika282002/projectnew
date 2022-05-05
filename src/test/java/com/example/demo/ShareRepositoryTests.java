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
public class ShareRepositoryTests 
{
	@Autowired
	private ShareRepository repo2;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testcreateshareuserdetails()
	{
		Share share=new Share();
		share.setAddress("D.NO:1-4-226/7A/B,Bhavanipuram");
		share.setAge("23");
		share.setCity("Hyderabad");
		share.setEmail("harikaveerubhotla28");
		share.setMobile("7989935086");
		share.setName("Harika Veerubhotla");
		share.setQuantityoffood("4kgs");
		
		
        Share savedShare = repo2.save(share);
		
		
		Share existShare = entityManager.find(Share.class,savedShare.getId());
		
		assertThat(existShare.getEmail()).isEqualTo(share.getEmail());
	}

}
