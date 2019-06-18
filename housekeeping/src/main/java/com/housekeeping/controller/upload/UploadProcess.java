package com.housekeeping.controller.upload;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.housekeeping.controller.upload.model.MultiPicUploadModel;
import com.housekeeping.controller.upload.param.UploadPicParam;
import com.housekeeping.model.enums.HttpCodeEnum;
import com.housekeeping.utils.DateUtil;
@Component
public class UploadProcess {
	/**
	 * 访问图片地址的前缀
	 */
	 protected String path = "https://www.sxscott.com/crazyBirdimg/";
	 /**
	  * 保存图片地址的前缀
	  */
	 //protected String filePath = "D:/www/wwwroot/www.sxscott.com/crazyBirdimg/";
	protected String filePath = "/www/wwwroot/www.sxscott.com/crazyBirdimg/";
	/**
	 * 文件扩展名限制
     */
     protected String picExtLimit = "jpg,jpeg,gif,png,bmp";
    /**
      * 单个上传文件大小限制,单位为字节
     */
     protected long sizeLimit = 10 * 1024 * 1024; 
    /**
      * 附件扩张名限制
     */
    protected String attachExtLimit = "jpg,jpeg,gif,png,bmp,xls,xlsx,pdf,silk,mp3,dat";
    
	public MultiPicUploadModel doUploadPic(MultipartFile[] files, UploadPicParam uploadParam){
		MultiPicUploadModel model = new MultiPicUploadModel();
		if(files==null||files.length==0) {
			model.setCode(HttpCodeEnum.ERROR.getCode());
			model.setMessage("文件上传失败，内容为空");
			return model;
		}
		for(MultipartFile file : files) {
			//检验文件大小是否超过限制
			if(!fileSizeValidate(file)) {
				model.setCode(HttpCodeEnum.ERROR.getCode());
				model.setMessage("文件上传失败，大小超过限制");
				return model;	
			}
			//检验文件类型
			if(!picExtValidate(getExtName(file.getOriginalFilename()))) {
				model.setCode(HttpCodeEnum.ERROR.getCode());
				model.setMessage("文件上传失败，文件格式错误");
				return model;
			}
		}
		String picType=uploadParam.getPicType();
		String url= null;
		List<String> urlList = new ArrayList<>();
		for(int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			try {
				url = fileUpload(file, picType);
				urlList.add(url);
			} catch (Exception e) {
				e.printStackTrace();
				model.setCode(HttpCodeEnum.ERROR.getCode());
				model.setMessage("上传失败");
				return model ;
			}
		}
		model.setUrlList(urlList);
		model.setMessage("上传成功");
		return model;
	}
	/**
	 * 图片上传
	 * @param file
	 * @param picType
	 * @return
	 * @throws Exception
	 */
	private String fileUpload(MultipartFile file, String picType)throws Exception { 
		
	int length = file.getOriginalFilename().split("\\.").length;
	  String fileName =picType+"_"+DateUtil.formatDate(new Date(), DateUtil.dtLongLong)+"."+file.getOriginalFilename().split("\\.")[length-1];
	  //MultipartFile 类型文件不支持创建父目录，所以得用File类型
	  File newfile = new File(filePath+picType+"/"+fileName);
	  File fileParent = newfile.getParentFile();  
	  //父目录不存在就创建目录
	  if(!fileParent.exists()){  
		  fileParent.mkdirs();  
		 }  
	  file.transferTo(new File(filePath+picType+"/"+fileName));
	  String picUrl = path+picType+"/"+fileName;
	  return picUrl;		
	}
	
    /**
     * 图片文件扩展名验证
     * 
     * @param ext
     * @return
     */
	private boolean picExtValidate(String ext) {
        return extValidate(ext, picExtLimit);
    }
	
    /**
     * 文件扩展名验证
     * 
     * @param ext
     * @return
     */
	private boolean extValidate(String ext, String extLimit) {
        if ("*".equals(ext)) {
            return true;
        }
        ext = "," + ext.toLowerCase() + ",";
        extLimit = "," + extLimit.toLowerCase() + ",";
        if (extLimit.indexOf(ext) != -1) {
            return true;
        }
        return false;
    }
    
    /**
     * 文件大小验证
     * 
     * @param ext
     * @return
     */
	private boolean fileSizeValidate(MultipartFile file) {
        if (file.getSize() <= sizeLimit) {
            return true;
        }
        return false;
    }
	
    /**
     * 获得文件扩展名
     * 
     * @param fileName
     * @return
     */
	private String getExtName(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
   	
}
