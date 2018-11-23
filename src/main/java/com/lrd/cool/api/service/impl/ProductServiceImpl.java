package com.lrd.cool.api.service.impl;

import com.lrd.cool.api.BusinessException;
import com.lrd.cool.api.ResponseCode;
import com.lrd.cool.api.entity.Product;
import com.lrd.cool.api.repository.ProductRepository;
import com.lrd.cool.api.service.IProductService;
import com.lrd.cool.api.utils.LogMan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lierdong
 */
@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getProducts(int pageNumber, int pageSize, String sortProperty, int sortDirection) throws BusinessException {
        if (pageNumber < 1) {
            throw new BusinessException(ResponseCode.CODE_INVALID_PARAMS, "pageNumber不能小于1");
        }
        if (pageSize < 0) {
            throw new BusinessException(ResponseCode.CODE_INVALID_PARAMS, "pageSize不能小于0");
        }
        Sort sort = Sort.by(sortDirection == 0 ? Sort.Direction.ASC : Sort.Direction.DESC, sortProperty);
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize, sort);
        return repository.findAll(pageRequest).getContent();
    }

    @Override
    public List<Product> getProducts() {
        return repository.findAll();
    }

    @Override
    public void saveProduct(Product product) throws BusinessException {
        LogMan.i("要添加的商品信息：" + product.toString());
        LogMan.i("添加之后:" + repository.save(product).toString());
    }

    @Override
    public void deleteProduct(Long id) throws BusinessException {
        repository.deleteById(id);
    }

    @Override
    public Long getProductCount() {
        return repository.count();
    }
}
