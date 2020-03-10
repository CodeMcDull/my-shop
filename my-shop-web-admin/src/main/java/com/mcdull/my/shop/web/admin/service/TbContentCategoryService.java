package com.mcdull.my.shop.web.admin.service;

import com.mcdull.my.shop.domain.TbContentCategory;

import java.util.List;

public interface TbContentCategoryService {
    List<TbContentCategory> selectAll();

    /**
     * 根据父级节点 ID 查询所有子节点
     * @param pid
     * @return
     */
    List<TbContentCategory> selectByPid(Long pid);
}
