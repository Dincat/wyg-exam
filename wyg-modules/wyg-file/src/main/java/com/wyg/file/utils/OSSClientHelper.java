package com.wyg.file.utils;


import com.wyg.common.core.exception.GlobalException;
import com.wyg.common.core.utils.DateUtils;
import com.wyg.common.core.utils.SpringContextHolder;
import com.wyg.common.core.utils.StringUtils;
import com.wyg.common.core.utils.file.FileUtils;
import com.wyg.common.core.utils.file.ImageUtils;
import com.wyg.common.core.utils.file.MimeTypeUtils;
import com.wyg.common.core.utils.uuid.IdUtils;
import com.wyg.file.api.domain.SysFile;
import com.wyg.file.api.domain.SysMaterial;
import com.wyg.file.client.IOSSClient;
import com.wyg.file.config.*;
import com.wyg.file.exception.FileNameLengthLimitExceededException;
import com.wyg.file.exception.InvalidExtensionException;
import com.wyg.file.service.ISysMaterialService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 文件上传操作助手
 */
@Service
@Slf4j
public class OSSClientHelper {

    private IOSSClient ossClient;

    private OssConfig ossConfig;

    @Autowired
    private ISysMaterialService materialService;

    // 默认的文件名最大长度
    public static final int DEFAULT_FILE_NAME_LENGTH = 200;

    public static final String[] IMAGE_EXTENSION = {"bmp", "gif", "jpg", "jpeg", "png"};

    public static final String[] FLASH_EXTENSION = {"swf", "flv"};

    public static final String[] MEDIA_EXTENSION = {"swf", "flv", "mp3", "wav", "wma", "wmv", "mid", "avi", "mpg",
            "asf", "rm", "rmvb"};

    //文件大小限制
    // 默认大小 50M
    public static final long DEFAULT_MAX_SIZE = 52428800;
    private long maxSize = DEFAULT_MAX_SIZE;
    private boolean needDatePath = false;
    //允许扩展名
    public static final String[] DEFAULT_ALLOWED_EXTENSION = {
            // 图片
            "bmp", "gif", "jpg", "jpeg", "png",
            // word excel powerpoint
            "doc", "docx", "xls", "xlsx", "ppt", "pptx", "html", "htm", "txt",
            // 压缩文件
            "rar", "zip", "gz", "bz2",
            // pdf
            "pdf"};
    public String[] allowedExtension = DEFAULT_ALLOWED_EXTENSION;


    public void init(OssConfig ossConfig, IOSSClient ossClient) {
        this.ossConfig = ossConfig;
        maxSize = ossConfig.getMaxSize();
        needDatePath = ossConfig.isNeedDatePath();
        String extension = ossConfig.getAllowedExtension();
        allowedExtension = extension.split(",");
        ISysMaterialService service = SpringContextHolder.getApplicationContext().getBean(ISysMaterialService.class);
        this.materialService=service;
        this.ossClient = ossClient;
    }

    /**
     * 以默认配置进行文件上传
     *
     * @param request 当前请求
     * @param file    上传的文件
     *                添加出错信息
     * @return
     * @throws IOException
     * @throws FileNameLengthLimitExceededException
     * @throws InvalidExtensionException
     * @throws FileSizeLimitExceededException
     */
    public SysMaterial upload(HttpServletRequest request, MultipartFile file, Long folderId)
            throws FileSizeLimitExceededException, InvalidExtensionException, FileNameLengthLimitExceededException,
            IOException {
        return upload(request, file, ossConfig.getBaseDir(), folderId, allowedExtension);
    }

    /**
     * 以默认配置进行文件上传
     *
     * @param request 当前请求
     * @param file    上传的文件
     *                添加出错信息
     * @return
     * @throws IOException
     * @throws FileNameLengthLimitExceededException
     * @throws InvalidExtensionException
     * @throws FileSizeLimitExceededException
     */
    public SysMaterial upload(HttpServletRequest request, MultipartFile file, String baseDir, Long folderId)
            throws FileSizeLimitExceededException, InvalidExtensionException, FileNameLengthLimitExceededException,
            IOException {
        return upload(request, file, baseDir, folderId, allowedExtension);
    }

