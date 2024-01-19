package com.newswebsite.main.utils;

import com.github.slugify.Slugify;

public class SlugGenerator {
    public final static Slugify slugify = Slugify.builder().build();

    public static String generateUniqueSlug(String baseSlug) {
        String uniqueSuffix = "-" + System.currentTimeMillis();
        return slugify.slugify(baseSlug) + uniqueSuffix;
    }
}
