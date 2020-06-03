package pl.jaworskimateuszm.myleagues.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.jaworskimateuszm.myleagues.mapper.UserMapper;
import pl.jaworskimateuszm.myleagues.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

	private UserMapper userMapper;

	public LoginController(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	@GetMapping("/home")
	public String showHomePage(HttpServletRequest request, HttpSession session) {
		User user = userMapper.findByUsername(request.getRemoteUser());
		session.setAttribute("user", user);
		return "home";
	}
	
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		return "access-denied";
	}
	
}