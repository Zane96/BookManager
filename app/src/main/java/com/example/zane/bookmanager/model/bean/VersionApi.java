package com.example.zane.bookmanager.model.bean;

/**
 * Created by Zane on 16/3/29.
 * 对应于fir项目的项目信息
 */
public class VersionApi {

    /**
     * name : BookManager
     * version : 376
     * changelog : 简单的完成了阅读计划和想读书单。下一步：添加阅读打卡提醒
     * updated_at : 1459063822
     * versionShort : 1.6.0
     * build : 376
     * installUrl : http://download.fir.im/v2/app/install/56c6cc07748aac70db00002e?download_token=94588dc954f72216f5847e6f75d4ff22
     * install_url : http://download.fir.im/v2/app/install/56c6cc07748aac70db00002e?download_token=94588dc954f72216f5847e6f75d4ff22
     * direct_install_url : http://download.fir.im/v2/app/install/56c6cc07748aac70db00002e?download_token=94588dc954f72216f5847e6f75d4ff22
     * update_url : http://fir.im/6z9
     * binary : {"fsize":3623739}
     */

    private String name;
    private String version;
    private String changelog;
    private String versionShort;
    private String build;
    private String installUrl;
    private String install_url;
    private String update_url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getChangelog() {
        return changelog;
    }

    public void setChangelog(String changelog) {
        this.changelog = changelog;
    }

    public String getVersionShort() {
        return versionShort;
    }

    public void setVersionShort(String versionShort) {
        this.versionShort = versionShort;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getInstallUrl() {
        return installUrl;
    }

    public void setInstallUrl(String installUrl) {
        this.installUrl = installUrl;
    }

    public String getInstall_url() {
        return install_url;
    }

    public void setInstall_url(String install_url) {
        this.install_url = install_url;
    }

    public String getUpdate_url() {
        return update_url;
    }

    public void setUpdate_url(String update_url) {
        this.update_url = update_url;
    }
}
