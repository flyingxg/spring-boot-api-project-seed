package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 问题
     */
    private String problem;

    /**
     * 答案
     */
    private String answer;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取问题
     *
     * @return problem - 问题
     */
    public String getProblem() {
        return problem;
    }

    /**
     * 设置问题
     *
     * @param problem 问题
     */
    public void setProblem(String problem) {
        this.problem = problem;
    }

    /**
     * 获取答案
     *
     * @return answer - 答案
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * 设置答案
     *
     * @param answer 答案
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}