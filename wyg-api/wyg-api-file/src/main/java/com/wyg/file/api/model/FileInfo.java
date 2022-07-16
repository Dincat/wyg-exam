package com.wyg.file.api.model;

import com.wyg.common.core.enums.FileTypes;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FileInfo {

    private String fileName;
    private String originalName;
    private BigDecimal fileSize;
    private String url;
    private FileTypes fileType;


}
