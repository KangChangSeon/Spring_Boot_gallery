package com.ssg.gallery.item.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
// 상품 조회 DTO
public class ItemRead {
    private Integer id;
    private String name;
    private String img_path;
    private Integer price;
    private Integer discount_per;
}
