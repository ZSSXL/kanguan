package com.kanguan.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author ZSS
 * @date 2020/3/18 23:07
 * @description 文件操作服务层接口
 */
public interface FileService {

    /**
     * 上传文件
     *
     * @param file    文件
     * @param dirPath 文件夹路径
     * @param path    路径
     * @return String
     */
    String uploadFile(MultipartFile file, String dirPath, String path);
}
