package save.controller;

import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import save.entity.MyFile;
import save.service.FileService;
import save.util.Result;
import save.util.StatusCode;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/search")
public class SearchFileController {
    @Autowired
    FileService fileService;
    @GetMapping("/searchFile")
    public Result SearchFile(String name,int page){
        Map<String,Object> searchMap = new HashMap<>();
        searchMap.put("name",name);
        Page<MyFile> page1 = fileService.findPage(page, 10, searchMap);
        return new Result(true,StatusCode.OK,"查询数据库成功",page1.getResult());
    }
    @GetMapping("/addDownLoad")
    public Result addDownLoad(int fid,int download){
        fileService.addDownLoad(fid,download);
        return new Result(true,StatusCode.OK,"增加下载量成功",null);
    }
}
