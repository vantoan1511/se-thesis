package com.newswebsite.main.controller.web;

import com.newswebsite.main.service.reviewservice.IReviewWriter;
import com.newswebsite.main.utils.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final IReviewWriter reviewWriter;

    @Autowired
    public ReviewController(IReviewWriter reviewWriter) {
        this.reviewWriter = reviewWriter;
    }

    @GetMapping("/{reviewId}/delete")
    public String deleteReview(@PathVariable("reviewId") Long id,
                               @RequestParam(value = "redirectUrl") String redirectUrl,
                               RedirectAttributes attributes) {
        String viewName = "redirect:".concat(redirectUrl);
        try {
            reviewWriter.delete(id);
            attributes.addFlashAttribute("message", FlashMessage.success("Đã xóa thành công!"));
        } catch (RuntimeException ex) {
            attributes.addFlashAttribute("message", FlashMessage.danger(ex.getMessage()));
        }
        return viewName;
    }
}
