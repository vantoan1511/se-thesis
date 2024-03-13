package com.newswebsite.main.enums;

public enum ArticleState {
    DRAFT("Nháp"), PENDING("Đang chờ duyệt"), APPROVED("Đã được chấp nhận"), PUBLISHED("Đã đăng tải"), UNPUBLISHED("Đã gỡ"), TRASH("Đã xóa");

    private final String value;

    ArticleState(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
