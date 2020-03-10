package com.mcdull.my.shop.web.admin.service;

import com.mcdull.my.shop.commons.dto.BaseResult;
import com.mcdull.my.shop.commons.dto.PageInfo;
import com.mcdull.my.shop.domain.TbContent;
import com.mcdull.my.shop.domain.TbUser;

import java.util.List;
import java.util.Map;

public interface TbContentService {
    /**
     * 查询全部信息
     * @return
     */
    public List<TbContent> selectAll();

    /**
     * 新增
     * @param tbContent
     */
    public BaseResult save(TbContent tbContent);

    /**
     * 删除
     * @param id
     */
    public void delete(Long id);

    /**
     * 根据ID查询信息
     * @param id
     * @return
     */
    public TbContent getById(Long id);

    /**
     * 更新
     * @param tbContent
     */
    public void update(TbContent tbContent);


    /**
     * 批量删除
     * @param ids
     */
    public void deleteMulti(String[] ids);

    /**
     * 分页查询
     * @param tbContent 需要两个参数 start记录开始的位置 length 每页记录数
     * @return
     */
    public PageInfo<TbContent> page(int start, int length, int draw, TbContent tbContent);

    /**
     * 查询总笔数
     * @return
     */
    public int count(TbContent tbContent);
}
