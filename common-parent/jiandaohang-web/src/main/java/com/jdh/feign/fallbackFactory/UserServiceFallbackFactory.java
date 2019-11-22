package com.jdh.feign.fallbackFactory;

import com.jdh.feign.UserService;
import com.jdh.pojo.Permission;
import com.jdh.pojo.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public class UserServiceFallbackFactory implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable throwable) {
        return new UserService() {
            @Override
            public User findByUserName(String username) {
                return null;
            }

            @Override
            public List<Permission> findPermissionByUsername(String username) {
                return null;
            }

            @Override
            public Map updatePassword(User user) {
                return null;
            }
        };
    }
}
