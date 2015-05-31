package org.arago.showcase.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.arago.showcase.model.Login;
import org.arago.showcase.model.Session;
import org.arago.showcase.model.TrelloSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"login", "session"})
public class LoginController {
	private static final String LOGIN = "/login";
	private static final String VALIDATE_LOGIN = "/validateLogin";
	
	
	@ModelAttribute("login")
	public Login createLoginBean() {
		return new Login();
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login() {
	}
	
	@RequestMapping(value = LOGIN, method=RequestMethod.POST)
	public String login(Model model, Login login, 
			HttpServletRequest request, BindingResult result) {
	    if(!model.containsAttribute("session")) {
	    	Session session = new TrelloSession();
	    	session.startAuthorization(login.getKey(), login.getSecret(), getCallbackURL(request));
	    	model.addAttribute("session", session);
	    	return "redirect:" + session.getAuthFlowURL();
	    }
	    
	    return "redirect:/boards";
	}
	
	@RequestMapping(value = VALIDATE_LOGIN, method = RequestMethod.GET)
	public String validateLogin(@ModelAttribute("session") Session session, 
			@RequestParam("oauth_token") String oauthToken, 
			@RequestParam("oauth_verifier") String oauthVerifier) {
		if(session != null && session.getClient() == null) {
			session.createtClientSession(oauthVerifier);
			return "forward:/boards";
		}
		return "forward:/logout";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap modelMap, HttpSession session) {
		modelMap.remove("session");
	    session.removeAttribute("session");
    	return "redirect:/";
	}
	
	private String getCallbackURL(HttpServletRequest request) {
    	String requestURL = request.getRequestURL().toString();
    	StringBuffer callbackURL = new StringBuffer(
    			requestURL.substring(0, requestURL.lastIndexOf(LOGIN)));
    	callbackURL.append(VALIDATE_LOGIN);
    	return callbackURL.toString();
	}
}
