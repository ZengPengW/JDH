package com.jdh.feign;

import com.jdh.pojo.BackgroundImgDo;
import com.jdh.utils.JdhResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "JIANDAOHANG-API")
public interface BackgroundService {
    @PostMapping(value = "/upload/bgImg",consumes = "appliction/json")
    public JdhResult saveBackgroundImg(@RequestBody BackgroundImgDo backgroundImgDo);
}
