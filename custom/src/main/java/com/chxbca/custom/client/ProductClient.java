package com.chxbca.custom.client;

import com.chxbca.custom.model.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author chxbca
 */
@FeignClient(name = "product")
public interface ProductClient {

    @PostMapping(path = "/message")
    ProductDto sendMessage(@RequestBody ProductDto productDto);

}
