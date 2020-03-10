package com.mcdull.my.shop.web.admin.dao;

import com.mcdull.my.shop.domain.TbContent;
import com.mcdull.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbContentDao {
    /**
     * 查询全部信息
     * @return
     */
    public List<TbContent> selectAll();

    /**
     * 新增
     * @param tbContent
     */
    public void insert(TbContent tbContent);

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
     * @param params 需要两个参数 start记录开始的位置 length 每页记录数
     * @return
     */
    public List<TbContent> page(Map<String,Object> params);

    /**
     * 查询总笔数
     * @return
     */
    public int count(TbContent tbContent);
}
