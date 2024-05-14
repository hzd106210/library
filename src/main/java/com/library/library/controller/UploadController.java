package com.library.library.controller;

import java.io.FileOutputStream;
import java.util.Date;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {
  private static final String COVER_DIR = "/src/main/resources/assets/cover/";

  @PostMapping("/uploadCover")
  public ResponseResult<String> uoploadCover(@RequestParam("cover") MultipartFile file) {
    if (file.isEmpty()) {
      return ResponseResult.fail("文件不能为空");
    }
    try {
      String originalFilename = file.getOriginalFilename();
      if (originalFilename != null) {
        // 后缀
        String subfix = originalFilename.substring(originalFilename.indexOf("."),
            originalFilename.length());
        String newName = new Date().getTime() + subfix;
        String path = System.getProperty("user.dir");
        try (FileOutputStream fileOutputStream = new FileOutputStream(path + COVER_DIR + newName)) {
          byte[] bytes = file.getBytes();
          fileOutputStream.write(bytes);
        }
        return ResponseResult.success("/static/cover/" + newName, "上传成功");
      }

      return ResponseResult.fail("上传失败");
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseResult.fail("上传失败");
    }
  }
}
