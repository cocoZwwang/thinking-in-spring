package thinking.in.spring.ioc.bean.scope.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import thinking.in.spring.ioc.container.overview.domain.User;

@Controller
public class IndexController {


    private final User user;

    @Autowired
    public IndexController(User user) {
        this.user = user;
        System.out.println(user.getClass().getTypeName());
    }

    @GetMapping("/index")
    public String index(Model model) {
        System.out.println(user);
        model.addAttribute("user", user);

        return "index";
    }
}
