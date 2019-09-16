package com.houseWork.service.upload;

import com.houseWork.entity.upload.UploadPicParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zzc
 */
public interface UploadService {
    /**
     *上传图片（单个）
     * @param file 图片文件
     * @param uploadParam 上传参数
     * @param request   请求
     * @return
     */
    ResponseEntity doUploadAvr(MultipartFile file, UploadPicParam uploadParam,HttpServletRequest request);

    /**
     *上传图片（多个）
     * @param files 多个图片文件
     * @param uploadParam 上传参数
      *@param request   请求
     * @return
     */
    ResponseEntity doUploadPic(MultipartFile[] files, UploadPicParam uploadParam,HttpServletRequest request);

    /**
     *上传视频
     * @param file 视频文件
     * @param uploadParam 上传参数
     * @param request   请求
     * @return
     */
    ResponseEntity doUploadVideo(MultipartFile file, UploadPicParam uploadParam,HttpServletRequest request);
}
