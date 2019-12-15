package com.jdh.feign;

import com.jdh.pojo.BackgroundSpeDo;
import com.jdh.utils.PageDataGridResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "JIANDAOHANG-API")
public interface BackgroundSpeService {

    /**
     * 获取所有背景特效
     * @param transparent 是否透明 必选
     * @param page 第几页 可选
     * @param size 每页几条 可选
     * @param field 排序字段名 可选
     * @param order asc/desc 排序规则 可选
     * @return
     */
    @GetMapping("/bgSpe/transparent/{transparent}")
    public PageDataGridResult<BackgroundSpeDo> getBackgroundSpeByTransparent(@PathVariable(name = "transparent") Boolean transparent, @RequestParam(required = false,name = "page") Integer page, @RequestParam(required = false,name = "size")Integer size, @RequestParam(required = false,name = "field")String field, @RequestParam(required = false,name = "order")String order);

    /**
     * 根据sid查询背景特效
     * @param sid
     * @return
     */
    @GetMapping("/bgSpe/sid/{sid}")
    public BackgroundSpeDo getBackgroundSpeBySid(@PathVariable(name = "sid") Long sid);
}
