package com.newswebsite.main.controller.web;

import com.newswebsite.main.dto.ProfileRequest;
import com.newswebsite.main.security.SecurityUtil;
import com.newswebsite.main.service.userservice.IUserReader;
import com.newswebsite.main.service.userservice.IUserWriter;
import com.newswebsite.main.utils.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller(value = "WebProfileController")
public class ProfileController {

    private final IUserReader userReader;

    private final IUserWriter userWriter;

    @Autowired
    public ProfileController(IUserReader userReader, IUserWriter userWriter) {
        this.userReader = userReader;
        this.userWriter = userWriter;
    }

    @GetMapping("/my-profile")
    public ModelAndView getMyProfile() {
        String username = SecurityUtil.username();
        String viewName = "web/profile";
        ModelAndView view = new ModelAndView(viewName);
        view.addObject("user", userReader.getUser(username));
        return view;
    }

    @PostMapping("/my-profile")
    public ModelAndView updateProfile(@Valid @ModelAttribute ProfileRequest profileRequest,
                                      BindingResult bindingResult,
                                      RedirectAttributes attributes) {
        String username = SecurityUtil.username();
        String viewName = "redirect:/my-profile";
        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("message", FlashMessage.danger(bindingResult.getFieldError().getDefaultMessage()));
        } else {
            userWriter.updateProfile(username, profileRequest);
            attributes.addFlashAttribute("message", FlashMessage.success("Cập nhật thành công"));
        }
        ModelAndView view = new ModelAndView(viewName);
        return view;
    }
}
