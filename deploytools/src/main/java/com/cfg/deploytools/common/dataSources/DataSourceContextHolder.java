package com.cfg.deploytools.common.dataSources;

/**
 * ClassName: DataSourceContextHolder
 * Description:
 * date: 2020/6/5 10:43
 *
 * @author CFG
 * @since JDK 1.8
 */
public class DataSourceContextHolder {

    // 使用ThreadLocal维护变量，ThreadLocal为每个使用该变量的线程提供独立的变量副本,
    // 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /*
     * @Author wadreamer
     * @Description //TODO 设置数据源
     * @Date 10:47 2020/6/5
     * @Param [type]
     * @return void
     **/
    public static void setDataSource(String type) {
        CONTEXT_HOLDER.set(type);
    }

    /*
     * @Author wadreamer
     * @Description //TODO 获取数据源
     * @Date 10:49 2020/6/5
     * @Param []
     * @return java.lang.String
     **/
    public static String getDataSource() {
        return CONTEXT_HOLDER.get();
    }

    /*
     * @Author wadreamer
     * @Description //TODO 清除所有数据源
     * @Date 10:49 2020/6/5
     * @Param []
     * @return void
     **/
    public static void clearDataSource() {
        CONTEXT_HOLDER.remove();
    }
}
