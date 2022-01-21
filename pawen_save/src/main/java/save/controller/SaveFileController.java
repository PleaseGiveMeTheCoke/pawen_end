package save.controller;

import net.sf.json.JSONArray;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import save.entity.Item;
import save.util.Result;
import save.util.StatusCode;
import save.util.WriteInFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;


@RestController
@RequestMapping("/save")
public class SaveFileController {
    @GetMapping("/saveFile/{content}")
    public ResponseEntity<FileSystemResource> saveFile(@PathVariable("content")String content, HttpServletResponse response){
        System.out.println(content);
        String[] split2 = content.split("-");
        String s = split2[0];
        String[] tags = s.split(",");
        String[] split = split2[1].split("}");
        int n = split.length/3;
        ArrayList<Item> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Item it = new Item();
            String noun = split[i*3].substring(7).replaceAll(":","");
            String verb = split[i*3+1].substring(7).replaceAll(":","");
            String adj = split[i*3+2].substring(7).replaceAll(":","");
            it.setNs(noun);
            it.setVs(verb);
            it.setAdj(adj);
            it.setName(tags[i]);
            res.add(it);
        }
        String fileContent = "";
        for (Item re : res) {
            fileContent+=re.toString();
        }
        WriteInFile.generateAndWrite("marking.json",fileContent);
        System.out.println(JSONArray.fromObject(res).toString());
       return downloadFile("marking.json");

    }
    public ResponseEntity<FileSystemResource> downloadFile(String path) {
        return export(new File(path));
    }
    public ResponseEntity<FileSystemResource> export(File file) {
        if (file == null) {
            System.out.println("========null!!!=======");
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new FileSystemResource(file));
    }

}
