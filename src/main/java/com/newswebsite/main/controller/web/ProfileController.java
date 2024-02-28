package com.newswebsite.main.controller.web;

import com.newswebsite.main.dto.UserDTO;
import com.newswebsite.main.security.SecurityUtil;
import com.newswebsite.main.service.imageservice.IImageReader;
import com.newswebsite.main.service.userservice.IUserReader;
import com.newswebsite.main.service.userservice.IUserWriter;
import com.newswebsite.main.utils.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller(value = "WebProfileController")
@RequestMapping("/profiles/{username}")
public class ProfileController {

    private final IUserReader userReader;

    private final IUserWriter userWriter;

    private final IImageReader imageReader;

    @Autowired
    public ProfileController(IUserReader userReader, IUserWriter userWriter, IImageReader imageReader) {
        this.userReader = userReader;
        this.userWriter = userWriter;
        this.imageReader = imageReader;
    }

    @GetMapping
    public ModelAndView getProfile(@PathVariable("username") String username) {
        String viewName = "web/profile";
        ModelAndView view = new ModelAndView(viewName);
        view.addObject("profile", userReader.getUser(username));
        view.addObject("uploadedImages", imageReader.getFiles(username, new PageRequest(0, 99, Sort.Direction.DESC, "createdAt")));
        return view;
    }

    @PostMapping
    public String updateProfile(@PathVariable("username") String username,
                                @Valid @ModelAttribute UserDTO userDTO,
                                RedirectAttributes attributes) {
        String loggedUsername = SecurityUtil.username();
        if (loggedUsername.equals(username)) {
            try {
                userWriter.updateProfile(userDTO);
                attributes.addFlashAttribute("message", FlashMessage.success("Cập nhật thành công"));
            } catch (RuntimeException ex) {
                attributes.addFlashAttribute("message", FlashMessage.danger(ex.getMessage()));
            }
        } else {
            attributes.addFlashAttribute("message", FlashMessage.danger("Thao tác không được phép"));
        }
        return "redirect:/profiles/".concat(username);
    }

    @GetMapping("/delete")
    public String deleteProfile(@PathVariable("username") String username,
                                RedirectAttributes attributes) {
        String loggedUsername = SecurityUtil.username();
        String viewName;
        if (loggedUsername.equals(username)) {
            try {
                userWriter.delete(username);
                viewName = "redirect:/logout";
                attributes.addFlashAttribute("message", FlashMessage.success("Tài khoản của bạn đã xóa thành công"));
            } catch (RuntimeException ex) {
                viewName = "redirect:/profiles/".concat(username);
                attributes.addFlashAttribute("message", FlashMessage.danger(ex.getMessage()));
            }
        } else {
            attributes.addFlashAttribute("message", FlashMessage.danger("Thao tác không được phép"));
            viewName = "redirect:/profiles/".concat(username);
        }
        return viewName;
    }
}