    /**
     * 以默认配置进行文件上传
     *
     * @param request          当前请求
     * @param file             上传的文件
     *                         添加出错信息
     * @param allowedExtension 允许上传的文件类型
     * @return
     * @throws IOException
     * @throws FileNameLengthLimitExceededException
     * @throws InvalidExtensionException
     * @throws FileSizeLimitExceededException
     */
    public SysMaterial upload(HttpServletRequest request, MultipartFile file, String baseDir, Long folderId, String[] allowedExtension)
            throws FileSizeLimitExceededException, InvalidExtensionException, FileNameLengthLimitExceededException,
            IOException {
        return upload(request, file, baseDir, folderId, allowedExtension, maxSize, needDatePath);
    }

    /**
     * 文件上传
     *
     * @param request          当前请求 从请求中提取 应用上下文根
     * @param baseDir          相对应用的基目录
     * @param file             上传的文件
     * @param allowedExtension 允许的文件类型 null 表示允许所有
     * @param maxSize          最大上传的大小 -1 表示不限制
     * @param needDatePath     是否需要日期目录和随机文件名前缀
     * @return 返回上传成功的文件名
     * @throws InvalidExtensionException            如果MIME类型不允许
     * @throws FileSizeLimitExceededException       如果超出最大大小
     * @throws FileNameLengthLimitExceededException 文件名太长
     * @throws IOException                          比如读写文件出错时
     */
    public SysMaterial upload(HttpServletRequest request, MultipartFile file, String baseDir, Long folderId,
                              String[] allowedExtension, long maxSize, boolean needDatePath)
            throws InvalidExtensionException, FileSizeLimitExceededException, IOException,
            FileNameLengthLimitExceededException {
        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > OSSClientHelper.DEFAULT_FILE_NAME_LENGTH) {
            throw new FileNameLengthLimitExceededException(file.getOriginalFilename(), fileNamelength,
                    OSSClientHelper.DEFAULT_FILE_NAME_LENGTH);
        }

        baseDir = StringUtils.trimDiagonal(ossConfig.getBaseDir()) + "/" + StringUtils.trimDiagonal(baseDir);

        String filename = FileUploadUtils.extractFilename(file, baseDir);
        assertAllowed(file, filename, allowedExtension, maxSize);
        String url = ossClient.upload(file, StringUtils.trimDiagonal(filename));

        //region 保存到素材表
        SysMaterial material = new SysMaterial();
        material.setFolderId(folderId);

        String originalFilename = file.getOriginalFilename();
        if (StringUtils.isEmpty(originalFilename) || originalFilename.equals("blob")) originalFilename = FileUtils.getName(filename);
        material.setMaterialOriginalName(originalFilename);
        material.setMaterialOriginalUrl(filename);

        BigDecimal bigDecimal = new BigDecimal(file.getSize());
        BigDecimal MB = new BigDecimal("1000000");
        BigDecimal size = bigDecimal.divide(MB, 4, BigDecimal.ROUND_UP);
        material.setMaterialSize(size);

        //初始状态默认nick=name
        material.setMaterialNick(originalFilename);
        material.setMaterialName(FileUtils.getName(url));
        material.setMaterialUrl(url);


        materialService.insertMaterial(material);
        //endregion

        //生成缩略图
        uploadThumb(file,material,filename);

