package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
   
	@Autowired
	private UserService service;
	
	@Autowired
	private ContactRepository repo;
	
	
	@Autowired
	private ArmyRepository repo1;
	
	@Autowired
	private ShareRepository repo2;
	
	@Autowired
	private RequestRepository repo3;

    
	@GetMapping("")
	public String viewHomePage()
	{
		return "index";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
	    model.addAttribute("user", new User());
	     
	    return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
	    service.saveUserWithDefaultRole(user);
	    return "login";
	}
	
	@GetMapping("/list_users")
	public String listUsers(Model model) {
	    List<User> listUsers = service.listAll();
	    model.addAttribute("listUsers", listUsers);
	    return "users";
	}
	
	
	@GetMapping("/register_success")
	public String loginsu(Model model)
	{
		return "register_success";
	}
	
	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable("id") Long id, Model model)
	{ 
		User user=service.get(id);
		List<Role> listRoles = service.getRoles();
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		return "user_form";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user)
	{
		service.save(user);
		
		return "redirect:/list_users";
	}
	
	@GetMapping("/admin")
	public String openadminhome(User user)
	{
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String showLoginPage()
	{
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken)
		{
			return "login";
		}
		else
		{
			return "redirect:/register_success";
		}
	}
	
	@PostMapping("/login")
	public String afterlogin()
	{
		return "register_success";
	}
	
	@GetMapping("/admin/")
	public String adminhome()
	{
		return "adminhome";
	}

	@GetMapping("/about")
	public String about()
	{
		return "about";
	}
	
	@GetMapping("/services")
	public String services()
	{
		return "services";
	}
	
	@GetMapping("/contact")
	public String contact(Model model)
	{
		model.addAttribute("contact",new Contact());
		return "contact";
	}
	

	@PostMapping("/process_contact")
	public String processContact(Contact contact)
	{
		repo.save(contact);
		return "redirect:/contact";
	}
	
	
	
	@GetMapping("/army")
	public String army(Model model)
	{
		model.addAttribute("army",new Army());
		return "army";
	}
	

	@PostMapping("/process_army")
	public String processArmy(Army army)
	{
		repo1.save(army);
		return "redirect:/army";
	}
	
	
	
	@GetMapping("/share")
	public String Share(Model model)
	{
		model.addAttribute("share",new Share());
		return "share";
	}
	

	@PostMapping("/process_share")
	public String processShare(Share share)
	{
		repo2.save(share);
		return "redirect:/share";
	}
	
	@GetMapping("/request")
	public String Request(Model model)
	{
		model.addAttribute("request",new Request());
		return "request";
	}
	

	@PostMapping("/process_request")
	public String processShare(Request request)
	{
		repo3.save(request);
		return "redirect:/request";
	}
	
}
