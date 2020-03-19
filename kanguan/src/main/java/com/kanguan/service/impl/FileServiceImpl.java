package com.kanguan.service.impl;

import com.kanguan.common.Const;
import com.kanguan.service.FileService;
import com.kanguan.util.FtpUtil;
import com.kanguan.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author ZSS
 * @date 2020/3/18 23:08
 * @description 文件操作服务层接口方法的实现
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadFile(MultipartFile file, String path) {
        // 获取原始文件的文件名
        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            return null;
        }
        // 获取扩展名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 组装上传的文件名
        String uploadFileName = IdUtil.getId() + "." + fileExtensionName;
        log.info("开始上传文件,上传文件的源文件名是:{},上传的路径是:{},上传后的文件名:{}", fileName, path, uploadFileName);

        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }

        File targetFile = new File(path, uploadFileName);
        try {
            file.transferTo(targetFile);
            FileInputStream fileInputStream = new FileInputStream(targetFile);
            FtpUtil.uploadFile(Const.path.COVER_PATH, uploadFileName, fileInputStream);
            targetFile.delete();
        } catch (IOException e) {
            log.error("上传文件失败，发生未知异常 ", e);
        }
        return targetFile.getName();
    }
}
