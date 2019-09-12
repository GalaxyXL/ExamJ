import com.galaxyxl.exam.dao.UserDao;
import com.galaxyxl.exam.model.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;

public class Demo {
    public static void main(String[] args) throws Exception {
        Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        Context ctx = new InitialContext(jndiProperties);

        String fullName = "ejb:/ExamJ-EJB_EJB exploded/UserDaoImpl!com.galaxyxl.exam.dao.UserDao";
        UserDao userDao = (UserDao) ctx.lookup(fullName);
        User user = userDao.findByUserName("admin");
        System.out.println(user.getPassword());
    }
}
