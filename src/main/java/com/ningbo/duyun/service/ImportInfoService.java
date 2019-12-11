package com.ningbo.duyun.service;


import com.ningbo.duyun.bean.ImportInfo;
import com.ningbo.duyun.dao.ImportInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImportInfoService {
    @Autowired
    ImportInfoMapper importInfoMapper;

   public   int insertImportInfo(ImportInfo importInfo)
    {
        return importInfoMapper.insertImportInfo(importInfo);
    }

}
