package com.zhj.apiclientsdk.client;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.zhj.apiclientsdk.exception.BusinessException;
import com.zhj.apiclientsdk.exception.ErrorCode;
import com.zhj.apiclientsdk.model.User;
import com.zhj.apiclientsdk.model.request.AddStuRequest;
import com.zhj.apiclientsdk.model.request.ImageData;
import com.zhj.apiclientsdk.model.request.QuestionRequest;
import com.zhj.apiclientsdk.model.response.StuInfoResponse;
import com.zhj.apiclientsdk.utis.SignUtils;
import com.zhj.apiclientsdk.utis.UrlImageValidator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 朱焕杰
 * @version 1.0
 * @description TODO
 * @date 2023/4/4 16:37
 */
@AllArgsConstructor
@NoArgsConstructor
public class ApiClient {
    private static final String GATEWAY_HOST = "http://localhost:8090";
    //private static final String GATEWAY_HOST = "http://api-gateway.tempeisite.xyz";
    private String accessKey;
    private String secretKey;


    public String getName(Map map) {
        //String result = HttpUtil.get(GATEWAY_HOST + "/api/name/", map);
        HttpResponse response = HttpRequest.get(GATEWAY_HOST + "/api/name/" + JSONUtil.toJsonStr(map))
                .addHeaders(getHeaderMap(null, "getName"))
                .body(JSONUtil.toJsonStr(map))
                .execute();
        return response.body();
    }

    public String getNameByGet(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.get(GATEWAY_HOST + "/api/name/", paramMap);
        return result;
    }

    public String getNameByPost(String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);

