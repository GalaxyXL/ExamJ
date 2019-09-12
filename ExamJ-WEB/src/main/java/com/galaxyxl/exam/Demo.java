package com.galaxyxl.exam;

import com.galaxyxl.exam.dao.GradesDao;
import com.galaxyxl.exam.dao.UserDao;
import com.galaxyxl.exam.model.PaperUser;
import com.galaxyxl.exam.util.EJBUtil;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;

public class Demo {

    public static void main(String[] args) throws Exception {
        GradesDao gradesDao = EJBUtil.getBean(GradesDao.class);
        gradesDao.getGradesByUid(1);
    }

}
