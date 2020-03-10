package com.mcdull.my.shop.web.admin.service;

import com.mcdull.my.shop.commons.dto.BaseResult;
import com.mcdull.my.shop.commons.dto.PageInfo;
import com.mcdull.my.shop.domain.TbUser;

import java.util.List;

public interface TbUserService {
    /**
     * 查询全部
     * @return
     */
    public List<TbUser> selectAll();

    /**
     * 保存用户信息
     * @param tbUser
     * @return
     */
    public BaseResult save(TbUser tbUser);

    /**
     * 删除用户信息
     * @param id
     */
    public void delete(Long id);

    /**
     * 根据ID 获取用户信息
     * @param id
     * @return
     */
    public TbUser getById(Long id);

    /**
     * 更新用户信息
     * @param tbUser
     */
    public void update(TbUser tbUser);


    /**
     * 用户登录
     * @param email
     * @param password
     * @return
     */
    public TbUser Login(String email, String password);


    /**
     * 批量删除
     * @param ids
     */
    public void deleteMulti(String[] ids);

    /**
     * 分页查询
     * @param start
     * @param length
     * @return
     */
    public PageInfo<TbUser> page(int start, int length,int draw,TbUser tbUser);

    /**
     * 查询总笔数
     * @return
     */
    public int count(TbUser tbUser);
}
