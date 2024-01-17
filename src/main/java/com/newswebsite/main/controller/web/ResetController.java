package com.newswebsite.main.controller.web;

import com.newswebsite.main.service.IUserModificationService;
import com.newswebsite.main.service.IUserRetrievalService;
import com.newswebsite.main.utils.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reset")
public class ResetController {

    @Autowired
    private IUserRetrievalService userRetrievalService;

    @Autowired
    private IUserModificationService userModificationService;

    @GetMapping
    public ModelAndView showReset(@RequestParam(value = "token", required = false) String token,
                                  RedirectAttributes attributes) {
        String viewName = "redirect:/login";
        if (!StringUtils.hasText(token) || !userRetrievalService.existsToken(token)) {
            attributes.addFlashAttribute("message",
                    FlashMessage.danger("Đường dẫn đã hết hạn hoặc không tồn tại"));
        } else {
            viewName = "web/recover";
        }
        return new ModelAndView(viewName);
    }

    @PostMapping
    public ModelAndView changePW(@RequestParam("token") String token,
                                 @RequestParam("password") String password,
                                 RedirectAttributes attributes) {
        String viewName = "web/recover";
        //check password length before
        try {
            userModificationService.changePassword(token, password);
            attributes.addFlashAttribute("message",
                    FlashMessage.success("Đổi mật khẩu thành công. Đăng nhập để tiếp tục"));
            viewName = "redirect:/login";
        } catch (RuntimeException e) {
            attributes.addFlashAttribute("message",
                    FlashMessage.danger(e.getMessage()));
        }
        return new ModelAndView(viewName);
    }
}
