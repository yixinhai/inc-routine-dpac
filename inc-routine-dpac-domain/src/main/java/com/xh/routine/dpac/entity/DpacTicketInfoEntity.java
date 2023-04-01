package com.xh.routine.dpac.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 工单信息表
 * @TableName dpac_ticket_info
 */
@Data
@TableName("dpac_ticket_info")
public class DpacTicketInfoEntity implements Serializable {
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

    private static final long serialVersionUID = 1L;

}