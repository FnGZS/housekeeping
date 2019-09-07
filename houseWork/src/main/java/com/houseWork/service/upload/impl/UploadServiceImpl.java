package com.houseWork.service.upload.impl;

import com.houseWork.entity.response.ResponseResult;
import com.houseWork.entity.response.Result;
import com.houseWork.entity.upload.UploadPicParam;
import com.houseWork.service.upload.UploadService;
import com.houseWork.utils.DateUtil;
import com.houseWork.utils.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zzc
 */
@Service
public class UploadServiceImpl implements UploadService {

    /**
     * 访问图片地址的前缀
     */
    protected String path = "https://www.sxscott.com/housework/image/";
    /**
     * 保存图片地址的前缀
     */
    protected String filePath = "/www/wwwroot/www.sxscott.com/housework/image/";

    /**
     * 访问图片地址的前缀
     */
    protected String videoPath = "https://www.sxscott.com/housework/video/";
    /**
     * 保存图片地址的前缀
     */
    protected String videoFilePath = "/www/wwwroot/www.sxscott.com/housework/video/";
    /**
     * 图片文件扩展名限制
     */
    protected String picExtLimit = "jpg,jpeg,gif,png,bmp";
    /**
     * 视频文件扩展名限制
     */
    protected String videoExtLimit = "mp3,wma,avi,rm,rmvb,flv,mpg,mov,mkv";
    /**
     * 图片单个上传文件大小限制,单位为字节
     */
    protected long sizeLimit = 10 * 1024 * 1024;
    /**
     * 视频单个上传文件大小限制,单位为字节
     */
    protected long videoSizeLimit = 300 * 1024 * 1024;

    @Override
    public ResponseEntity doUploadAvr(MultipartFile file, UploadPicParam uploadParam){

        if(file==null) {
            return new ResponseEntity<>(ResponseResult.errResponse("文件上传失败，内容为空"),HttpStatus.BAD_REQUEST);
        }
        if(fileSizeValidate(file)) {
            return new ResponseEntity<>(ResponseResult.errResponse("文件上传失败，大小超过限制"),HttpStatus.BAD_REQUEST);
        }
        //检验文件类型
        if(picExtValidate(getExtName(file.getOriginalFilename()))) {
            return new ResponseEntity<>(ResponseResult.errResponse("文件上传失败，文件格式错误"),HttpStatus.BAD_REQUEST);
        }

        return getResponseEntity(file, uploadParam.getType(),path,filePath);
    }



    @Override
    public ResponseEntity doUploadPic(MultipartFile[] files, UploadPicParam uploadParam){
        if(files==null||files.length==0) {
            return new ResponseEntity<>(ResponseResult.errResponse("文件上传失败，内容为空"),HttpStatus.BAD_REQUEST);
        }
        for(MultipartFile file : files) {
            //检验文件大小是否超过限制
            if(fileSizeValidate(file)) {
                return new ResponseEntity<>(ResponseResult.errResponse("文件上传失败，大小超过限制"),HttpStatus.BAD_REQUEST);
            }
            //检验文件类型
            if(picExtValidate(getExtName(file.getOriginalFilename()))) {
                    return new ResponseEntity<>(ResponseResult.errResponse("文件上传失败，文件格式错误"),HttpStatus.BAD_REQUEST);
            }
        }
        Result result;
        List<String> urlList = new ArrayList<>();
        for(int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            result = (Result) getResponseEntity(file, uploadParam.getType(),path,filePath).getBody();
            //如果上传不成功
            if(result.getCode()!=200){
                return new ResponseEntity<>(ResponseResult.errResponse("上传失败"+result.getMsg()),HttpStatus.BAD_REQUEST);
            }
            urlList.add(result.getData().toString());
        }
        return new ResponseEntity<>(ResponseResult.successResponse(urlList),HttpStatus.OK);
    }

    @Override
    public ResponseEntity doUploadVideo(MultipartFile file, UploadPicParam uploadParam) {

        if(file==null) {
            return new ResponseEntity<>(ResponseResult.errResponse("文件上传失败，内容为空"),HttpStatus.BAD_REQUEST);
        }
        if(videoSileSizeValidate(file)) {
            return new ResponseEntity<>(ResponseResult.errResponse("文件上传失败，大小超过限制"),HttpStatus.BAD_REQUEST);
        }
        //检验文件类型
        if(videoExtValidate(getExtName(file.getOriginalFilename()))) {
            return new ResponseEntity<>(ResponseResult.errResponse("文件上传失败，文件格式错误"),HttpStatus.BAD_REQUEST);
        }

        String picType=uploadParam.getType();
        return getResponseEntity(file, picType, videoPath,videoFilePath);

    }

    /**
     *  文件上传
     * @param file 文件
     * @param type 文件归类
     * @return
     */
    private ResponseEntity getResponseEntity(MultipartFile file, String type, String path,String filePath) {
        String url;
        try {
            url = fileUpload(file, type,path,filePath);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseResult.errResponse("上传失败"+e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ResponseResult.successResponse(url),HttpStatus.OK);
    }
    /**
     * 文件上传
     * @param file 文件
     * @param type 文件归类
     * @return 地址
     * @throws Exception 异常
     */
    private String fileUpload(MultipartFile file, String type,String path,String filePath)throws Exception {
        String random = RandomUtil.getRandomNumString(8);
        int length = file.getOriginalFilename().split("\\.").length;

        String fileName =type+"_"+ DateUtil.formatDate(new Date(), DateUtil.dtLongLong)+"_"+random+"."+file.getOriginalFilename().split("\\.")[length-1];
        //MultipartFile 类型文件不支持创s建父目录，所以得用File类型
        File newFile = new File(filePath+type+"/"+fileName);
        File fileParent = newFile.getParentFile();
        //父目录不存在就创建目录
        if(!fileParent.exists()){
            fileParent.mkdirs();
        }
        file.transferTo(new File(filePath+type+"/"+fileName));
        String url = path+type+"/"+fileName;
        return url;
    }

    /**
     * 图片文件扩展名验证
     *
     * @param ext 文件扩展名
     * @return boolean
     */
    private boolean picExtValidate(String ext) {
        return !extValidate(ext, picExtLimit);
    }
    /**
     * 视频文件扩展名验证
     *
     * @param ext 文件扩展名
     * @return boolean
     */
    private boolean videoExtValidate(String ext) {
        return !extValidate(ext, videoExtLimit);
    }
    /**
     * 文件扩展名验证
     *
     * @param ext 文件扩展名
     * @return boolean
     */
    private boolean extValidate(String ext, String extLimit) {
        if ("*".equals(ext)) {
            return true;
        }
        ext = "," + ext.toLowerCase() + ",";
        extLimit = "," + extLimit.toLowerCase() + ",";
        return extLimit.contains(ext);
    }

    /**
     * 图片文件大小验证
     *
     * @param file 文件
     * @return  Boolean
     */
    private boolean fileSizeValidate(MultipartFile file) {
        return file.getSize() > sizeLimit;
    }
    /**
     * 视频文件大小验证
     *
     * @param file 文件
     * @return  Boolean
     */
    private boolean videoSileSizeValidate(MultipartFile file) {
        return file.getSize() > videoSizeLimit;
    }
    /**
     * 获得文件扩展名
     *
     * @param fileName 文件名称
     * @return 名称
     */
    private String getExtName(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
