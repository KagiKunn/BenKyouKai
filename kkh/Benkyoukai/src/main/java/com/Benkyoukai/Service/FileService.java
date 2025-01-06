package com.Benkyoukai.Service;

import com.Benkyoukai.classOne.Mapper.FileMapper;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public int uploadImg(String file) {
        return fileMapper.insertImage(file);
    }
}
