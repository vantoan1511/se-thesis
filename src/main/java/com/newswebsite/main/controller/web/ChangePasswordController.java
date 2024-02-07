package com.newswebsite.main.controller.web;

import com.newswebsite.main.security.SecurityUtil;
import com.newswebsite.main.service.userservice.IUserReader;
import com.newswebsite.main.service.userservice.IUserWriter;
import com.newswebsite.main.utils.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/change-password")
public class ChangePasswordController {

    private final IUserReader userReader;

    private final IUserWriter userWriter;

    @Autowired
    public ChangePasswordController(IUserReader userReader, IUserWriter userWriter) {
        this.userReader = userReader;
        this.userWriter = userWriter;
    }

    @GetMapping
    public String getPage() {
        return "web/changepw";
    }

    @PostMapping
    public String perform(@RequestParam("currentPassword") String currentPassword,
                          @RequestParam("newPassword") String newPassword,
                          @RequestParam("confirmPassword") String confirmPassword,
                          RedirectAttributes attributes) {
        String username = SecurityUtil.username();
        if (!newPassword.equals(confirmPassword)) {
            attributes.addFlashAttribute("message", FlashMessage.danger("Nhập lại mật khẩu không khớp"));
            return "redirect:/change-password";
        } else if (!userReader.matches(username, currentPassword)) {
            attributes.addFlashAttribute("message", FlashMessage.danger("Mật khẩu cũ không chính xác"));
            return "redirect:/change-password";
        } else {
            userWriter.changePassword(username, newPassword);
            attributes.addFlashAttribute("message", FlashMessage.success("Đổi mật khẩu thành công!"));
        }
        return "redirect:/profiles/".concat(username);
    }
}
