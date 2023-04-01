package com.xh.routine.dpac.vo;

import lombok.Data;

import java.util.Date;

@Data
public class DpacTicketInfoVO {

    private Integer pageNum;
    private Integer pageSize;

    /**
     * 工单id
     */
    private Long id;

    /**
     * 工单状态
     */
    private String status;

    /**
     * 邮件发送人
     */
    private String consumerEmail;

    /**
     * 当前工单处理人id
     */
    private Long assignedUserId;

    /**
     * 当前工单处理人所在小组id
     */
    private Long assignedUserGroupId;

    /**
     * 工单创建时间
     */
    private Date createTime;

    /**
     * 邮件接收时间
     */
    private Date receiveTime;

    /**
     * 客服最近处理时间
     */
    private Date processTime;
}
