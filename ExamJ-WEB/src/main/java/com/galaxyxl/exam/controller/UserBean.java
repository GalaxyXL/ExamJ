package com.galaxyxl.exam.controller;

import com.galaxyxl.exam.dao.UserDao;
import com.galaxyxl.exam.model.User;
import com.galaxyxl.exam.util.EJBUtil;
import com.galaxyxl.exam.util.HTTPUtil;
import com.galaxyxl.exam.util.JSFUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@Named
@SessionScoped
public class UserBean implements Serializable{
    private static final Logger log = LoggerFactory.getLogger(UserBean.class);

    private UserDao userDao = EJBUtil.getBean(UserDao.class);

    private User user = new User();

    private User currentUser;

    private String confirmStyle;

    private String confirmPassword;

    public void validatePassword(FacesContext ctx, UIComponent ui, Object o) throws ValidatorException {
        String password = o.toString();
        if (password.length() < 8) {
            throw new ValidatorException(new FacesMessage("密码不符合要求", "密码至少八位"));
        }
        if (!password.matches(".*([a-zA-Z]+).*") || !password.matches(".*([0-9]+).*")) {
            throw new ValidatorException(new FacesMessage("密码不符合要求", "密码必须包含字母和数字"));
        }
    }

    public void validateUsername(FacesContext ctx, UIComponent ui, Object o) throws ValidatorException {
        User u = userDao.findByUserName(o.toString());
        if (u != null) {
            throw new ValidatorException(new FacesMessage("用户名不符合要求", "用户名已存在！"));
        }
    }

    public void validateEmail(FacesContext ctx, UIComponent ui, Object o) {
        String email = o.toString();
        if (!email.matches("(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)")) {
            throw new ValidatorException(new FacesMessage("邮箱格式不正确", "请输入正确的邮箱"));
        }
    }

    public String login() {
        user.setName(user.getName().replaceAll("\\s+", ""));
        user.setPassword(user.getPassword().replaceAll("\\s+", ""));
        User u = userDao.findByUserName(user.getName());
        if (user.getName().equals("") || user.getPassword().equals("")) {
            UIComponent uiComponent = JSFUtil.findComponentById(FacesContext.getCurrentInstance(), "inputPassword");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN , "登录错误","用户名和密码不能为空");
            FacesContext.getCurrentInstance().addMessage(uiComponent.getClientId(), msg);
            return "error";
        }
        else if (u == null){
            UIComponent uiComponent = JSFUtil.findComponentById(FacesContext.getCurrentInstance(), "inputPassword");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN , "登录错误","用户名不存在");
            FacesContext.getCurrentInstance().addMessage(uiComponent.getClientId(), msg);
            return "error";
        }
        else if (!u.getPassword().equals(user.getPassword())) {
            UIComponent uiComponent = JSFUtil.findComponentById(FacesContext.getCurrentInstance(), "inputPassword");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN , "登录错误","密码错误");
            FacesContext.getCurrentInstance().addMessage(uiComponent.getClientId(), msg);
            return "error";
        }
        HttpSession session = HTTPUtil.getSession();
        currentUser = u;
        session.setAttribute("CUR_USER", currentUser);
        return "index";
    }

    public String register() {
        userDao.save(user);
        currentUser = user;
        HTTPUtil.getSession().setAttribute("CUR_USER", currentUser);
        return "index";
    }

    public User getUser() {
        return user;
    }

    public String getConfirmStyle() {
        return confirmStyle;
    }

    public void setConfirmStyle(String confirmStyle) {
        this.confirmStyle = confirmStyle;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
