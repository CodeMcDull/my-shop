package com.mcdull.my.shop.web.admin.dao;

import com.mcdull.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbUserDao {
    /**
     * 查询用户表全部信息
     * @return
     */
    public List<TbUser> selectAll();

    /**
     * 新增
     * @param tbUser
     */
    public void insert(TbUser tbUser);

    /**
     * 删除
     * @param id
     */
    public void delete(Long id);

    /**
     * 根据ID查询用户信息
     * @param id
     * @return
     */
    public TbUser getById(Long id);

    /**
     * 更新
     * @param tbUser
     */
    public void update(TbUser tbUser);


    /**
     * 根据邮箱查询用户信息
     * @param email
     * @return
     */
    public TbUser getByEmail(String email);


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
    public List<TbUser> page(Map<String,Object> params);

    /**
     * 查询总笔数
     * @return
     */
    public int count(TbUser tbUser);
}