        return material;
    }

    /**
     * 生成图片缩略图
     * @param file
     * @param material
     * @param fileName
     */
    private void uploadThumb(MultipartFile file, SysMaterial material, String fileName) {

        if (!FileUtils.isImage(file)) return;

        InputStream resizeInputStream = null;

        try {
            int resize = 32;
            resizeInputStream = ImageUtils.resizeImage(file, resize);
            ossClient.upload(resizeInputStream, fileName + "." + resize + ".jpg");

            resize = 64;
            resizeInputStream = ImageUtils.resizeImage(file, resize);
            ossClient.upload(resizeInputStream, fileName + "." + resize + ".jpg");

            resize = 128;
            resizeInputStream = ImageUtils.resizeImage(file, resize);
            ossClient.upload(resizeInputStream, fileName + "." + resize + ".jpg");

            resize = 256;
            resizeInputStream = ImageUtils.resizeImage(file, resize);
            ossClient.upload(resizeInputStream, fileName + "." + resize + ".jpg");
        }catch (Exception ex){
            log.error(ex.getMessage(), ex);
            throw new GlobalException("生成上传文件缩略图失败："+ex.getMessage());
        }

    }


    /**
     * 是否允许文件上传
     *
     * @param remoteUrl        上传的文件
     * @param allowedExtension 文件类型 null 表示允许所有
     * @param maxSize          最大大小 字节为单位 -1表示不限制
     * @return
     * @throws InvalidExtensionException      如果MIME类型不允许
     * @throws FileSizeLimitExceededException 如果超出最大大小
     */
    public void assertAllowed(String remoteUrl, String[] allowedExtension, long maxSize)
            throws InvalidExtensionException, FileSizeLimitExceededException {

        String extension = FilenameUtils.getExtension(remoteUrl);
        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
            if (allowedExtension == IMAGE_EXTENSION) {
                throw new InvalidExtensionException.InvalidImageExtensionException(allowedExtension, extension,
                        remoteUrl);
            } else if (allowedExtension == FLASH_EXTENSION) {
                throw new InvalidExtensionException.InvalidFlashExtensionException(allowedExtension, extension,
                        remoteUrl);
            } else if (allowedExtension == MEDIA_EXTENSION) {
                throw new InvalidExtensionException.InvalidMediaExtensionException(allowedExtension, extension,
                        remoteUrl);
            } else {
                throw new InvalidExtensionException(allowedExtension, extension, remoteUrl);
            }
        }
    }


    /**
     * 是否允许文件上传
     *
     * @param file             上传的文件
     * @param allowedExtension 文件类型 null 表示允许所有
     * @param maxSize          最大大小 字节为单位 -1表示不限制
     * @return
     * @throws InvalidExtensionException      如果MIME类型不允许
     * @throws FileSizeLimitExceededException 如果超出最大大小
     */
    public void assertAllowed(MultipartFile file, String filename, String[] allowedExtension, long maxSize)
            throws InvalidExtensionException, FileSizeLimitExceededException {

        String extension = FilenameUtils.getExtension(filename);

        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
            if (allowedExtension == IMAGE_EXTENSION) {
                throw new InvalidExtensionException.InvalidImageExtensionException(allowedExtension, extension,
                        filename);
            } else if (allowedExtension == FLASH_EXTENSION) {
                throw new InvalidExtensionException.InvalidFlashExtensionException(allowedExtension, extension,
                        filename);
            } else if (allowedExtension == MEDIA_EXTENSION) {
                throw new InvalidExtensionException.InvalidMediaExtensionException(allowedExtension, extension,
                        filename);
            } else {
                throw new InvalidExtensionException(allowedExtension, extension, filename);
            }
        }

        long size = file.getSize();
        if (maxSize != -1 && size > maxSize) {
            throw new FileSizeLimitExceededException("not allowed upload:file size over limit", size, maxSize);
        }
    }

    /**
     * 判断MIME类型是否是允许的MIME类型
     *
     * @param extension
     * @param allowedExtension
     * @return
     */
    public boolean isAllowedExtension(String extension, String[] allowedExtension) {
        for (String str : allowedExtension) {
            if (str.trim().equalsIgnoreCase(extension.trim())) {
                return true;
            }
        }
        return false;
    }


    public void delete(HttpServletRequest request, String filename) throws IOException {
        if (StringUtils.isEmpty(filename)) {
            return;
        }
        ossClient.delete(filename);
    }
}
