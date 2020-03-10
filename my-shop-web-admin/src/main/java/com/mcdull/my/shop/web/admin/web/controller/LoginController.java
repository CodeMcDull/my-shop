package com.mcdull.my.shop.web.admin.web.controller;

import com.mcdull.my.shop.commons.constant.ConstantUtils;
import com.mcdull.my.shop.domain.TbUser;
import com.mcdull.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private TbUserService tbUserService;

    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping(value={"","login"},method= RequestMethod.GET)
    public String login(){
        return "login";
    }

    /**
     * 登录逻辑
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value="login",method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email, @RequestParam(required = true) String password, HttpServletRequest httpServletRequest, Model model) {
        TbUser tbUser = tbUserService.Login(email, password);
        //登录失败
        if (tbUser == null) {
            model.addAttribute("message", "用户名或密码错误，请重新输入");
            return login();
        }
        //登录成功
        else{
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER, tbUser);
            return "redirect:/main";
        }

    }

    @RequestMapping(value="logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().invalidate();
        return login();
    }
}
