package com.cfg.deploytools.shiro.utils;

import com.cfg.deploytools.model.User;
import com.cfg.deploytools.shiro.service.UserRealm;
import com.cfg.deploytools.utils.BeanUtils;
import com.cfg.deploytools.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * ClassName: ShiroUtils
 * Description: Shiro 工具类
 * date: 2020/6/8 10:01
 *
 * @author CFG
 * @since JDK 1.8
 */
public class ShiroUtils {


    private ShiroUtils() {
    }

    /**
     * 获取shiro subject
     *
     * @return
     * @author fuce
     * @Date 2019年11月21日 上午10:00:55
     */
    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取登录session
     *
     * @return
     * @author fuce
     * @Date 2019年11月21日 上午10:00:41
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 退出登录
     *
     * @author fuce
     * @Date 2019年11月21日 上午10:00:24
     */
    public static void logout() {
        getSubjct().logout();
    }

    /**
     * 获取登录用户model
     *
     * @return
     * @author fuce
     * @Date 2019年11月21日 上午10:00:10
     */
    public static User getUser() {
        User user = null;
        Object obj = getSubjct().getPrincipal();
        if (StringUtils.isNotNull(obj)) {
            user = new User();
            BeanUtils.copyBeanProp(user, obj);
        }
        return user;
    }

    /**
     * set用户
     *
     * @param user
     * @author fuce
     * @Date 2019年11月21日 上午9:59:52
     */
    public static void setUser(User user) {
        Subject subject = getSubjct();
        PrincipalCollection principalCollection = subject.getPrincipals();
        String realmName = principalCollection.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(user, realmName);
        // 重新加载Principal
        subject.runAs(newPrincipalCollection);
    }

    /**
     * 清除授权信息
     *
     * @author fuce
     * @Date 2019年11月21日 上午9:59:37
     */
    public static void clearCachedAuthorizationInfo() {
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        UserRealm realm = (UserRealm) rsm.getRealms().iterator().next();
        realm.clearCachedAuthorizationInfo();
    }

    /**
     * 获取登录用户id
     *
     * @return
     * @author fuce
     * @Date 2019年11月21日 上午9:58:55
     */
    public static Integer getUserId() {
        User tsysUser = getUser();
        if (tsysUser == null || tsysUser.getId() == null) {
            throw new RuntimeException("用户不存在！");
        }
        return tsysUser.getId();
    }

    /**
     * 获取登录用户name
     *
     * @return
     * @author fuce
     * @Date 2019年11月21日 上午9:58:48
     */
    public static String getLoginName() {
        User tsysUser = getUser();
        if (tsysUser == null) {
            throw new RuntimeException("用户不存在！");
        }
        return tsysUser.getAccount();
    }

    /**
     * 获取登录用户ip
     *
     * @return
     * @author fuce
     * @Date 2019年11月21日 上午9:58:26
     */
    public static String getIp() {
        return getSubjct().getSession().getHost();
    }

    /**
     * 获取登录用户sessionid
     *
     * @return
     * @author fuce
     * @Date 2019年11月21日 上午9:58:37
     */
    public static String getSessionId() {
        return String.valueOf(getSubjct().getSession().getId());
    }
}
