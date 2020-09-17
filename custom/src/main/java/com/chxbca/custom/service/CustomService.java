package com.chxbca.custom.service;

import com.chxbca.custom.client.ProductClient;
import com.chxbca.custom.dao.CustomDao;
import com.chxbca.custom.model.Custom;
import com.chxbca.custom.model.convert.DtoAssembler;
import com.chxbca.custom.model.dto.ProductDto;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

/**
 * @author chxbca
 */
@Service
public class CustomService {

    private static final String FAIL = "fail";
    @Autowired
    private CustomDao customDao;
    @Autowired
    private ProductClient productClient;

    @GlobalTransactional
    public Object sendAndSave(String message) {
        ProductDto build = ProductDto.builder().message(message).build();
        ProductDto productDto = productClient.sendMessage(build);
        Custom custom = DtoAssembler.INSTANCE.toEntity(productDto);
        Custom result = customDao.save(custom);
        if (Objects.equals(message, FAIL)) {
            throw new RuntimeException(FAIL);
        }
        return Arrays.asList(result, productDto);
    }

    @Transactional(rollbackFor = Exception.class)
    public Object localMessage(String message) {
        Custom custom = Custom.builder().sendMessage(message).productId(RandomUtils.nextInt()).build();
        Custom result = customDao.save(custom);
        if (Objects.equals(message, FAIL)) {
            throw new RuntimeException(FAIL);
        }
        return Collections.singletonList(result);
    }
}
