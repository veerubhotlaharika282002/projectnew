package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class Sdp3Application 
{
	@Autowired
	private SendEmailService sendEmailService;
	
	public static void main(String[] args) {
		SpringApplication.run(Sdp3Application.class, args);
		System.out.println("Successfully launched");
	}
	@EventListener(ApplicationReadyEvent.class)
	public void triggerWhenStarts()
	{
		sendEmailService.sendEmail("veerubhotlaharika@gmail.com", "City: Vizag"
				                                                + " " + "Name:Veerubhotla harika"+ " "+   "9878765456"+ " "+  "Quantityoffood:10kgs" ,"Details of Food Donating Persion");
	}
}
