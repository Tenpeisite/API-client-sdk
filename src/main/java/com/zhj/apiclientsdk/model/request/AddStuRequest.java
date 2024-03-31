package com.zhj.apiclientsdk.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhj
 * @version 1.0
 * @description
 * @date 2024/3/10 15:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddStuRequest {
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
     * 专业id
     */
    private Long majorId;

    /**
     * 学院id
     */
    private Long collegeId;
}
