package com.example.assignment.support;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.example.assignment.exception.CustomizeErrorCode;
import com.example.assignment.exception.CustomizeException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

@Service
public class AliyunSupport {
    @Value("${aliyun.ufile.access-key-id}")
    private String accessKeyId;
    @Value("${aliyun.ufile.access-key-secret}")
    private String accessKeySecret;
    @Value("${aliyun.ufile.endpoint}")
    private String endpoint;
    @Value("${aliyun.ufile.bucket-name}")
    private String bucketName;

    public String upload(InputStream input, String fileName) {
        String generatedFileName;
        String[] fileSplitter = fileName.split("\\.");
        if (fileSplitter.length > 1) {
            generatedFileName = UUID.randomUUID().toString() + "." + fileSplitter[fileSplitter.length - 1];
        } else {
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_ERROR);
        }

        try {
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            ossClient.putObject(bucketName, generatedFileName, input);
            Date expiration = new Date(new Date().getTime() + 3600 * 1000 * 24 * 365 * 10);
            URL url = ossClient.generatePresignedUrl(bucketName, generatedFileName, expiration);
            ossClient.shutdown();
            return url.toString();
        } catch (OSSException e) {
            e.printStackTrace();
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_ERROR);
        } catch (ClientException e) {
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_ERROR);
        }
    }
}
