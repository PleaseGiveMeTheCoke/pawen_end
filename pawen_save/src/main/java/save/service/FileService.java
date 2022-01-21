package save.service;

import com.github.pagehelper.Page;
import save.entity.MyFile;
import save.entity.PublicFile;

import java.util.List;
import java.util.Map;

public interface FileService {

    void add(MyFile file);

    List<MyFile> findAll(int pid);

    void delete(int fid);

    MyFile findOne(int fid);

    Page<MyFile> findPage(int page, int size, int pid, Map<String,Object> searchMap);

    Page<MyFile> findPage(int page, int size, Map<String,Object> searchMap);

    void addDownLoad(int fid,int download);


}
