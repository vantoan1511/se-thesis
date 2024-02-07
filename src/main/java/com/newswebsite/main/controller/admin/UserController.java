package com.newswebsite.main.controller.admin;

import com.newswebsite.main.dto.UserDTO;
import com.newswebsite.main.security.SecurityUtil;
import com.newswebsite.main.service.roleservice.IRoleReader;
import com.newswebsite.main.service.userservice.IUserReader;
import com.newswebsite.main.service.userservice.IUserWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
        String loggedUsername = SecurityUtil.username();
        UserDTO userDTO;
        if (!loggedUsername.equals(username)) {
            userDTO = userReader.getUser(username);
            userDTO.eraseCredentials();
        } else {
            userDTO = userReader.getUser(loggedUsername);
            userDTO.eraseCredentials();
        }
        String viewName = "admin/users/userDetails";
        ModelAndView view = new ModelAndView(viewName);
        view.addObject("userDetails", userDTO);
        view.addObject("roles", roleReader.getAllRoles());
        return view;
    }
}
