package com.ningbo.duyun.service;


import com.ningbo.duyun.bean.FaildItem;
import com.ningbo.duyun.bean.ImportInfo;
import com.ningbo.duyun.dao.ImportInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportInfoService {
    @Autowired
    ImportInfoMapper importInfoMapper;

   public  int insertImportInfo(ImportInfo importInfo)
    {
        return importInfoMapper.insertImportInfo(importInfo);
    }

    public List<FaildItem> getFaildItemList()
    {
        return importInfoMapper.getFaildItemList();
    }

}
