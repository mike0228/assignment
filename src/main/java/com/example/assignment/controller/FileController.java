package com.example.assignment.controller;

import com.example.assignment.dto.FileDTO;
import com.example.assignment.exception.CustomizeErrorCode;
import com.example.assignment.exception.CustomizeException;
import com.example.assignment.support.AliyunSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class FileController {

    @Autowired
    private AliyunSupport aliyunSupport;

    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDTO upload(HttpServletRequest request,
                          @RequestParam(name = "editormd-image-file") MultipartFile file) throws IOException {

        try {
            String fileName = aliyunSupport.upload(file.getInputStream(), file.getOriginalFilename());
            FileDTO fileDTO = new FileDTO();
            fileDTO.setUrl(fileName);
            fileDTO.setSuccess(1);
            return fileDTO;
        } catch (MaxUploadSizeExceededException e) {
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_ERROR);
        }
    }
}
