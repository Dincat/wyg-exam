package com.wyg.file.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;

import com.wyg.common.core.utils.file.FileUtils;
import com.wyg.common.core.utils.uuid.IdUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.multipart.MultipartFile;
import com.wyg.common.core.exception.file.FileNameLengthLimitExceededException;
import com.wyg.common.core.exception.file.FileSizeLimitExceededException;
import com.wyg.common.core.exception.file.InvalidExtensionException;
import com.wyg.common.core.utils.DateUtils;
import com.wyg.common.core.utils.StringUtils;
import com.wyg.common.core.utils.file.FileTypeUtils;
import com.wyg.common.core.utils.file.MimeTypeUtils;
import com.wyg.common.core.utils.uuid.Seq;

/**
 * 文件上传工具类
 * 
 * @author wyg
 */
public class FileUploadUtils
{
    /**
     * 编码文件名
     */
    public static final String extractFilename(MultipartFile file,String baseDir)
    {
        String fileName = file.getOriginalFilename();
        String extension = getExtension(file);
        fileName =baseDir+"/"+ DateUtils.datePath() + "/" + IdUtils.fastUUID() + "." + extension;
        return fileName;
    }

    /**
     * 获取文件名的后缀
     *
     * @param file 表单文件
     * @return 后缀名
     */
    public static final String getExtension(MultipartFile file)
    {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StringUtils.isEmpty(extension))
        {
            extension = MimeTypeUtils.getExtension(file.getContentType());
        }
        return extension;
    }

    public static String extractFilename(MultipartFile file, String baseDir, boolean needDatePathAndRandomName)
            throws UnsupportedEncodingException {

        String filename = file.getOriginalFilename();
        //文件名必须重新命名，以时间精确到毫秒命名
        filename = DateUtils.dateTimeNow() + "." + FileUtils.getExtensionName(filename);
        if (needDatePathAndRandomName) {
            filename = datePath() + "/" + filename;
        }
        if (!StringUtils.isEmpty(baseDir)) {
            filename = baseDir + "/" + filename;
        }
        return filename;
    }


    public static  String extractFilename(String remoteUrl, String baseDir, boolean needDatePathAndRandomName)
            throws UnsupportedEncodingException {
        //字符串处理

        String filename = remoteUrl;
        int slashIndex = filename.indexOf("/");
        if (slashIndex >= 0) {
            filename = filename.substring(slashIndex + 1);
        }
        if (needDatePathAndRandomName) {
            filename = datePath() + "/" + System.currentTimeMillis() + "."
                    + FileUtils.getExtensionName(filename);
        }
        if (!StringUtils.isEmpty(baseDir)) {
            filename = baseDir + "/" + filename;
        }
        return filename;
    }

    /**
     * 日期路径 即年/月/日 如2013/01/03
     *
     * @return
     */
    private static String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }
}