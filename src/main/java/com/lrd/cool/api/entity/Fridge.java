package com.lrd.cool.api.entity;

import java.util.List;

/**
 * 冰箱详情
 *
 * @author lierdong
 * @date 2018-08-29
 */
public class Fridge {

    /**
     * 冰箱编号
     */
    private Long id;

    /**
     * 冰箱每层的详情
     */
    private List<Layer> layers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }
}

class Layer{

    /**
     * 层数
     */
    private Integer layerIndex;

    /**
     * 该层的商品详情
     */
    private List<LayerProductDetail> productDetails;

    public Integer getLayerIndex() {
        return layerIndex;
    }

    public void setLayerIndex(Integer layerIndex) {
        this.layerIndex = layerIndex;
    }

    public List<LayerProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<LayerProductDetail> productDetails) {
        this.productDetails = productDetails;
    }

}

class LayerProductDetail{

    /**
     * 商品详情
     */
    private Product product;

    /**
     * 商品数量
     */
    private Integer productCount;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }
}
