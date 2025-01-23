package com.ltk.Benkyoukai.service;

import com.ltk.Benkyoukai.mapper.UploadFileMapper;
import org.springframework.stereotype.Service;

@Service
public class UploadService {

    private final UploadFileMapper uploadFileMapper;

    public UploadService(UploadFileMapper w) {
        this.uploadFileMapper = w;
    }

    public int upload(String files) {
        return uploadFileMapper.UploadFile(files);
    }
}
