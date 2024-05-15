package com.zhj.apiclientsdk.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author zhj
 * @version 1.0
 * @description
 * @date 2024/5/8 19:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageData implements Serializable {
    private String imageUrl;
}
