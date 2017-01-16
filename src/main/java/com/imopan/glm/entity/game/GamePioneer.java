package com.imopan.glm.entity.game;

import java.util.Date;

/**
 * GL游戏尖兵
 * @author
 *
 */
public class GamePioneer {
    /**
     * 任务id
     */
    private String pid;
    /**
     * 游戏id
     */
    private String gid;
    /**
     * 任务状态 1-上架，0-下架
     */
    private String status;
    /**
     * 任务名称
     */
    private String pname;
    /**
     * 游戏名称
     */
    private String gname;
    /**
     * 任务完成数
     */
    private Integer finnum;
    /**
     * 创建人
     */
    private String createUser;

    /**
     * 上架时间
     */
    private Date putawayTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 任务描述
     */
    private String pdesc;
    /**
     * 任务类型 0 注册+首次登陆 1 首次充值
     */
    private String[] ptype;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Integer getFinnum() {
        return finnum;
    }

    public void setFinnum(Integer finnum) {
        this.finnum = finnum;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getPutawayTime() {
        return putawayTime;
    }

    public void setPutawayTime(Date putawayTime) {
        this.putawayTime = putawayTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    public String[] getPtype() {
        return ptype;
    }

    public void setPtype(String[] ptype) {
        this.ptype = ptype;
    }

/*    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return  false;
        }

        if (((GamePioneer)obj).getPid().equals(this.pid)) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int result = pid != null ? pid.hashCode() : 0;
        return result;
    }*/
}
