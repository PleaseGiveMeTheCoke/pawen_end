package save.entity;

import java.util.ArrayList;

public class Item {
    String ns = "";
    String vs="";
    String adj="";
    String name = "";

   /* @Override
    public String toString() {
        String s1 = "============="+name+"=============\n";
        String s2 = "标注的名词: "+ns+"\n";
        String s3 = "标注的动词: "+vs+"\n";
        String s4 = "标注的形容词: "+adj+"\n";
        return s1+s2+s3+s4;
    }*/

    @Override
    public String toString() {
        return name+" :{" +
                "名词='" + ns + '\'' +
                ", 动词='" + vs + '\'' +
                ", 形容词='" + adj + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item() {
    }

    public String getNs() {
        return ns;
    }

    public void setNs(String ns) {
        this.ns = ns;
    }

    public String getVs() {
        return vs;
    }

    public void setVs(String vs) {
        this.vs = vs;
    }

    public String getAdj() {
        return adj;
    }

    public void setAdj(String adj) {
        this.adj = adj;
    }

    public Item(String ns, String vs, String adj) {
        this.ns = ns;
        this.vs = vs;
        this.adj = adj;
    }
}
