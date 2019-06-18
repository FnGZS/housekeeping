package com.housekeeping.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

public class JsonUtils {
	
    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * 对象转换成json格式
     * 
     * @param obj
     * @return
     */
    public static String toJSON(Object obj) {
        if (obj == null) {
            return "";
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, Boolean.FALSE);
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("对象转换json出错", e);
        }
        return "";
    }

    /**
     * json格式转换成对象
     * 
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T fromJSON(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            return null;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error("json转换对象出错,json={}", json, e);
        }
        return null;
    }

    /**
     * json字符串转换成标准格式
     * 
     * @param json
     * @return
     */
    public static String toJSONFormatter(Object obj) {
        if (obj == null) {
            return "";
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, Boolean.TRUE);
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("对象转换json出错", e);
        }
        return "";
    }

    /**
     * json格式转换成Map
     * 
     * @param jsonString
     * @return Map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getMap4Json(String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> maps = mapper.readValue(jsonString, Map.class);
            Iterator<String> iter = maps.keySet().iterator();
            Map<String, Object> valueMap = new HashMap<String, Object>();
            while (iter.hasNext()) {
                String key = (String) iter.next();
                Object value = maps.get(key);
                valueMap.put(key, value);
            }
            return valueMap;
        } catch (Exception e) {
            log.error("json转换Map对象出错,json={}", jsonString, e);
        }
        return null;
    }
    
    /**
     * 读取名片识别的结果
     * 
     */
    public static Map<String, String> getCard(String jsonString) {
    	
    	Map<String, String> result = new HashMap<>();
        Object obj=JSONValue.parse(jsonString);
        try {
        	JSONObject jsonObject = (JSONObject) obj;
        	if(jsonObject.get("organization") != null) {
        		JSONArray array =  (JSONArray) jsonObject.get("organization");
        		String organization = "";
        		for(int i=0; i<array.size(); i++) {
        			JSONObject jo = (JSONObject) array.get(i);
        			if(jo != null) {
        				JSONObject item = (JSONObject) jo.get("item");
        				if(item != null) {
        					if(item.get("name") != null) {
        						organization = organization + item.get("name").toString() + " ";
        					} else if(item.get("unit") != null) {
        						organization = organization + item.get("unit").toString() + " ";
        					}
        				}
        			}
        		}
        		result.put("position", organization);
        	}
        	if(jsonObject.get("telephone") != null) {
        		JSONArray array =  (JSONArray) jsonObject.get("telephone");
        		String telephone = "";
        		for(int i=0; i<array.size(); i++) {
        			JSONObject jo = (JSONObject) array.get(i);
        			if(jo != null) {
        				JSONObject item = (JSONObject) jo.get("item");
        				if(item != null) {
        					if(item.get("number") != null) {
        						String mobile = item.get("number").toString();
        						if(i == 0) {
        							result.put("mobile", mobile);
        						}
        						telephone = telephone + mobile + " ";
        					}
        				}
        			}
        		}
        		result.put("telephone", telephone);
        	}
        	if(jsonObject.get("email") != null) {
        		JSONArray array =  (JSONArray) jsonObject.get("email");
        		String email = "";
        		for(int i=0; i<array.size(); i++) {
        			JSONObject jo = (JSONObject) array.get(i);
        			if(jo != null) {
        				if(jo.get("item") != null) {
        					email = email + jo.get("item").toString() + " ";
        				}
        			}
        		}
        		result.put("email", email);
        	}
        	String position = "";
        	if(jsonObject.get("title") != null) {
        		JSONArray array =  (JSONArray) jsonObject.get("title");
        		for(int i=0; i<array.size(); i++) {
        			JSONObject jo = (JSONObject) array.get(i);
        			if(jo != null) {
        				if(jo.get("item") != null) {
        					position = position + jo.get("item").toString() + " ";
        				}
        			}
        		}
        		result.put("position", position);
        	} else if(jsonObject.get("role") != null) {
        		JSONArray array =  (JSONArray) jsonObject.get("role");
        		for(int i=0; i<array.size(); i++) {
        			JSONObject jo = (JSONObject) array.get(i);
        			if(jo != null) {
        				if(jo.get("item") != null) {
        					position = position + jo.get("item").toString() + " ";
        				}
        			}
        		}
        		result.put("position", position);
        	}
        	
        	String name = "";
        	if(jsonObject.get("formatted_name") != null) {
        		JSONArray array =  (JSONArray) jsonObject.get("formatted_name");
        		for(int i=0; i<array.size(); i++) {
        			JSONObject jo = (JSONObject) array.get(i);
        			if(jo != null) {
        				if(jo.get("item") != null) {
        					name = name + jo.get("item").toString() + " ";
        				}
        			}
        		}
        		result.put("name", name);
        	} else if(jsonObject.get("name") != null) {
        		JSONArray array =  (JSONArray) jsonObject.get("name");
        		for(int i=0; i<array.size(); i++) {
        			JSONObject jo = (JSONObject) array.get(i);
        			if(jo != null) {
        				JSONObject item = (JSONObject) jo.get("item");
        				if(item != null) {
        					if(item.get("family_name") != null) {
        						name = name + item.get("family_name").toString() + " ";
        					}
        					if(item.get("given_name") != null) {
        						name = name + item.get("given_name").toString() + " ";
        					}
        				}
        			}
        		}
        		result.put("name", name);
        	}
        	
        	if(jsonObject.get("address") != null) {
        		String address = "";
        		JSONArray array =  (JSONArray) jsonObject.get("address");
        		for(int i=0; i<array.size(); i++) {
        			JSONObject jo = (JSONObject) array.get(i);
        			if(jo != null) {
        				JSONObject item = (JSONObject) jo.get("item");
        				if(item != null) {
        					if(item.get("country") != null) {
        						address = address + item.get("country").toString() + " ";
        					}
        					if(item.get("region") != null) {
        						address = address + item.get("region").toString() + " ";
        					}
        					if(item.get("locality") != null) {
        						address = address + item.get("locality").toString() + " ";
        					}
        					if(item.get("street") != null) {
        						address = address + item.get("street").toString() + " ";
        					}
        				}
        			}
        		}
        		result.put("address", address);
        	}
		} catch (ClassCastException e) {
			return result;
		}
        return result;
	}

}