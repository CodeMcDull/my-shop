package com.mcdull.my.shop.web.admin.web.controller;

import com.alibaba.druid.support.spring.stat.SpringStatUtils;
import com.mcdull.my.shop.commons.dto.BaseResult;
import com.mcdull.my.shop.commons.dto.PageInfo;
import com.mcdull.my.shop.domain.TbUser;
import com.mcdull.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.SpringTransactionAnnotationParser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 */
@Controller
@RequestMapping(value="user")
public class UserController {
    @Autowired
    private TbUserService tbUserService;

    /**
     * 跳转到用户列表页
     * @return
     */
    @RequestMapping(value="list", method= RequestMethod.GET)
    public String list(){
        return "user_list";
    }

    @ModelAttribute
    public TbUser getTbUser(Long id){
        TbUser tbUser=null;
        //id不为空，则从数据库获取
        if (id != null) {
            tbUser = tbUserService.getById(id);
        }
        //
        else {
            tbUser=new TbUser();
        }
        return tbUser;
    }

    /**
     * 跳转用户表单页
     * @return
     */
    @RequestMapping(value="form",method = RequestMethod.GET)
    public String form(){
        return "user_form";
    }

    /**
     * 保存用户信息
     * @param tbUser
     * @return
     */
    @RequestMapping(value="save",method=RequestMethod.POST)
    public String save(TbUser tbUser, Model model,RedirectAttributes redirectAttributes){
        BaseResult baseResult = tbUserService.save(tbUser);
        //保存成功
        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/user/list";
        }
        //保存失败
        else{
            model.addAttribute("baseResult", baseResult);
            return "user_form";
        }
    }


    /**
     * 删除用户信息
     * @param ids
     * @return
     */

    @ResponseBody
    @RequestMapping(value="delete",method = RequestMethod.POST)
    public BaseResult delete(String ids) {
        BaseResult baseResult=null;
        if (StringUtils.isNotBlank(ids)) {
            String[] idArray=ids.split(",");
            tbUserService.deleteMulti(idArray);
            baseResult=BaseResult.success("删除用户成功");
        }
        else{
            baseResult=BaseResult.fail("删除用户失败");
        }
        return baseResult;
    }

    /**
     * 分页查询
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="page",method = RequestMethod.GET)
    public PageInfo<TbUser> page(HttpServletRequest request,TbUser tbUser) {


        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);

        //封装Datatables 需要的结果
        PageInfo<TbUser> pageInfo = tbUserService.page(start,length,draw,tbUser);
        return pageInfo;
    }

    /**
     * 显示用户详情
     * @param tbUser
     * @return
     */
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(TbUser tbUser) {
        System.out.println(tbUser.getUsername());
        return "user_detail";
    }
}
