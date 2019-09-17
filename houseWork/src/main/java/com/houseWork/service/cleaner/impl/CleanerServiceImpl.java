package com.houseWork.service.cleaner.impl;

import com.houseWork.dao.cleaner.CleanerDao;
import com.houseWork.entity.cleaner.Cleaner;
import com.houseWork.entity.cleaner.CleanerWorkDetail;
import com.houseWork.service.cleaner.CleanerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cleanerService")
public class CleanerServiceImpl implements CleanerService {

    @Autowired
    private CleanerDao cleanerDao;

    @Override
    public void addCleaner(Cleaner cleaner) {
        cleanerDao.addCleaner(cleaner);
    }

    @Override
    public void deleteCleaners(List<Cleaner> list) {
        cleanerDao.deleteCleaners(list);
    }

    @Override
    public void updateCleaners(List<Cleaner> list) {
        cleanerDao.updateCleaners(list);
    }

    @Override
    public List<Cleaner> findCleaners(String namelike, String sortName, String sortOrder) {
        return cleanerDao.findCleaners(namelike, sortName, sortOrder);
    }

    @Override
    public Cleaner loadCleanerByName(String name) {
        return cleanerDao.loadCleanerByName(name);
    }

    @Override
    public List<CleanerWorkDetail> cleanerWork(Integer cleanerId) {
        return cleanerDao.cleanerWork(cleanerId);
    }
}
