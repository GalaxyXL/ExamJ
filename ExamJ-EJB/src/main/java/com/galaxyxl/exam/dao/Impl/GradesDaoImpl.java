package com.galaxyxl.exam.dao.Impl;

import com.galaxyxl.exam.dao.GradesDao;
import com.galaxyxl.exam.model.Paper;
import com.galaxyxl.exam.model.PaperUser;
import com.galaxyxl.exam.model.User;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class GradesDaoImpl extends BaseDaoImpl<PaperUser, Integer> implements GradesDao {

    @Override
    public List<PaperUser> getGradesByUid(Integer uid) {
        TypedQuery<PaperUser> query = em.createQuery("select pu from PaperUser as pu where pu.user.id = :uid and pu.status = 3", PaperUser.class);
        query.setParameter("uid", uid);
        return query.getResultList();
    }

    @Override
    public List<PaperUser> getGradesByPid(Integer pid) {
        TypedQuery<PaperUser> query = em.createQuery("select pu from PaperUser as pu where pu.paper.id = :pid and pu.status = 3", PaperUser.class);
        query.setParameter("pid", pid);
        return query.getResultList();
    }

    @Override
    public PaperUser getPaperUserByUidAndPid(Integer uid, Integer pid)  {
        TypedQuery<PaperUser> query = em.createQuery("select pu from PaperUser as pu where pu.paper.id = :pid and pu.user.id = :uid", PaperUser.class);
        query.setParameter("pid", pid);
        query.setParameter("uid", uid);
        PaperUser paperUser = null;
        try {
            paperUser = query.getSingleResult();
        } catch (NoResultException e) {
            log.debug("No Result found in GradesDao.");
        }
        return paperUser;
    }

    @Override
    public void setPaperStatus(Paper paper, User user, Integer status) {
        PaperUser paperUser = getPaperUserByUidAndPid(user.getId(), paper.getId());
        if (paperUser == null) {
            PaperUser paperUser1 = new PaperUser();
            paperUser1.setPaper(paper);
            paperUser1.setUser(user);
            paperUser1.setStatus(status);
            save(paperUser1);
        } else
            paperUser.setStatus(status);
    }
}
