package com.cfg.deploytools.model;

import java.io.Serializable;

/**
 * ClassName: Task
 * Description:
 * date: 2020/6/8 10:34
 *
 * @author CFG
 * @since JDK 1.8
 */
public class Task implements Serializable {

    private static final long serialVersionUID = -6537854290451339990L;

    private Integer taskId;

    private String name;

    private String status;

    private String openBy;


}
