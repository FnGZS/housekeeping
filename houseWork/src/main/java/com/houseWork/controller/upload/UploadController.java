package com.houseWork.controller.upload;

import com.houseWork.entity.upload.UploadPicParam;
import com.houseWork.service.upload.UploadService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/upload")
@Api("文件上传")

public class UploadController {
    @Autowired
    private UploadService uploadService;

    /**
     * 图片上传通用接口 支持批量
     * @param request
     * @param uploadParam
     * @param files
     * @return
     */
    @PostMapping("/pic")
    public ResponseEntity doUploadPic(HttpServletRequest request, UploadPicParam uploadParam,
                                      @RequestParam(value = "files") MultipartFile[] files)  {
        return uploadService.doUploadPic(files, uploadParam,request);
    }

    /**
     *  图片上传单张
     * @param request
     * @param uploadParam
     * @param file
     * @return
     */
    @PostMapping("/avatar")
    public ResponseEntity doUploadAvr(HttpServletRequest request, UploadPicParam uploadParam,
                                      @RequestParam(value = "file" ) MultipartFile file) {
        return uploadService.doUploadAvr(file, uploadParam,request);
    }
    /**
     *  视频上传单个
     * @param request
     * @param uploadParam
     * @param file
     * @return
     */
    @PostMapping("/video")
    public ResponseEntity doUploadVideo(HttpServletRequest request, UploadPicParam uploadParam,
                                      @RequestParam(value = "file") MultipartFile file) {
        return uploadService.doUploadVideo(file, uploadParam,request);
    }
}
