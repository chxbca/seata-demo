package com.chxbca.custom.dao;

import com.chxbca.custom.model.Custom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chxbca
 */
public interface CustomDao extends JpaRepository<Custom, Integer> {
}
