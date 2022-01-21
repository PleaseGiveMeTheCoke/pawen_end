package save.entity;

public class PublicFile extends MyFile{
    String contributor;

    public PublicFile(MyFile file,String contributor) {
        this.createTime = file.createTime;
        this.descc = file.descc;
        this.download = file.download;
        this.fid = file.fid;
        this.ispublic = file.ispublic;
        this.size = file.size;
        this.name = file.name;
        this.pid = file.pid;
        this.typee = file.typee;
        this.url = file.url;
        this.contributor = contributor;
    }

    @Override
    public String toString() {
        return "PublicFile{" +
                "contributor='" + contributor + '\'' +
                ", pid=" + pid +
                ", fid=" + fid +
                ", contributor='" + contributor + '\'' +
                ", download='" + download + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", typee='" + typee + '\'' +
                ", ispublic='" + ispublic + '\'' +
                ", descc='" + descc + '\'' +
                ", createTime='" + createTime + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }
}
