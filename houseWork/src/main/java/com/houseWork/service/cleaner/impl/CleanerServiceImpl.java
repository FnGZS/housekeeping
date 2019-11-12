package com.houseWork.service.cleaner.impl;

import com.houseWork.dao.cleaner.CleanerDao;
import com.houseWork.entity.cleaner.Cleaner;
import com.houseWork.entity.cleaner.CleanerWorkDetail;
import com.houseWork.service.cleaner.CleanerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("cleanerService")
public class CleanerServiceImpl implements CleanerService {

    @Autowired
    private CleanerDao cleanerDao;

    @Override
    public void addCleaner(Cleaner cleaner) {
        cleanerDao.addCleaner(cleaner);
    }

    @Override
    public void deleteCleaner(Integer id) {
        cleanerDao.deleteCleaner(id);
    }

    @Override
    public void updateCleaners(List<Cleaner> list) {
        cleanerDao.updateCleaners(list);
    }

    @Override
    public List<Cleaner> findCleaners(String name, String place, Integer price, Integer total, String type) {
        return cleanerDao.findCleaners(name, place, price, total, type);
    }

    @Override
    public Cleaner loadCleanerByName(String name) {
        return cleanerDao.loadCleanerByName(name);
    }

    @Override
    public Cleaner loadCleanerById(Integer id) {
        return cleanerDao.loadCleanerById(id);
    }

    @Override
    public List<CleanerWorkDetail> cleanerWork(Integer cleanerId) {
        return cleanerDao.cleanerWork(cleanerId);
    }

    @Override
    public void updateCleanerWorkDetail(Map map) {
        cleanerDao.updateCleanerWorkDetail(map);
    }

    @Override
    public void deleteCleanerWorkDetail(Map map) {
        cleanerDao.deleteCleanerWorkDetail(map);
    }

    @Override
    public CleanerWorkDetail subscribe(CleanerWorkDetail cleanerWorkDetail) {
        return cleanerDao.subscribe(cleanerWorkDetail);
    }

    @Override
    public void addUserCleaner(Integer cid, Integer uid) {
        cleanerDao.addUserCleaner(cid, uid);
    }
}
