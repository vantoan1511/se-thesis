package com.newswebsite.main.controller.web;

import com.newswebsite.main.dto.UserDTO;
import com.newswebsite.main.service.userservice.IUserModificationService;
import com.newswebsite.main.utils.FlashMessage;
import com.newswebsite.main.validator.CustomUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private IUserModificationService userModificationService;

    @Autowired
    private CustomUserValidator userValidator;

    @InitBinder("user")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

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
            userModificationService.register(userDTO);
            viewName = "redirect:/login";
            attributes.addFlashAttribute("message", FlashMessage.success("Đăng ký thành công"));
        }
        ModelAndView view = new ModelAndView(viewName);
        return view;
    }
}
