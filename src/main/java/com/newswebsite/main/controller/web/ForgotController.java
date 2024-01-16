package com.newswebsite.main.controller.web;

import com.newswebsite.main.utils.FlashMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/forgot")
public class ForgotController {

    @Value("${password.reset.email.sent}")
    String msg;

    @GetMapping
    public String getForgotPage() {
        return "web/forgot";
    }

    @PostMapping
    public String sendResetEmail(@RequestParam("email") String email,
                                 RedirectAttributes attributes) {
        attributes.addFlashAttribute("message", FlashMessage.info(msg));
        return "redirect:/forgot";
    }
}
