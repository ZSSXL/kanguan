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
     * @param file         文件
     * @param dirPath      文件夹路径
     * @param path         路径
     * @param originalName 是否原名 true为原名，false重命名
     * @return String
     */
    String uploadFile(MultipartFile file, String dirPath, String path, Boolean originalName);
}
