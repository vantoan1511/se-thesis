package com.newswebsite.main.controller.web;

import com.newswebsite.main.service.IUserModificationService;
import com.newswebsite.main.utils.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

@Controller
@RequestMapping("/forgot")
public class ForgotController {

    @Autowired
    private IUserModificationService userModificationService;

    @Autowired
    private MessageSource msg;

    @GetMapping
    public String getForgotPage() {
        return "web/forgot";
    }

    @PostMapping
    public String sendResetEmail(@RequestParam("email") String email,
                                 RedirectAttributes attributes,
                                 Locale locale) {
        if (StringUtils.hasText(email)) {
            try {
                userModificationService.resetPassword(email);
                attributes.addFlashAttribute("message",
                        FlashMessage.info(msg.getMessage("email.reset.sent", null, locale)));
            } catch (RuntimeException ex) {
                attributes.addFlashAttribute("message",
                        FlashMessage.danger(ex.getMessage()));
            }
        } else {
            attributes.addFlashAttribute("message",
                    FlashMessage.danger(msg.getMessage("email.required", null, locale)));
        }
        return "redirect:/forgot";
    }
}
