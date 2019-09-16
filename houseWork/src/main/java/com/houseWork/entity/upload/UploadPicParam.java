package com.houseWork.entity.upload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zzc
 */
@Data
@ApiModel("文件上传参数")
public class UploadPicParam {
	@ApiModelProperty("归类")
	private String type;
	
}
