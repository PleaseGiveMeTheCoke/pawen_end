package save.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Table(name="file")
public class MyFile implements Serializable {
    Integer pid;
    @Id
    Integer fid;

    public MyFile(Integer pid, Integer fid, String url, String name, String typee, String ispublic, String descc) {
        this.fid = fid;
        this.pid = pid;
        this.url = url;
        this.name = name;
        this.typee = typee;
        this.ispublic = ispublic;
        this.descc = descc;
    }
    String contributor;

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    String download;
    String url;
    String name;
    //TODO 保留字
    String typee;
    String ispublic;
    String descc;
    String createTime;
    String size;

    @Override
    public String toString() {
        return "MyFile{" +
                "pid=" + pid +
                ", fid=" + fid +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", typee='" + typee + '\'' +
                ", ispublic='" + ispublic + '\'' +
                ", descc='" + descc + '\'' +
                ", createTime='" + createTime + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    public String getDescc() {
        return descc;
    }

    public void setDescc(String descc) {
        this.descc = descc;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public MyFile(String download,Integer pid, Integer fid, String url, String name, String typee, String ispublic, String descc, String createTime, String size) {
        this.pid = pid;
        this.fid = fid;
        this.url = url;
        this.name = name;
        this.typee = typee;
        this.ispublic = ispublic;
        this.descc = descc;
        this.createTime = createTime;
        this.size = size;
        this.download = download;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypee() {
        return typee;
    }

    public void setTypee(String typee) {
        this.typee = typee;
    }

    public String getIspublic() {
        return ispublic;
    }

    public void setIspublic(String ispublic) {
        this.ispublic = ispublic;
    }

    public String getDesc() {
        return descc;
    }

    public void setDesc(String desc) {
        this.descc = desc;
    }

    public MyFile() {
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

}
