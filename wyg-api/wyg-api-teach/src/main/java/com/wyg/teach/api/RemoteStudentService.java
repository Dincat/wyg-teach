package com.wyg.teach.api;

import com.wyg.common.core.constant.SecurityConstants;
import com.wyg.common.core.constant.ServiceNameConstants;
import com.wyg.common.core.domain.R;
import com.wyg.teach.api.domain.TeachStudent;
import com.wyg.teach.api.factory.RemoteTeacherFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 教职工服务
 *
 * @author wyg
 */
@FeignClient(contextId = "remoteStudentService", value = ServiceNameConstants.TEACH_SERVICE, fallbackFactory = RemoteTeacherFallbackFactory.class)
public interface RemoteStudentService {

    /**
     * 根据身份证号获取学生信息
     *
     * @return 结果
     */
    @GetMapping(value = "/teacher/getStudentByIdNumber/{idNumber}")
    public R<TeachStudent> getStudentByIdNumber(@PathVariable(value = "idNumber") String idNumber, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);
}
