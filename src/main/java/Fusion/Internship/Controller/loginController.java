package Fusion.Internship.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Fusion.Internship.DAO.User;
import Fusion.Internship.DAO.UserDAO;
import Fusion.Internship.DAO.UserDAOImpl;
import Fusion.Internship.Model.login;


@Controller
@RequestMapping(value = { "/login" })
public class loginController {

    //@Autowired
    //private UserDAO UserDAO;

    @GetMapping(value = "/database")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }
    @PostMapping
    public String submitLogin(@RequestParam(name="username") String username, @RequestParam(name= "password") String password, Model model) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        User regUser = UserDAOImpl.userLogin(user);
        if(regUser.getUsername() != "NO") {
            login login = new login();
            login.setMessage("Hello " +regUser.getUsername()+", Welcome back!");
            model.addAttribute("login", login);
            return "submitted";
        }
        else {
            login login = new login();
            regUser.setUsername(user.getUsername());
            login.setMessage("The user, " +regUser.getUsername()+", was not found. Please try again!");
            model.addAttribute("login", login);
            return "submitted";
        }
    }
}
