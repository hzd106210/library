package com.library.library.controller;

import java.io.File;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
  private static final String COVER_DIR = "/assets/cover/";

  @PostMapping("/uploadCover")
  public ResponseResult<String> uoploadCover(@RequestParam("cover") MultipartFile file) {
    if (file.isEmpty()) {
      return ResponseResult.fail("文件不能为空");
    }
    try {
      String originalFilename = file.getOriginalFilename();
      if (originalFilename != null) {
        // 后缀
        String subfix = originalFilename.substring(originalFilename.indexOf("."), originalFilename.length());
        String newName = new Date().getTime() + subfix;
        File uploadFile = new File(COVER_DIR + newName);
        file.transferTo(uploadFile);
      }
      return ResponseResult.success("/static/cover/", originalFilename);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseResult.fail("上传失败");
    }
  }
}
