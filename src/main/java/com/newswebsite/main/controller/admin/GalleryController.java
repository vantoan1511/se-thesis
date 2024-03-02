package com.newswebsite.main.controller.admin;

import com.newswebsite.main.dto.ImageDTO;
import com.newswebsite.main.security.SecurityUtil;
import com.newswebsite.main.service.imageservice.IImageReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/gallery")
public class GalleryController {

    private final IImageReader imageReader;

    @Autowired
    public GalleryController(IImageReader imageReader) {
        this.imageReader = imageReader;
    }

    @GetMapping
    public String getList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "order", defaultValue = "DESC") String order,
            @RequestParam(value = "by", defaultValue = "title") String by,
            Model model
    ) {
        String viewName = "admin/gallery";
        String username = SecurityUtil.username();

        Sort.Direction direction = order.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = new PageRequest(page - 1, size, direction, by);
        model.addAttribute("files", imageReader.getFiles(username, pageable));
        model.addAttribute("sortBy", by);
        model.addAttribute("sortOrder", order);
        return viewName;
    }

    @GetMapping({"/{alias}"})
    public ModelAndView upload(@PathVariable(value = "alias", required = false) String alias) {
        String viewName = "admin/imageDetails";
        ImageDTO fileRequest = alias != null ? imageReader.getFile(alias) : new ImageDTO();

        ModelAndView view = new ModelAndView(viewName);
        view.addObject("file", fileRequest);
        return view;
    }
}
