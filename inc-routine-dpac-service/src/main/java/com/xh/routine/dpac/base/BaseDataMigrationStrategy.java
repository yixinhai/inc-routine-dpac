package com.xh.routine.dpac.base;


/**
 * 数据迁移策略
 */
public interface BaseDataMigrationStrategy {

    void execProcess();

    /**
     * 将热库数据同步到mark表中
     */
    void synchronizeData();

    /**
     * 标记冷数据
     */
    void checkClodData();

    /**
     * 迁移冷数据
     */
    void migrateColdData();

    /**
     * 将冷数据从热库中删除
     * @param ticketId
     */
    void removeHotData(Long ticketId) throws Exception;
}
