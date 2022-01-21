package save.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import save.dao.FileMapper;
import save.dao.PawerMapper;
import save.entity.MyFile;
import save.entity.PublicFile;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class FileService implements save.service.FileService {
    @Autowired
    PawerMapper pawerMapper;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public void add(MyFile file) {
        fileMapper.insertSelective(file);
    }

    @Override
    public List<MyFile> findAll(int pid) {
        Example example=new Example(MyFile.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pid",pid);
        return fileMapper.selectByExample(example);
    }

    @Override
    public void delete(int fid) {
        fileMapper.deleteByPrimaryKey(fid);
    }

    @Override
    public MyFile findOne(int fid) {
        MyFile myFile = fileMapper.selectByPrimaryKey(fid);
        return myFile;
    }

    @Override
    public Page<MyFile> findPage(int page, int size, int pid, Map<String,Object> searchMap) {


        PageHelper.startPage(page,size);
        Example example=new Example(MyFile.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("pid",pid);
        if(searchMap!=null){
            if(searchMap.get("typee")!=null&&!"".equals(searchMap.get("typee"))){
                System.out.println();
                criteria.andEqualTo("typee",searchMap.get("typee"));
            }
            if(searchMap.get("name")!=null&&!"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            if(searchMap.get("desc")!=null&&!"".equals(searchMap.get("desc"))){
                criteria.andLike("descc","%"+searchMap.get("desc")+"%");
            }
        }
        Page<MyFile> page1= (Page<MyFile>)fileMapper.selectByExample(example);
        return page1;

    }

    @Override
    public Page<MyFile> findPage(int page, int size, Map<String, Object> searchMap) {
        PageHelper.startPage(page,size);
        Example example=new Example(MyFile.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("ispublic","true");
        if(searchMap!=null){
            if(searchMap.get("name")!=null&&!"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
        }
        Page<MyFile> page1= (Page<MyFile>)fileMapper.selectByExample(example);
        for (int i = 0; i < page1.size(); i++) {
            MyFile myFile = page1.get(i);
            myFile.setContributor(pawerMapper.selectByPrimaryKey(myFile.getPid()).getUsername());
        }
        return page1;
    }

    @Override
    public void addDownLoad(int fid,int download) {
        MyFile myFile = new MyFile();
        myFile.setFid(fid);
        myFile.setDownload(download+1+"");
        fileMapper.updateByPrimaryKey(myFile);

    }
}