        String result = HttpUtil.post(GATEWAY_HOST + "/api/name/", paramMap);
        return result;
    }

    public String getUserNameByPost(Map map) {
        User user = new User();
        user.setUserName(map.get("name").toString());
        String json = JSONUtil.toJsonStr(user);
        HttpResponse response = HttpRequest.post(GATEWAY_HOST + "/api/name/user")
                .body(json)
                //.addHeaders(getHeaderMap(json))
                .addHeaders(getHeaderMap(null, "getUserNameByPost"))
                .execute();
        String result = response.body();
        return result;
    }

    public String getRandomName() {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HttpResponse response = HttpRequest.get(GATEWAY_HOST + "/api/name/random")
                //.addHeaders(getHeaderMap(null))
                .addHeaders(getHeaderMap(null, "getRandomName"))
                .execute();
        String result = response.body();
        return result;
    }

    public String getYiYan() {
        HttpResponse response = HttpRequest.get(GATEWAY_HOST + "/api/yiyan")
                //.addHeaders(getHeaderMap(null))
                .addHeaders(getHeaderMap(null, "getYiYan"))
                .execute();
        String result = response.body();
        return result;
    }

    public String getDYgirl() {
        HttpResponse response = HttpRequest.get(GATEWAY_HOST + "/api/dygirl")
                //.addHeaders(getHeaderMap(null))
                .addHeaders(getHeaderMap(null, "getDYgirl"))
                .execute();
        String result = response.body();
        return result;
    }

    public String getQQImage(String qq) {
        HttpResponse response = HttpRequest.get(GATEWAY_HOST + "/api/qqimage?qq=" + qq)
                //.addHeaders(getHeaderMap(null))
                .addHeaders(getHeaderMap(null, "getQQImage"))
                .execute();
        String result = response.body();
        return result;
    }

    public String getDuJiTang() {
        HttpResponse response = HttpRequest.get(GATEWAY_HOST + "/api/dujitang")
                .addHeaders(getHeaderMap(null, "getDuJiTang"))
                .execute();
        String result = response.body();
        return result;
    }

    public String getBiZi() {
        HttpResponse response = HttpRequest.get(GATEWAY_HOST + "/api/bizi")
                .addHeaders(getHeaderMap(null, "getBiZi"))
                .execute();
        String result = response.body();
        return result;
    }

    public String getHoroscope(String type, String time) {
        HttpResponse response = HttpRequest.get(GATEWAY_HOST + "/api/horoscope?type=" + type + "&time=" + time)
                .addHeaders(getHeaderMap(null, "getHoroscope"))
                .execute();
        String result = response.body();
        return result;
    }

    public String getLoveTalk() {
        HttpResponse response = HttpRequest.get(GATEWAY_HOST + "/api/loveTalk")
                .addHeaders(getHeaderMap(null, "getLoveTalk"))
                .execute();
        String result = response.body();
        return result;
    }

    public String getIpLocation(String ip) {
        HttpResponse response = HttpRequest.get(GATEWAY_HOST + "/api/ipLocation?ip=" + ip)
                .addHeaders(getHeaderMap(null, "getIpLocation"))
                .execute();
        String result = response.body();
        return result;
    }

    public String getIpLocation() {
        HttpResponse response = HttpRequest.get(GATEWAY_HOST + "/api/ipLocation")
                .addHeaders(getHeaderMap(null, "getIpLocation"))
                .execute();
        String result = response.body();
        return result;
    }

    public String getWeather(String city, String ip, String type) {
        HttpResponse response = HttpRequest.get(GATEWAY_HOST + "/api/weather?city=" + city + "&ip=" + ip + "&type=" + type)
                .addHeaders(getHeaderMap(null, "getWeather"))
                .execute();
        String result = response.body();
        return result;
    }

    public String getWeather() {
        HttpResponse response = HttpRequest.get(GATEWAY_HOST + "/api/weather")
                .addHeaders(getHeaderMap(null, "getWeather"))
                .execute();
        String result = response.body();
        return result;
    }

    public String addStuInfo(AddStuRequest addStuRequest) {
        Long id = addStuRequest.getId();
        String studentName = addStuRequest.getStudentName();
        String sex = addStuRequest.getSex();
        Long majorId = addStuRequest.getMajorId();
        Long collegeId = addStuRequest.getCollegeId();
        if (StringUtils.isAnyBlank(id.toString(), studentName, sex, majorId.toString(), collegeId.toString())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数不能为空");
        }
        String json = JSONUtil.toJsonStr(addStuRequest);
        HttpResponse response = HttpRequest.post(GATEWAY_HOST + "/api/stu/")
                .body(json, "application/json;charset=UTF-8")
                .addHeaders(getHeaderMap(json, "addStuInfo"))
                .execute();
        String result = response.body();
        return result;
    }

    public String addStuInfo(Map<String, String> map) {
        String id = map.get("id");
        String studentName = map.get("studentName");
        String sex = map.get("sex");
        String majorId = map.get("majorId");
        String collegeId = map.get("collegeId");
        sex = "男";
        majorId = "1";
        collegeId = "1";
        if (StringUtils.isAnyBlank(id, studentName, sex, majorId, collegeId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数不能为空");
        }
        AddStuRequest addStuRequest = new AddStuRequest();
        addStuRequest.setId(Long.valueOf(id));
        addStuRequest.setStudentName(studentName);
        addStuRequest.setSex(sex);
        addStuRequest.setMajorId(Long.valueOf(majorId));
        addStuRequest.setCollegeId(Long.valueOf(collegeId));

        String json = JSONUtil.toJsonStr(addStuRequest);
        HttpResponse response = HttpRequest.post(GATEWAY_HOST + "/api/stu/")
                .body(json, "application/json;charset=UTF-8")
                .addHeaders(getHeaderMap(json, "addStuInfo"))
                .execute();
        String result = response.body();
        return result;
    }

    public String getStuInfoById(String studentId) {
        HttpResponse response = HttpRequest.get(GATEWAY_HOST + "/api/stu/" + studentId)
                .addHeaders(getHeaderMap(null, "getStuInfoById"))
                .execute();
        String result = response.body();
        if (StringUtils.isBlank(result)) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return result;
    }

    public String doChat(Map<String, String> map) {
        String question = map.get("question");
        String assistantId = map.get("assistantId");

        if (StringUtils.isAnyBlank(question)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "问题不能为空");
        }
        QuestionRequest questionRequest = new QuestionRequest();
        questionRequest.setQuestion(question);
        if (assistantId != null) {
            questionRequest.setAssistantId(Long.valueOf(assistantId));
        }

        String json = JSONUtil.toJsonStr(questionRequest);
        HttpResponse response = HttpRequest.post(GATEWAY_HOST + "/api/ai/dochat")
                .body(json, "application/json;charset=UTF-8")
                .addHeaders(getHeaderMap(json, "doChat"))
                .execute();
        String result = response.body();
        return result;
    }

    public String doChat(QuestionRequest questionRequest) {
        String question = questionRequest.getQuestion();
        if (StringUtils.isAnyBlank(question)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "问题不能为空");
        }

        String json = JSONUtil.toJsonStr(questionRequest);
        HttpResponse response = HttpRequest.post(GATEWAY_HOST + "/api/ai/dochat")
                .body(json, "application/json;charset=UTF-8")
                .addHeaders(getHeaderMap(json, "doChat"))
                .execute();
        String result = response.body();
        return result;
    }

    public String doDraw(Map<String, String> map) {
        String question = map.get("question");
        String assistantId = map.get("assistantId");

        if (StringUtils.isAnyBlank(question)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "问题不能为空");
        }
        QuestionRequest questionRequest = new QuestionRequest();
        questionRequest.setQuestion(question);
        questionRequest.setAssistantId(Long.valueOf(assistantId));
        String json = JSONUtil.toJsonStr(questionRequest);
        HttpResponse response = HttpRequest.post(GATEWAY_HOST + "/api/ai/draw")
                .body(json, "application/json;charset=UTF-8")
                .addHeaders(getHeaderMap(json, "doDraw"))
                .execute();
        String result = response.body();
        return result;
    }

    public String doDraw(QuestionRequest questionRequest) {
        String question = questionRequest.getQuestion();
        if (StringUtils.isAnyBlank(question)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "问题不能为空");
        }
        String json = JSONUtil.toJsonStr(questionRequest);
        HttpResponse response = HttpRequest.post(GATEWAY_HOST + "/api/ai/draw")
                .body(json, "application/json;charset=UTF-8")
                .addHeaders(getHeaderMap(json, "doDraw"))
                .execute();
        String result = response.body();
        return result;
    }

    /**
     * 通用图像识别
     *
     * @param bytes
     * @return
     */
    public String textRecognition(byte[] bytes) {
        HttpResponse response = HttpRequest.post(GATEWAY_HOST + "/api/ai/textRecognition")
                .body(bytes)
                .addHeaders(getHeaderMap(bytes.toString(), "textRecognition"))
                .header("Content-Type", "application/octet-stream")
                .execute();
        String result = response.body();
        return result;
    }

    /**
     * 身份证识别
     *
     * @param bytes
     * @return
     */
    public String idCardRecognition(byte[] bytes) {
        HttpResponse response = HttpRequest.post(GATEWAY_HOST + "/api/ai/ocr/idcard")
                .body(bytes)
                .addHeaders(getHeaderMap(bytes.toString(), "idCardRecognition"))
                .header("Content-Type", "application/octet-stream")
                .execute();
        String result = response.body();
        return result;
    }

    /**
     * 图像识别
     *
     * @param imageUrl
     * @return
     */
    public String imageRecognition(String imageUrl) {
        final boolean flag = UrlImageValidator.isImageUrl(imageUrl);
        if (!flag) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        final ImageData imageData = new ImageData(imageUrl);
        String json = JSONUtil.toJsonStr(imageData);
        HttpResponse response = HttpRequest.post(GATEWAY_HOST + "/api/ai/imageRecognition")
                .body(json, "application/json;charset=UTF-8")
                .addHeaders(getHeaderMap(json, "imageRecognition"))
                .execute();
        String result = response.body();
        return result;
    }


    private Map getHeaderMap(String body, String methodName) {
        Map<String, String> hashmap = new HashMap<>();
        hashmap.put("accessKey", accessKey);
        //密钥不能发送给前端
        //hashmap.put("secretKey", secretKey);
        hashmap.put("nonce", RandomUtil.randomNumbers(4));
        hashmap.put("body", body);
        hashmap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashmap.put("sign", SignUtils.getSign(body, secretKey));
        hashmap.put("methodName", methodName);
        return hashmap;
    }


}
