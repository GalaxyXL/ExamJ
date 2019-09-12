package com.galaxyxl.exam.dao;

import com.galaxyxl.exam.model.Paper;
import com.galaxyxl.exam.model.PaperUser;
import com.galaxyxl.exam.model.User;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface GradesDao extends BaseDao<PaperUser, Integer> {
    PaperUser getPaperUserByUidAndPid(Integer uid, Integer pid);

    List<PaperUser> getGradesByUid(Integer uid);

    List<PaperUser> getGradesByPid(Integer pid);

    void setPaperStatus(Paper paper, User user, Integer status);


}
