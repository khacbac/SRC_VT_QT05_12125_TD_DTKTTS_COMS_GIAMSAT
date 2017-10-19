package com.viettel.database.entity;

/**
 * Created by tho xinh dep on 10/13/2017.
 */

public class ConstrNodeEntity {
    private long nodeID;
    private String nodeCode;
    private String nodeName;
    private String nodePopulation;
    private int numPort;
    private int creatorId;
    private String creatorDate;
    private int updatorId;
    private String updateDate;
    private int contructorId;
    private int isActive;
    private int processId;
    private int syncStatus;
    private int catEmployeeId;

    public long getNodeID() {
        return nodeID;
    }

    public void setNodeID(long nodeID) {
        this.nodeID = nodeID;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodePopulation() {
        return nodePopulation;
    }

    public void setNodePopulation(String nodePopulation) {
        this.nodePopulation = nodePopulation;
    }

    public int getNumPort() {
        return numPort;
    }

    public void setNumPort(int numPort) {
        this.numPort = numPort;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorDate() {
        return creatorDate;
    }

    public void setCreatorDate(String creatorDate) {
        this.creatorDate = creatorDate;
    }

    public int getUpdatorId() {
        return updatorId;
    }

    public void setUpdatorId(int updatorId) {
        this.updatorId = updatorId;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getContructorId() {
        return contructorId;
    }

    public void setContructorId(int contructorId) {
        this.contructorId = contructorId;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(int syncStatus) {
        this.syncStatus = syncStatus;
    }

    public int getCatEmployeeId() {
        return catEmployeeId;
    }

    public void setCatEmployeeId(int catEmployeeId) {
        this.catEmployeeId = catEmployeeId;
    }
}
