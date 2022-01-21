package save.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import save.config.RabbitMQConfig;
import save.entity.FastDFSFile;
import save.entity.MyFile;
import save.service.FileService;
import save.util.FastDFSClient;
import save.util.Result;
import save.util.StatusCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/file")
public class FileUploadController {
    final String REDIS_FILE_kEY = "redis_file_";
    final String REDIS_REPORT_kEY = "redis_report_";

    @Autowired
    FileService fileService;
    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    public RabbitTemplate rabbitTemplate;
    @PostMapping("/uploadFile")
    public Result readFile(MultipartHttpServletRequest request){
        String name = request.getParameter("name");
        String ispublic = request.getParameter("ispublic");
        String type = request.getParameter("type");
        String desc = request.getParameter("desc");
        String pid = request.getParameter("pid");
        String size = getSize(request.getParameter("size"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = sdf.format(new java.util.Date());
        MultipartFile file = request.getFile("file");//获取文件
        try{
            //判断文件是否存在
            if (file == null){
                throw new RuntimeException("文件不存在");
            }
            //获取文件的完整名称
            String originalFilename = file.getOriginalFilename();
            if (StringUtils.isEmpty(originalFilename)){
                throw new RuntimeException("文件不存在");
            }
            //获取文件的扩展名称  abc.jpg   jpg
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

            //获取文件内容
            byte[] content = file.getBytes();

            //创建文件上传的封装实体类
            FastDFSFile fastDFSFile = new FastDFSFile(originalFilename,content,extName);

            //基于工具类进行文件上传,并接受返回参数  String[]
            String[] uploadResult = FastDFSClient.upload(fastDFSFile);

            //封装返回结果
            String url = FastDFSClient.getTrackerUrl()+uploadResult[0]+"/"+uploadResult[1];
            System.out.println(url);

            MyFile myFile = new MyFile("0",Integer.parseInt(pid),0,url,name,type,ispublic,desc,time,size);

            fileService.add(myFile);
            Set<Object> keys = redisTemplate.boundHashOps(REDIS_FILE_kEY + pid).keys();
            for (Object key : keys) {
                redisTemplate.opsForHash().delete(REDIS_FILE_kEY + pid,key);
            }
            return new Result(true,StatusCode.OK,"文件上传成功",myFile);


        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(false, StatusCode.ERROR,"文件上传失败");
    }

    private String getSize(String sizes) {
        int size = Integer.parseInt(sizes);
        if(size/1024==0){
            return size+"KB";
        }else if(size/1024<1024){
            return (double)size/1024.0+"MB";
        }else{
            return (double)size/(1024*1024.0)+"GB";
        }
    }

    @GetMapping("/findPageFile")
    public Result findAllFile(int pid,String name,String desc,String typee,int page){
        Map<String,Object> searchMap = new HashMap<>();
        searchMap.put("name",name);
        searchMap.put("desc",desc);
        searchMap.put("typee",typee);
        if(page==1&&(name.equals("")&&desc.equals("")&&typee.equals(""))){

            List values = redisTemplate.boundHashOps(REDIS_FILE_kEY + pid).values();
            if(values==null||values.size()==0){
                Page<MyFile> page1 = fileService.findPage(1, 10, pid,null);
                List<MyFile> result = page1.getResult();
                for (MyFile myFile : result) {
                    redisTemplate.opsForHash().put(REDIS_FILE_kEY + pid,myFile.getFid()+"", JSON.toJSONString(myFile));
                }
                return new Result(true,StatusCode.OK,"查询缓存成功",redisTemplate.boundHashOps(REDIS_FILE_kEY + pid).values());
            }
            return new Result(true,StatusCode.OK,"查询缓存成功",values);
        }

        Page<MyFile> page1 = fileService.findPage(page, 10, pid,searchMap);
        List<MyFile> result = page1.getResult();
        return new Result(true,StatusCode.OK,"查询数据库成功",page1.getResult());
    }
    @GetMapping("/deleteFile")
    public Result deleteFile(int fid) throws Exception {
        MyFile one = fileService.findOne(fid);
        Set<Object> keys = redisTemplate.boundHashOps(REDIS_FILE_kEY + one.getPid()).keys();
        for (Object key : keys) {


            redisTemplate.opsForHash().delete(REDIS_FILE_kEY + one.getPid(),key);
        }

        fileService.delete(fid);
        String url = one.getUrl();
        String trackerUrl = FastDFSClient.getTrackerUrl();
        String substring = url.substring(trackerUrl.length());
        String groupName = substring.substring(0, 6);
        String remoteFileName = substring.substring(7);
        FastDFSClient.deleteFile(groupName,remoteFileName);
        return new Result(true,StatusCode.OK,"文件删除成功");
    }

    @GetMapping("/reportFile")
    public Result reportFile(int fid,int pid) throws Exception {
        if(redisTemplate.hasKey(REDIS_REPORT_kEY+pid)){
            return new Result(true,StatusCode.ACCESSERROR,"文件举报失败");
        }
        redisTemplate.opsForValue().set(REDIS_REPORT_kEY+pid, "1", 10, TimeUnit.MINUTES);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,"pawen.haha",fid);
        return new Result(true,StatusCode.OK,"文件举报成功");
    }



}
