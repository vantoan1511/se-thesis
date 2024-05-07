package com.newswebsite.main.controller.admin;

import com.newswebsite.main.dto.UserDTO;
import com.newswebsite.main.enums.Role;
import com.newswebsite.main.security.SecurityUtil;
import com.newswebsite.main.service.imageservice.IImageReader;
import com.newswebsite.main.service.roleservice.IRoleReader;
import com.newswebsite.main.service.session.ISessionService;
import com.newswebsite.main.service.userservice.IUserReader;
import com.newswebsite.main.service.userservice.IUserWriter;
import com.newswebsite.main.utils.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    private final IUserReader userReader;

    private final IUserWriter userWriter;

    private final IRoleReader roleReader;

    private final ISessionService sessionService;

    private final IImageReader imageReader;

    @Autowired
    public UserController(IUserReader userReader,
                          IUserWriter userWriter,
                          IRoleReader roleReader,
                          ISessionService sessionService, IImageReader imageReader) {
        this.userReader = userReader;
        this.userWriter = userWriter;
        this.roleReader = roleReader;
        this.sessionService = sessionService;
        this.imageReader = imageReader;
    }

    @GetMapping
    public ModelAndView getUserList(@RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "limit", defaultValue = "2") int limit,
                                    @RequestParam(value = "by", defaultValue = "createdAt") String by,
                                    @RequestParam(value = "order", defaultValue = "DESC") String order) {
        Sort.Direction direction = order.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = new Sort(direction, by);
        Pageable pageable = new PageRequest(page - 1, limit, sort);

        String viewName = "admin/listUsers";
        ModelAndView view = new ModelAndView(viewName);
        view.addObject("userPage", userReader.getAllUsers(pageable));
        view.addObject("sortBy", by);
        view.addObject("sortOrder", order);
        return view;
    }

    @GetMapping("/{username}")
    public String getUser(@PathVariable("username") String username,
                          RedirectAttributes attributes,
                          Model model) {
        String viewName = "admin/userDetails";
        String loggedUsername = SecurityUtil.username();
        try {
            model.addAttribute("profile", userReader.getUser(username));
            model.addAttribute("allRoles", roleReader.getAllRoles());
            if (loggedUsername.equals(username)) {
                model.addAttribute("uploadedImages",
                        imageReader.getFiles(username, new PageRequest(0, 99, Sort.Direction.DESC, "createdAt")));
            }
            return viewName;
        } catch (RuntimeException ex) {
            attributes.addFlashAttribute("message", FlashMessage.danger(ex.getMessage()));
            return "redirect:/admin/users";
        }
    }

    @PostMapping("/{username}")
    public String updateProfile(@PathVariable("username") String username,
                                @ModelAttribute UserDTO userDTO,
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
        return "redirect:/admin/users/".concat(username);
    }

    @GetMapping("/{username}/disable")
    public String disableAccount(@PathVariable("username") String username,
                                 RedirectAttributes attributes) {
        if (SecurityUtil.getAuthorities().contains(Role.ADMIN.name())) {
            try {
                userWriter.disable(username);
                sessionService.expireByUsername(username);
            } catch (RuntimeException ex) {
                attributes.addFlashAttribute("message", FlashMessage.danger(ex.getMessage()));
            }
        } else {
            attributes.addFlashAttribute("message", FlashMessage.danger("Thao tác không được phép"));
        }
        return "redirect:/admin/users/".concat(username);
    }

    @GetMapping("/{username}/enable")
    public String enableAccount(@PathVariable("username") String username,
                                RedirectAttributes attributes) {
        if (SecurityUtil.getAuthorities().contains(Role.ADMIN.name())) {
            userWriter.enable(username);
        } else {
            attributes.addFlashAttribute("message", FlashMessage.danger("Thao tác không được phép"));
        }
        return "redirect:/admin/users/".concat(username);
    }

    @GetMapping("/{username}/delete")
    public String deleteAccount(@PathVariable("username") String username,
                                RedirectAttributes attributes) {
        if (SecurityUtil.username().equals(username)) {
            userWriter.delete(username);
            return "redirect:/logout";
        }
        if (SecurityUtil.getAuthorities().contains(Role.ADMIN.name())) {
            try {
                userWriter.delete(username);
                sessionService.expireByUsername(username);
            } catch (RuntimeException ex) {
                attributes.addFlashAttribute("message", FlashMessage.danger(ex.getMessage()));
            }
        } else {
            attributes.addFlashAttribute("message", FlashMessage.danger("Thao tác không được phép"));
        }
        return "redirect:/admin/users";
    }
}
