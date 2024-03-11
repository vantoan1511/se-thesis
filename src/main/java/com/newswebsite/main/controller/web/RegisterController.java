package com.newswebsite.main.controller.web;

import com.newswebsite.main.dto.request.UserRegistrationRequest;
import com.newswebsite.main.service.userservice.IUserWriter;
import com.newswebsite.main.utils.FlashMessage;
import com.newswebsite.main.validator.CustomUserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final IUserWriter userWriter;

    private final CustomUserValidator userValidator;

    public RegisterController(IUserWriter userWriter, CustomUserValidator userValidator) {
        this.userWriter = userWriter;
        this.userValidator = userValidator;
    }

    @InitBinder("user")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @GetMapping
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new UserRegistrationRequest());
        return "web/register";
    }

    @PostMapping
    public String register(@Valid @ModelAttribute("user") UserRegistrationRequest userRegistrationRequest,
                           BindingResult result,
                           Model model,
                           RedirectAttributes attributes) {
        String viewName = "redirect:/login";
        if (result.hasErrors()) {
            viewName = "web/register";
            model.addAttribute("message", FlashMessage.danger(result.getFieldError().getDefaultMessage()));
        } else {
            userWriter.register(userRegistrationRequest);
            attributes.addFlashAttribute("message", FlashMessage.success("Đăng ký thành công. Đăng nhập để tiếp tục"));
        }
        return viewName;
    }
}
