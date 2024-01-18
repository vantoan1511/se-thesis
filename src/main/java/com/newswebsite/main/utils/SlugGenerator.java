package com.newswebsite.main.utils;

import com.github.slugify.Slugify;

public class SlugGenerator {

    private static Slugify instance;

    public String generateUniqueSlug(String baseSlug) {
        String uniqueSuffix = "-" + System.currentTimeMillis();
        return instance.slugify(baseSlug) + uniqueSuffix;
    }

    public static Slugify getInstance() {
        if (instance == null)
            instance = Slugify.builder().build();
        return instance;
    }
}
