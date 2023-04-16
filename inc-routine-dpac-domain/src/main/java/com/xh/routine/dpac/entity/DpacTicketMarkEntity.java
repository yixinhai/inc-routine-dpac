package com.xh.routine.dpac.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName dpac_ticket_mark
 */
@TableName(value ="dpac_ticket_mark")
@Data
public class DpacTicketMarkEntity implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long ticketId;

    /**
     * 
     */
    private String coldFlag;

    /**
     * 
     */
    private String lockThread;

    /**
     * 
     */
    private Date lockTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DpacTicketMarkEntity other = (DpacTicketMarkEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTicketId() == null ? other.getTicketId() == null : this.getTicketId().equals(other.getTicketId()))
            && (this.getColdFlag() == null ? other.getColdFlag() == null : this.getColdFlag().equals(other.getColdFlag()))
            && (this.getLockThread() == null ? other.getLockThread() == null : this.getLockThread().equals(other.getLockThread()))
            && (this.getLockTime() == null ? other.getLockTime() == null : this.getLockTime().equals(other.getLockTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTicketId() == null) ? 0 : getTicketId().hashCode());
        result = prime * result + ((getColdFlag() == null) ? 0 : getColdFlag().hashCode());
        result = prime * result + ((getLockThread() == null) ? 0 : getLockThread().hashCode());
        result = prime * result + ((getLockTime() == null) ? 0 : getLockTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ticketId=").append(ticketId);
        sb.append(", coldFlag=").append(coldFlag);
        sb.append(", lockThread=").append(lockThread);
        sb.append(", lockTime=").append(lockTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}