package com.galaxyxl.exam.controller;

import com.galaxyxl.exam.dao.GradesDao;
import com.galaxyxl.exam.model.PaperUser;
import com.galaxyxl.exam.model.User;
import com.galaxyxl.exam.util.EJBUtil;
import com.galaxyxl.exam.util.HTTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

@Named
@SessionScoped
public class GradesBean implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(GradesBean.class);

    private GradesDao gradesDao = EJBUtil.getBean(GradesDao.class);

    private User user;

    private List<PaperUser> paperUserList;



    public void flushGradesTable(AjaxBehaviorEvent event) {
        paperUserList = gradesDao.getGradesByUid(user.getId());
        getRanking();
    }

    public void getRanking() {
        for(PaperUser paperUser : paperUserList) {
            List<PaperUser> paperUserListRank = gradesDao.getGradesByPid(paperUser.getPaper().getId());
            Comparator<PaperUser> comparator = Comparator.comparing(PaperUser::getScore);
            paperUserListRank.sort(comparator.reversed());
            paperUserListRank.forEach((pu) -> {
                if (pu.getId() == paperUser.getId()) {
                    paperUser.setRank(paperUserListRank.indexOf(pu));
                }
            });
        }
    }

    public GradesBean() {
        init();
    }

    private void init() {
        user = (User) HTTPUtil.getSession().getAttribute("CUR_USER");
        paperUserList = gradesDao.getGradesByUid(user.getId());
        getRanking();
    }

    public void test() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PaperUser> getPaperUserList() {
        return paperUserList;
    }

    public void setPaperUserList(List<PaperUser> paperUserList) {
        this.paperUserList = paperUserList;
    }

}
