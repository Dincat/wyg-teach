package com.wyg.teach.api.factory;

import com.wyg.common.core.domain.R;
import com.wyg.teach.api.RemoteTeacherService;
import com.wyg.teach.api.domain.TeachStudent;
import com.wyg.teach.api.domain.TeachTeacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 教师服务降级处理
 *
 * @author wyg
 */
@Component
public class RemoteTeacherFallbackFactory implements FallbackFactory<RemoteTeacherService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteTeacherFallbackFactory.class);

    @Override
    public RemoteTeacherService create(Throwable throwable) {
        log.error("文件服务调用失败:{}", throwable.getMessage());
        return new RemoteTeacherService() {

            @Override
            public R<TeachTeacher> getTeacherByIdNumber(String idNumber, String source) {
                return R.fail("获取教职工信息失败：" + throwable.getMessage());
            }
        };
    }
}
