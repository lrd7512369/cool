package com.lrd.cool.api.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author lierdong
 * @date 2018-08-01
 */
@Entity()
@Table(name = "tb_product")
public class Product implements Serializable {

    /**
     * 商品编号
     */
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    /**
     * 商品缩略图地址
     */
    @Column(name = "preview_image")
    private String previewImage;

    /**
     * 商品名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 品牌
     */
    @Column(name = "brand")
    private String brand;

    /**
     * 进价
     */
    @Column(name = "bid")
    private Integer bid;

    /**
     * 售价
     */
    @Column(name = "price")
    private Integer price;

    /**
     * 库存数量
     */
    @Column(name = "stock")
    private Integer stock = 0;

    /**
     * 已售出数量
     */
    @Column(name = "sold")
    private Integer sold = 0;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getPreviewImage() {
        return previewImage;
    }

    public void setPreviewImage(String previewImage) {
        this.previewImage = previewImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + productId +
                ", preview_image='" + previewImage + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", bid=" + bid +
                ", price=" + price +
                ", stock=" + stock +
                ", sold=" + sold +
                '}';
    }


}
