package com.jdh.feign;

import com.jdh.pojo.SearchBoxDo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "JIANDAOHANG-API")
public interface SearchBoxService {

    /**
     * 根据用户id 获取用户搜索框样式
     * @param uid
     * @return
     */
    @GetMapping("/searchbox/uid/{uid}")
    public SearchBoxDo getSearchBoxByUid(@PathVariable(name = "uid") Integer uid);

    /**
     * 保存搜索框样式信息
     * @param searchBoxDo
     * @return
     */
    @PostMapping(value = "/searchbox/add",consumes = "appliction/json")
    public Integer saveSearchBox(@RequestBody SearchBoxDo searchBoxDo);

    /**
     * 更新搜索框样式信息
     * @param searchBoxDo
     * @return
     */
    @PostMapping(value = "/searchbox/update",consumes = "appliction/json")
    public Integer updateSearchBox(@RequestBody SearchBoxDo searchBoxDo);
}
