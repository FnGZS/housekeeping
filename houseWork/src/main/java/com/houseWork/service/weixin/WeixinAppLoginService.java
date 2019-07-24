package com.houseWork.service.weixin;

import com.houseWork.entity.UserInfo;
import com.houseWork.entity.response.ResultCode;
import com.houseWork.service.weixin.domin.WeixinLoginResult;
import com.houseWork.service.weixin.weixinApp.WeixinAppCoding;
import com.houseWork.service.weixin.weixinApp.WeixinAppURL;
import com.houseWork.utils.AesUtils;
import com.houseWork.utils.JsonUtils;
import com.jpay.util.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Map;

@Component("weixinAppLoginService")
public class WeixinAppLoginService {

    private static CloseableHttpClient httpClient = null;

    private static CloseableHttpClient getHttpClient() {
        if (httpClient == null) {
            httpClient = HttpClientBuilder.create().build();
        }
        return httpClient;
    }
    /**
     * 微信登陆
     *
     * @param platCode
     * @param platUserInfoMap
     * @return
     */
    public static WeixinLoginResult<UserInfo> getUserInfo(String platCode, Map<String, String> platUserInfoMap) {
        WeixinLoginResult<UserInfo> result = new WeixinLoginResult<>();
        String url = String.format(WeixinAppURL.USER_INFO_URL, WeixinAppCoding.APP_ID, WeixinAppCoding.SECRET, platCode);
        URI uri = URI.create(url);
        HttpGet get = new HttpGet(uri);
        HttpResponse response;
        try {
            response = getHttpClient().execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();

                BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
                StringBuilder sb = new StringBuilder();

                for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
                    sb.append(temp);
                }

                Map<String, Object> resultMap = JsonUtils.getMap4Json(sb.toString().trim());
                String sessionkey = (String) resultMap.get("session_key");
                if (StringUtils.isBlank(sessionkey)) {
                    result.setCode(ResultCode.FAIL);
                    result.setMessage("微信小程序登录异常，code换取session_key失败");
                    return result;
                }
                // STEP2 获取解密用户关键信息
                String encryptedData = platUserInfoMap.get("encryptedData");
                String iv = platUserInfoMap.get("iv");
                String decryptedData = AesUtils.decrypt(encryptedData, sessionkey, iv);
                Map<String, Object> decryptedDataMap = JsonUtils.getMap4Json(decryptedData.trim());
                UserInfo userInfo = new UserInfo();
                userInfo.setOpenId((String) decryptedDataMap.get("openId"));
                userInfo.setHeadimgurl((String) decryptedDataMap.get("avatarUrl"));
                userInfo.setNickName((String) decryptedDataMap.get("nickName"));
                userInfo.setCountry((String) decryptedDataMap.get("country"));
                userInfo.setProvince((String) decryptedDataMap.get("province"));
                userInfo.setCity((String) decryptedDataMap.get("city"));
                userInfo.setSex((Integer) decryptedDataMap.get("gender"));
                userInfo.setUnionId((String) decryptedDataMap.get("unionId"));
                userInfo.setSessionKey(sessionkey);
                if (StringUtils.isBlank(userInfo.getNickName()) || StringUtils.isBlank(userInfo.getOpenId())) {
                    result.setCode(ResultCode.FAIL);
                    result.setMessage("微信小程序登录异常，返回信息不全");
                    return result;
                }
                result.setDataResult(userInfo);
            }
        } catch (ClientProtocolException e) {
            result.setMessage("微信小程序登录异常");
            result.setCode(ResultCode.FAIL);
        } catch (IOException e) {
            result.setMessage("微信小程序登录异常");
            result.setCode(ResultCode.FAIL);
        } catch (Exception e) {
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
