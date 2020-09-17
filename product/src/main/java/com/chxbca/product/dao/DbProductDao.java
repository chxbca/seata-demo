package com.chxbca.product.dao;

import com.chxbca.product.entity.DbProduct;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * (DbProduct)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-02 11:26:51
 */
public interface DbProductDao extends JpaRepository<DbProduct, Integer> {
}