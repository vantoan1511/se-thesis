package com.newswebsite.main.controller.web;

import com.newswebsite.main.utils.FlashMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage(
            @RequestParam(value = "invalidCredentials", required = false) boolean invalidCredentials,
            @RequestParam(value = "invalidSession", required = false) boolean invalidSession,
            @RequestParam(value = "expiredSession", required = false) boolean expiredSession,
            RedirectAttributes attributes) {
        String viewName = "web/login";
        if (invalidCredentials) {
            attributes.addFlashAttribute("message", FlashMessage.danger("Thông tin đăng nhập không hợp lệ"));
            viewName = "redirect:/login";
        }
        if (invalidSession || expiredSession) {
            attributes.addFlashAttribute("message", FlashMessage.danger("Phiên đăng nhập không hợp lệ hoặc đã hết hạn"));
            viewName = "redirect:/login";
        }
        return viewName;
    }

}
