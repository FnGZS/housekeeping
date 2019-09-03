package com.houseWork.service.dict;

import com.houseWork.dao.dict.DictDao;
import com.houseWork.entity.dict.DictEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DictServiceImpl implements DictService{

    @Autowired
    private DictDao dictDao;

    @Override
    public DictEntity insert(DictEntity o) {
        dictDao.insert(o);
        return o;
    }

    @Override
    public DictEntity update(DictEntity o) {
        dictDao.updateByPrimaryKey(o);
        return o;
    }

    @Override
    public DictEntity getById(int key) {
        return dictDao.selectByPrimaryKey(key);
    }

    @Override
    public DictEntity getByKey(String key) {
        return null;
    }

    @Override
    public void deleteById(int key) {
        dictDao.deleteByPrimaryKey(key);
    }

    @Override
    public List<DictEntity> findByMap(Map<String, Object> params) {
        return dictDao.selectByMap(params);
    }
}
