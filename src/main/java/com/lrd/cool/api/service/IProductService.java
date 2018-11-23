package com.lrd.cool.api.service;

import com.lrd.cool.api.BusinessException;
import com.lrd.cool.api.entity.Product;

import java.util.List;

/**
 * @author lierdong
 */
public interface IProductService {

    /**
     * 获取已添加的商品信息
     *
     * @param pageNumber    第几页
     * @param pageSize      每页多少条数据
     * @param sortProperty  排序属性
     * @param sortDirection 排序顺序
     * @return 商品列表
     * @throws BusinessException 业务异常
     */
    List<Product> getProducts(int pageNumber, int pageSize, String sortProperty, int sortDirection) throws BusinessException;

    /**
     * 获取所有已添加的商品信息
     * @return 商品信息
     */
    List<Product> getProducts();

    /**
     * 保存商品信息
     *
     * @param product 商品实体
     * @throws BusinessException 业务异常
     */
    void saveProduct(Product product) throws BusinessException;

    /**
     * 删除商品
     *
     * @param id 商品id
     * @throws BusinessException 业务异常
     */
    void deleteProduct(Long id) throws BusinessException;

    /**
     * 获取商品总数量
     *
     * @return 商品总数量
     */
    Long getProductCount();
}
