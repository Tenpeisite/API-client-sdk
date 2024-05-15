package com.zhj.apiclientsdk.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhj
 * @version 1.0
 * @description
 * @date 2024/4/22 14:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequest {

    private Long assistantId;

    private String question;

    public QuestionRequest(String question) {
        this.question = question;
    }
}
