package com.houseWork.service.test;

import com.houseWork.dao.test.TestDao;
import com.houseWork.entity.Test.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("TestService")
public class TestServiceImpl implements TestService{

    @Autowired
    private TestDao testDao;

    @Override
    public Test insert(Test test) {
        testDao.insert(test);
        return test;
    }

    @Override
    public Test update(Test test) {
        testDao.updateByPrimaryKey(test);
        return test;
    }

    @Override
    public Test getById(int key) {
        return testDao.selectByPrimaryKey(key);
    }

    @Override
    public Test getByKey(String key) {
        return null;
    }

    @Override
    public void deleteById(int id) {
        testDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<Test> findByMap(Map<String, Object> params) {
        return null;
    }
}
