package com.wyg.teach.api;

import com.wyg.common.core.constant.SecurityConstants;
import com.wyg.common.core.domain.R;
import com.wyg.teach.api.domain.TeachStudent;
import com.wyg.teach.api.domain.TeachTeacher;
import com.wyg.teach.api.factory.RemoteTeacherFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.wyg.common.core.constant.ServiceNameConstants;

/**
 * 教职工服务
 *
 * @author wyg
 */
@FeignClient(contextId = "remoteTeacherService", value = ServiceNameConstants.TEACH_SERVICE, fallbackFactory = RemoteTeacherFallbackFactory.class)
public interface RemoteTeacherService {



    /**
     * 根据身份证号获取学生信息
     *
     * @return 结果
     */
    @GetMapping(value = "/teacher/getTeacherByIdNumber/{idNumber}")
    public R<TeachTeacher> getTeacherByIdNumber(@PathVariable(value = "idNumber") String idNumber, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

}
