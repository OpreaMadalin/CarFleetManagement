package com.company.controller.authController;

import com.company.controller.databaseController.MongoController;
import com.company.controller.hashersController.HashAlgorithm;
import com.company.controller.hashersController.MadalinHasher;
import com.company.model.login.LoginRequestBody;
import com.company.model.register.RegisterRequestBody;
import org.bson.Document;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


@RestController
public class AuthController {

    private final HashAlgorithm hasher = new MadalinHasher();

    @PostMapping(value = "user/register", consumes = "application/x-www-form-urlencoded")
    public RedirectView register(RegisterRequestBody body, RedirectAttributes redirect) {
        String hashedPassword = hasher.saltAndHash(body.getPassword());
        MongoController mc = new MongoController();
        mc.addUser(body.getUsername(), hashedPassword);
        RedirectView redirectView = new RedirectView("/login", true);
        redirect.addFlashAttribute("message", "You successfully registered!");
        return redirectView;
    }

    @RequestMapping("user/login")
    public RedirectView login(LoginRequestBody body, RedirectAttributes redirect) {

        MongoController mc = new MongoController();
        Document result = mc.getUserWithUsername(body.getUsername());
        if (result == null) {
            RedirectView redirectView = new RedirectView("/loginBP", true);
            redirect.addFlashAttribute("message", "Incorrect Credentials!");
            return redirectView;
        }
        String referencePassword = (String) result.get("password");
        boolean isPasswordValid = hasher.checkPassword(referencePassword, body.getPassword());
        if (!isPasswordValid) {
            RedirectView redirectView = new RedirectView("/loginBP", true);
            redirect.addFlashAttribute("message", "Incorrect Credentials!");
            return redirectView;
        }
        return new RedirectView("/index", true);
    }

}
