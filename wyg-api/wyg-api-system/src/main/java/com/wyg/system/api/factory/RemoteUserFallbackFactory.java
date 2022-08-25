package com.wyg.system.api.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import com.wyg.common.core.domain.R;
import com.wyg.system.api.RemoteUserService;
import com.wyg.system.api.domain.SysUser;
import com.wyg.system.api.model.LoginUser;

/**
 * 用户服务降级处理
 * 
 * @author wyg
 */
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteUserFallbackFactory.class);

    @Override
    public RemoteUserService create(Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService()
        {
            @Override
            public R<LoginUser> getUserInfo(String username, String source)
            {
                return R.fail("获取用户失败：" + throwable.getMessage());
            }

            @Override
            public R<SysUser> findById(Long id, String source) {
                return R.fail("获取用户失败:" + throwable.getMessage());
            }

            @Override
            public R<Boolean> registerUserInfo(SysUser sysUser, String source)
            {
                return R.fail("注册用户失败：" + throwable.getMessage());
            }

            @Override
            public R<SysUser> addUser(SysUser sysUser, String source) {
                return R.fail("添加用户失败：" + throwable.getMessage());
            }

            @Override
            public R<Integer> updateUser(SysUser sysUser, String source) {
                return R.fail("更新用户失败：" + throwable.getMessage());
            }

            @Override
            public R<LoginUser> selectByWxOpenId(String wxOpenId, String source) {
                return R.fail("获取用户信息失败：" + throwable.getMessage());
            }

            @Override
            public R<Boolean> checkUserNameExist(SysUser sysUser, String source) {
                return R.fail("检查用户邮箱是否存在失败：" + throwable.getMessage());
            }

            @Override
            public R<Boolean> checkPhoneExist(SysUser sysUser, String source) {
                return R.fail("检查用户电话是否存在失败：" + throwable.getMessage());
            }

            @Override
            public R<Boolean> checkEmailExist(SysUser sysUser, String source) {
                return R.fail("检查用户电话是否存在失败：" + throwable.getMessage());
            }

            @Override
            public R<Boolean> checkIdNumberExist(SysUser sysUser, String source) {
                return R.fail("检查用户电话是否存在失败：" + throwable.getMessage());
            }

        };
    }
}
