package com.newswebsite.main.controller.web;

import com.newswebsite.main.dto.UserDTO;
import com.newswebsite.main.utils.FlashMessage;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @GetMapping
    public ModelAndView getRegisterPage() {
        String viewName = "web/register";
        ModelAndView view = new ModelAndView(viewName);
        view.addObject("user", new UserDTO());
        return view;
    }

    @PostMapping
    public ModelAndView register(@Valid @ModelAttribute("user") UserDTO userDTO,
                                 BindingResult result,
                                 RedirectAttributes attributes) {
        String viewName = "web/register";
        if (!result.hasErrors()) {
            viewName = "redirect:/login";
            attributes.addFlashAttribute("message", FlashMessage.success("Đăng ký thành công"));
        }
        ModelAndView view = new ModelAndView(viewName);
        return view;
    }
}
