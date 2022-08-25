package com.wyg.teach.api.factory;

import com.wyg.common.core.domain.R;
import com.wyg.teach.api.RemoteStudentService;
import com.wyg.teach.api.domain.TeachStudent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 学生服务降级处理
 *
 * @author wyg
 */
@Component
public class RemoteStudentFallbackFactory implements FallbackFactory<RemoteStudentService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteStudentFallbackFactory.class);

    @Override
    public RemoteStudentService create(Throwable throwable) {
        log.error("文件服务调用失败:{}", throwable.getMessage());
        return new RemoteStudentService() {

            @Override
            public R<TeachStudent> getStudentByIdNumber(String idNumber, String source) {
                return R.fail("获取教职工信息失败：" + throwable.getMessage());
            }
        };
    }
}
