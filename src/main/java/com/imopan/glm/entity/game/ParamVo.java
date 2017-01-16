package com.imopan.glm.entity.game;

/**
 * Created by Administrator on 2017/1/9.
 */
public class ParamVo {

    private String gid;//游戏id
    private String gameName;//游戏名称
    private String status;//状态
    private String pid;//尖兵id

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ParamVo{" +
                "gid='" + gid + '\'' +
                ", gameName='" + gameName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
