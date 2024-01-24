package com.newswebsite.main.controller.admin;

import com.newswebsite.main.dto.response.ImageResponse;
import com.newswebsite.main.security.SecurityUtil;
import com.newswebsite.main.service.imageservice.IImageReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/my-storage")
public class StorageController {

    @Autowired
    private IImageReader fileReader;

    @GetMapping
    public ModelAndView getList() {
        String viewName = "admin/storage/storage";
        String username = SecurityUtil.username();

        ModelAndView view = new ModelAndView(viewName);
        view.addObject("files", fileReader.getFiles(username, new PageRequest(0, 10)));
        return view;
    }

    @GetMapping({"/new", "/{alias}"})
    public ModelAndView upload(@PathVariable(value = "alias", required = false) String alias) {
        String viewName = "admin/storage/details";
        ImageResponse fileRequest = alias != null ? fileReader.getFile(alias) : new ImageResponse();

        ModelAndView view = new ModelAndView(viewName);
        view.addObject("file", fileRequest);
        return view;
    }
}
