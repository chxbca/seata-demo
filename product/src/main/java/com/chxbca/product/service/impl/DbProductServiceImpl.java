package com.chxbca.product.service.impl;

import com.chxbca.product.dao.DbProductDao;
import com.chxbca.product.entity.DbProduct;
import com.chxbca.product.service.DbProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (DbProduct)表服务实现类
 *
 * @author makejava
 * @since 2020-09-02 11:26:51
 */
@Service("dbProductService")
public class DbProductServiceImpl implements DbProductService {
    @Resource
    private DbProductDao dbProductDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DbProduct queryById(Integer id) {
        return this.dbProductDao.findById(id).
                orElseThrow(() -> new RuntimeException("测试错误"));
    }

    /**
     * 新增数据
     *
     * @param dbProduct 实例对象
     * @return 实例对象
     */
    @Override
    public DbProduct insert(DbProduct dbProduct) {
        this.dbProductDao.save(dbProduct);
        return dbProduct;
    }

    /**
     * 修改数据
     *
     * @param dbProduct 实例对象
     * @return 实例对象
     */
    @Override
    public DbProduct update(DbProduct dbProduct) {
        this.dbProductDao.save(dbProduct);
        return this.queryById(dbProduct.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     */
    @Override
    public void deleteById(Integer id) {
        this.dbProductDao.deleteById(id);
    }
}