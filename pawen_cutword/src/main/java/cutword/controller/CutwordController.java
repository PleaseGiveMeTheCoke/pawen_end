package cutword.controller;

import cutword.util.FastDFSClient;
import cutword.util.ParseString;
import cutword.util.Result;
import cutword.util.StatusCode;
import net.sf.json.JSONArray;
import org.ansj.splitWord.Analysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/cut")
public class CutwordController {
    @PostMapping("/cutword")
    public Result cutFile(MultipartHttpServletRequest request){

        String url = request.getParameter("url");//获取文件
        try{

            String trackerUrl = FastDFSClient.getTrackerUrl();
            String substring = url.substring(trackerUrl.length());
            String groupName = substring.substring(0, 6);
            String remoteFileName = substring.substring(7);
            StringBuilder sb;
            try (InputStream inputStream = FastDFSClient.downFile(groupName, remoteFileName)) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                sb = new StringBuilder();

                String line = null;
                try {
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "/n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            //获取文件内容
            String context = sb.toString();

            String res = ToAnalysis.parse(ParseString.clearNotChinese(context)).toString();


            String[] split = res.split(",");
            Set<String> noun = new HashSet<>();
            Set<String> verb = new HashSet<>();
            Set<String> adj = new HashSet<>();
            for (String s : split) {
                if(s.charAt(s.indexOf("/")+1)=='n'){
                    noun.add(s.substring(0,s.indexOf("/")));
                }else if(s.charAt(s.indexOf("/")+1)=='v') {
                    verb.add(s.substring(0,s.indexOf("/")));
                }else if(s.charAt(s.indexOf("/")+1)=='a'){
                    adj.add(s.substring(0,s.indexOf("/")));
                }
            }
            JSONArray nounJson = JSONArray.fromObject(noun);
            JSONArray verbJson = JSONArray.fromObject(verb);
            JSONArray adjJson = JSONArray.fromObject(adj);
            System.out.println(nounJson.toString());
            StringBuilder ress = new StringBuilder();
            ress.append("N");
            ress.append(nounJson.toString());
            ress.append("V");
            ress.append(verbJson.toString());
            ress.append("A");
            ress.append(adjJson.toString());
            ress.append("C");
            ress.append(context);

            return new Result(true, StatusCode.OK,"文件上传成功",ress.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(false, StatusCode.ERROR,"文件上传失败");
    }

}
