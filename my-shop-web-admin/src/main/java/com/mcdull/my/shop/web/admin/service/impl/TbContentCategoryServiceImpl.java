package com.mcdull.my.shop.web.admin.service.impl;

import com.mcdull.my.shop.domain.TbContentCategory;
import com.mcdull.my.shop.web.admin.dao.TbContentCategoryDao;
import com.mcdull.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {
    @Autowired
    private TbContentCategoryDao tbContentCategoryDao;

    @Override
    public List<TbContentCategory> selectAll() {
        return tbContentCategoryDao.selectAll();
    }

    @Override
    public List<TbContentCategory> selectByPid(Long pid) {
        return tbContentCategoryDao.selectByPid(pid);
    }
}
