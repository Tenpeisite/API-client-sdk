package com.zhj.apiclientsdk.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhj
 * @version 1.0
 * @description
 * @date 2024/3/10 16:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StuInfoResponse {

    /**
     * 学号
     */
    private Long id;

    /**
     * 学生名
     */
    private String studentName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 专业名
     */
    private String majorName;

    /**
     * 学院名
     */
    private String collegeName;
}
