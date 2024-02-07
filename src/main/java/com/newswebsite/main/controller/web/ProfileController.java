package com.newswebsite.main.controller.web;

import com.newswebsite.main.dto.ProfileRequest;
import com.newswebsite.main.security.SecurityUtil;
import com.newswebsite.main.service.userservice.IUserReader;
import com.newswebsite.main.service.userservice.IUserWriter;
import com.newswebsite.main.utils.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller(value = "WebProfileController")
@RequestMapping("/profiles/{username}")
public class ProfileController {

    private final IUserReader userReader;

    private final IUserWriter userWriter;

    @Autowired
    public ProfileController(IUserReader userReader, IUserWriter userWriter) {
        this.userReader = userReader;
        this.userWriter = userWriter;
    }

    @GetMapping
    public ModelAndView getProfile(@PathVariable("username") String username) {
        String viewName = "web/profile";
        ModelAndView view = new ModelAndView(viewName);
        view.addObject("profile", userReader.getUser(username));
        return view;
    }

    @PostMapping
    public ModelAndView updateProfile(@PathVariable("username") String username,
                                      @Valid @ModelAttribute ProfileRequest profileRequest,
                                      BindingResult bindingResult,
                                      RedirectAttributes attributes) {
        String loggedUsername = SecurityUtil.username();
        if (loggedUsername.equals(username)) {
            if (bindingResult.hasErrors()) {
                attributes.addFlashAttribute("message", FlashMessage.danger(bindingResult.getFieldError().getDefaultMessage()));
            } else {
                userWriter.updateProfile(loggedUsername, profileRequest);
                attributes.addFlashAttribute("message", FlashMessage.success("Cập nhật thành công"));
            }
        } else {
            attributes.addFlashAttribute("message", FlashMessage.danger("Thao tác không được phép"));
        }
        String viewName = "redirect:/profiles/".concat(username);
        ModelAndView view = new ModelAndView(viewName);
        return view;
    }
}
