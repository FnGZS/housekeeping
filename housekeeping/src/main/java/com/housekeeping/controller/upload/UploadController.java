package com.housekeeping.controller.upload;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.housekeeping.controller.upload.model.MultiPicUploadModel;
import com.housekeeping.controller.upload.param.UploadPicParam;
import com.housekeeping.exception.UploadException;



/**
 * 图片上传
 * @author zzc
 *
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
	@Autowired
	private UploadProcess uploadProcess;
	/**
	 * 图片上传通用接口 支持批量
	 * @param request
	 * @param uploadParam
	 * @param files
	 * @return
	 * @throws UploadException
	 * @throws IOException
	 */
	@RequestMapping(value = "/pic", method=RequestMethod.POST)
	@ResponseBody
	public MultiPicUploadModel doUploadPic(HttpServletRequest request, UploadPicParam uploadParam,
			@RequestParam(value = "files", required = false) MultipartFile[] files) throws UploadException, IOException {
		return uploadProcess.doUploadPic(files, uploadParam);
	}
}
