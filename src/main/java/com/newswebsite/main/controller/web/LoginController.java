package com.newswebsite.main.controller.web;

import com.newswebsite.main.utils.FlashMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public ModelAndView getLoginPage(@RequestParam(value = "invalidCredentials", required = false) boolean invalidCredentials,
                                     RedirectAttributes attributes) {
        String viewName = "web/login";
        if (invalidCredentials) {
            attributes.addFlashAttribute("message", FlashMessage.danger("Thông tin đăng nhập không hợp lệ"));
            viewName = "redirect:/login";
        }
        ModelAndView view = new ModelAndView(viewName);
        return view;
    }
}
