package com.newswebsite.main.controller.admin;

import com.newswebsite.main.dto.ProfileRequest;
import com.newswebsite.main.dto.UserDTO;
import com.newswebsite.main.enums.Role;
import com.newswebsite.main.security.SecurityUtil;
import com.newswebsite.main.service.roleservice.IRoleReader;
import com.newswebsite.main.service.userservice.IUserReader;
import com.newswebsite.main.service.userservice.IUserWriter;
import com.newswebsite.main.utils.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    private final IUserReader userReader;

    private final IUserWriter userWriter;

    private final IRoleReader roleReader;

    @Autowired
    public UserController(IUserReader userReader, IUserWriter userWriter, IRoleReader roleReader) {
        this.userReader = userReader;
        this.userWriter = userWriter;
        this.roleReader = roleReader;
    }

    @GetMapping
    public ModelAndView getUserList(@RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "limit", defaultValue = "2") int limit,
                                    @RequestParam(value = "by", defaultValue = "createdAt") String by,
                                    @RequestParam(value = "order", defaultValue = "DESC") String order) {
        Sort.Direction direction = order.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = new Sort(direction, by);
        Pageable pageable = new PageRequest(page - 1, limit, sort);

        String viewName = "admin/users/users";
        ModelAndView view = new ModelAndView(viewName);
        view.addObject("userPage", userReader.getAllUsers(pageable));
        view.addObject("sortBy", by);
        view.addObject("sortOrder", order);
        return view;
    }

    @GetMapping("/{username}")
    public ModelAndView getUser(@PathVariable("username") String username) {
        UserDTO userDTO = userReader.getUser(username);
        String viewName = "admin/users/userDetails";
        ModelAndView view = new ModelAndView(viewName);
        view.addObject("profile", userDTO);
        return view;
    }

    @PostMapping("/{username}")
    public String updateProfile(@PathVariable("username") String username,
                                @ModelAttribute UserDTO userDTO,
                                RedirectAttributes attributes) {
        String loggedUsername = SecurityUtil.username();
        if (loggedUsername.equals(username)) {
            try {
                userWriter.updateProfile(loggedUsername, new ProfileRequest(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail()));
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
            userWriter.disable(username);
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
}
