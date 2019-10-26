package com.houseWork.service.user;

import com.houseWork.entity.user.MessagePutParam;
import com.houseWork.entity.user.UserFormParam;
import org.springframework.http.ResponseEntity;

public interface UserFormService {
     ResponseEntity messageput(MessagePutParam param);
     void insertFormId(UserFormParam param);
}
