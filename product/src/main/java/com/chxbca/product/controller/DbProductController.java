package com.chxbca.product.controller;

import com.chxbca.product.entity.DbProduct;
import com.chxbca.product.service.DbProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (DbProduct)表控制层
 *
 * @author makejava
 * @since 2020-09-02 11:26:51
 */
@RestController
public class DbProductController {
    /**
     * 服务对象
     */
    @Resource
    private DbProductService dbProductService;

    @PostMapping(path = "/message")
    DbProduct sendMessage(@RequestBody DbProduct productDto) {
        return dbProductService.insert(productDto);
    }
}