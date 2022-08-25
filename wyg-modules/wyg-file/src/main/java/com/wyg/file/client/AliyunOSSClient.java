package com.wyg.file.client;

import com.aliyun.oss.OSSClient;
import com.wyg.common.core.utils.bean.PropertiesUtil;
import com.wyg.file.config.AliyunConfig;
import com.wyg.file.config.OssConfig;
import com.wyg.file.exception.OSSException;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * All rights Reserved, Designed By kjt.gzst.gov.cn
 *
 * @version V1.0
 * @title: IOSSClient.java
 * @description: 阿里云的客户端操作 * @date: 2017年8月11日 下午9:55:00
 * @copyright: 2017 kjt.gzst.gov.cn Inc. All rights reserved.
 */
public class AliyunOSSClient extends AbstractOSSClient {
    public static final String DEFAULT_CONFIG_FILE = "aliyun.oss.properties";
    //阿里云客户端
    private OSSClient client;
    //阿里云API的内或外网域名
    private String endpoint;
    //阿里云API的密钥Access Key ID
    private String accessKeyId;
    //阿里云API的密钥Access Key Secret
    private String accessKeySecret;
    //访问域名
    private String domain;
    //bucket名称
    private String bucketName;

    @Override
    public void init() {
        init(DEFAULT_CONFIG_FILE);
    }

    @Override
    public void init(String propertiesName) {
        PropertiesUtil p = new PropertiesUtil(propertiesName);
        endpoint = p.getString("aliyun.oss.endpoint");
        accessKeyId = p.getString("aliyun.oss.access-key-id");
        accessKeySecret = p.getString("aliyun.oss.access-key-secret");
        bucketName = p.getString("aliyun.oss.bucket-name");
        domain = p.getString("aliyun.oss.domain");
        client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    public void init(OssConfig config) {
        AliyunConfig aliyunConfig = config.getAliyun();
        endpoint = aliyunConfig.getEndpoint();
        accessKeyId = aliyunConfig.getAccessKeyId();
        accessKeySecret = aliyunConfig.getAccessKeySecret();
        bucketName = aliyunConfig.getBucketName();
        domain = aliyunConfig.getDomain();
        client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 一般不推荐使用
     *
     * @return
     */
    public OSSClient getClient() {
        return this.client;
    }

    /**
     * 文件上传
     *
     * @param inputStream
     * @param path
     * @return
     */
    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            client.putObject(bucketName, path, inputStream);
        } catch (Exception e) {
            throw new OSSException("上传文件失败", e);
        }
        return domain + "/" + path;
    }

    /**
     * 文件上传
     *
     * @param file
     * @param path
     * @return
     */
    @Override
    public String upload(MultipartFile file, String path) {
        try {
            client.putObject(bucketName, path, file.getInputStream());
        } catch (Exception e) {
            throw new OSSException("上传文件失败", e);
        }
        return domain + "/" + path;
    }

    @Override
    public void delete(String filename) {

        try {
            filename = filename.replace(domain + "/", "");
            client.deleteObject(bucketName, filename);
        } catch (Exception e) {
            throw new OSSException("删除文件失败", e);
        }
    }
}
