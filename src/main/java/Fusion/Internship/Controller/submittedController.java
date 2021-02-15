package Fusion.Internship.Controller;

import Fusion.Internship.DAO.User;
import Fusion.Internship.DAO.UserDAOImpl;
import Fusion.Internship.Model.login;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/submitted"})
public class submittedController {
    @GetMapping
    public ModelAndView homeView()
    {
        ModelAndView model = new ModelAndView();

        model.setViewName("submitted");

        return model;
    }
//    This is where I left off. TODO: understand http servlet request
    @GetMapping(value ={"/accepted"})
    public ModelAndView submitGoogleLogin(HttpServletRequest request, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("submitted");


        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken)request.getUserPrincipal();
        //System.out.println(token.getPrincipal().getAttributes());
        User user = new User();
        user.setEmail((String) token.getPrincipal().getAttributes().get("email"));
        String lastName = (String) token.getPrincipal().getAttributes().get("family_name");
        String username = (String) token.getPrincipal().getAttributes().get("given_name") + lastName.charAt(0);
        user.setUsername(username);
        UserDAOImpl.insertUser(user);
        login login = new login();
        login.setMessage("Hello " +user.getUsername()+", Welcome back!");
        model.addAttribute("login", login);


        return mv;
    }

}
