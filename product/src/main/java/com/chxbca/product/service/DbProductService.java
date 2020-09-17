package com.chxbca.product.service;

import com.chxbca.product.entity.DbProduct;

/**
 * (DbProduct)表服务接口
 *
 * @author makejava
 * @since 2020-09-02 11:26:51
 */
public interface DbProductService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DbProduct queryById(Integer id);

    /**
     * 新增数据
     *
     * @param dbProduct 实例对象
     * @return 实例对象
     */
    DbProduct insert(DbProduct dbProduct);

    /**
     * 修改数据
     *
     * @param dbProduct 实例对象
     * @return 实例对象
     */
    DbProduct update(DbProduct dbProduct);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     */
    void deleteById(Integer id);

}