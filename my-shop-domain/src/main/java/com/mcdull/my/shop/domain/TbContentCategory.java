package com.mcdull.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mcdull.my.shop.commons.persistence.BaseEntity;
import lombok.Data;

/**
 * 分类管理
 */
@Data
public class TbContentCategory extends BaseEntity {
    private Long parentId;
    private String name;
    private Integer status;
    private Integer sortOrder;
    private Boolean isParent;
}
