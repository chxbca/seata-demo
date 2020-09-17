package com.chxbca.product.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * (DbProduct)实体类
 *
 * @author makejava
 * @since 2020-09-02 11:26:51
 */
@Data
@Entity
@Table(name = "db_product")
public class DbProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String message;

}