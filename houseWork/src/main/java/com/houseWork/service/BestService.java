package com.houseWork.service;

import java.util.List;
import java.util.Map;

public interface BestService<T> {

    // 添加
    T insert(T o);


    // 更新
    T update(T o);

    T getById(int key);

    T getByKey(String key);

    // 根据数字id删除
    void deleteById(int key);

    // 查询列表
    List<T> findByMap(Map<String, Object> params);
}
