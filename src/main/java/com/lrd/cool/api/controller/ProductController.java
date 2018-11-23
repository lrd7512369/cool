package com.lrd.cool.api.controller;

import com.lrd.cool.api.BusinessException;
import com.lrd.cool.api.ResponseCode;
import com.lrd.cool.api.entity.Product;
import com.lrd.cool.api.response.ApiResult;
import com.lrd.cool.api.service.IProductService;
import com.lrd.cool.api.utils.ApiUtils;
import com.lrd.cool.api.utils.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author lierdong
 */
@RestController
@RequestMapping()
public class ProductController {

    private final IProductService service;

    @Autowired
    public ProductController(IProductService service) {
        this.service = service;
    }

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
    @GetMapping(value = "/products")
    public ApiResult getProducts(@RequestParam(name = "pageNumber", required = false) Integer pageNumber
            , @RequestParam(name = "pageSize", required = false) Integer pageSize
            , @RequestParam(name = "sortProperty", defaultValue = "productId", required = false) String sortProperty
            , @RequestParam(name = "sortDirection", defaultValue = "1", required = false) Integer sortDirection) throws BusinessException {
        ApiResult response = new ApiResult();
        List<Product> products;
        if (pageNumber != null && pageSize != null) {
            products = service.getProducts(pageNumber, pageSize, sortProperty, sortDirection);
        } else {
            products = service.getProducts();
        }
        if (products == null) {
            response.setCode(ResponseCode.CODE_ERROR);
            response.setMessage("获取商品信息失败，请求参数异常");
        } else {
            response.setCode(ResponseCode.CODE_SUCCESS);
            response.setMessage("获取商品信息成功");
        }
        response.setValue(products);
        return response;
    }

    /**
     * 添加商品信息
     *
     * @param request 请求参数
     * @param image   商品图片
     * @return 添加结果
     * @throws BusinessException 业务异常
     */
    @PostMapping(value = "/products")
    public ApiResult saveProduct(HttpServletRequest request, @RequestParam(name = "photo") MultipartFile image) throws BusinessException {
        String imageName = image.getOriginalFilename();
        if (StringUtils.isEmpty(imageName)) {
            return ApiUtils.getResultForInvalidParams("添加失败，图片名字为空");
        }
        String imageDirPath = request.getSession().getServletContext().getRealPath("/") + "product/image/";
        File imageDir = new File(imageDirPath);
        if (!imageDir.exists() && !imageDir.mkdirs()) {
            throw new BusinessException(ResponseCode.CODE_ERROR, "添加失败，创建目标文件夹异常");
        }
        String previewImage = saveFile(image, imageDir, imageName);
        if (StringUtils.isEmpty(previewImage)) {
            throw new BusinessException(ResponseCode.CODE_ERROR, "添加失败，保存商品图片异常");
        }
        String name = request.getParameter("name");
        if (StringUtils.isEmpty(name)) {
            return ApiUtils.getResultForInvalidParams("添加失败，商品名称不能为空");
        }
        String brand = request.getParameter("brand");
        if (StringUtils.isEmpty(brand)) {
            return ApiUtils.getResultForInvalidParams("添加失败，商品品牌不能为空");
        }
        String bid = request.getParameter("bid");
        if (StringUtils.isEmpty(bid)) {
            return ApiUtils.getResultForInvalidParams("添加失败，商品进价不能为空");
        }
        String price = request.getParameter("price");
        if (StringUtils.isEmpty(price)) {
            return ApiUtils.getResultForInvalidParams("添加失败，商品售价不能为空");
        }
        Product product = new Product();
        product.setPreviewImage(previewImage);
        product.setName(name);
        product.setBrand(brand);
        product.setBid(Integer.valueOf(bid));
        product.setPrice(Integer.valueOf(price));
        service.saveProduct(product);
        ApiResult response = new ApiResult();
        response.setCode(ResponseCode.CODE_SUCCESS);
        response.setMessage("新增商品成功");
        response.setValue(null);
        return response;
    }

    /**
     * 更新商品信息
     *
     * @param request 请求参数
     * @param image   商品图片
     * @return 更新结果
     * @throws BusinessException 业务异常
     */
    @PutMapping(value = "/products")
    public ApiResult updateProduct(HttpServletRequest request, @RequestParam(name = "photo", required = false) MultipartFile image) throws BusinessException {
        String productId = request.getParameter("productId");
        if (StringUtils.isEmpty(productId)) {
            return ApiUtils.getResultForInvalidParams("更新失败，商品id不能为空");
        }
        String previewImage;
        //更新图片时，可能并没有上传新的图片文件
        if (image == null) {
            previewImage = request.getParameter("previewImage");
            if (StringUtils.isEmpty(previewImage)) {
                return ApiUtils.getResultForInvalidParams("更新失败，商品图片地址不能为空");
            }
        } else {
            String imageName = image.getOriginalFilename();
            if (StringUtils.isEmpty(imageName)) {
                return ApiUtils.getResultForInvalidParams("更新失败，图片名字为空");
            }
            String imageDirPath = request.getSession().getServletContext().getRealPath("/") + "product/image/";
            File imageDir = new File(imageDirPath);
            if (!imageDir.exists() && !imageDir.mkdirs()) {
                throw new BusinessException(ResponseCode.CODE_ERROR, "更新失败，创建目标文件夹异常");
            }
            previewImage = saveFile(image, imageDir, imageName);
            if (StringUtils.isEmpty(previewImage)) {
                throw new BusinessException(ResponseCode.CODE_ERROR, "更新失败，保存商品图片异常");
            }
        }
        String name = request.getParameter("name");
        if (StringUtils.isEmpty(name)) {
            return ApiUtils.getResultForInvalidParams("更新失败，商品名称不能为空");
        }
        String brand = request.getParameter("brand");
        if (StringUtils.isEmpty(brand)) {
            return ApiUtils.getResultForInvalidParams("更新失败，商品品牌不能为空");
        }
        String bid = request.getParameter("bid");
        if (StringUtils.isEmpty(bid)) {
            return ApiUtils.getResultForInvalidParams("更新失败，商品进价不能为空");
        }
        String price = request.getParameter("price");
        if (StringUtils.isEmpty(price)) {
            return ApiUtils.getResultForInvalidParams("更新失败，商品售价不能为空");
        }
        String stock = request.getParameter("stock");
        if (StringUtils.isEmpty(stock)) {
            return ApiUtils.getResultForInvalidParams("更新失败，商品库存数量不能为空");
        }
        String sold = request.getParameter("sold");
        if (StringUtils.isEmpty(sold)) {
            return ApiUtils.getResultForInvalidParams("添加失败，商品已销售数量不能为空");
        }
        Product product = new Product();
        product.setProductId(Long.valueOf(productId));
        product.setPreviewImage(previewImage);
        product.setName(name);
        product.setBrand(brand);
        product.setBid(Integer.valueOf(bid));
        product.setPrice(Integer.valueOf(price));
        product.setStock(Integer.valueOf(stock));
        product.setSold(Integer.valueOf(sold));
        service.saveProduct(product);
        ApiResult response = new ApiResult();
        response.setCode(ResponseCode.CODE_SUCCESS);
        response.setMessage("更新商品成功");
        response.setValue(null);
        return response;
    }

    /**
     * 删除商品信息
     *
     * @param productId 商品id
     * @return 删除结果
     * @throws BusinessException 业务异常
     */
    @DeleteMapping(value = "products/{productId}")
    public ApiResult deleteProduct(@PathVariable(name = "productId") Long productId) throws BusinessException {
        service.deleteProduct(productId);
        ApiResult result = new ApiResult();
        result.setCode(ResponseCode.CODE_SUCCESS);
        result.setMessage("删除商品成功");
        result.setValue(null);
        return result;
    }

    /**
     * 获取商品总数量
     *
     * @return 商品个数
     */
    @GetMapping(value = "products/count")
    public ApiResult getProductCount() {
        ApiResult result = new ApiResult();
        result.setCode(ResponseCode.CODE_SUCCESS);
        result.setMessage("获取商品总数成功");
        result.setValue(service.getProductCount());
        return result;
    }

    /**
     * 保存图片到服务器
     *
     * @param image     图片文件
     * @param imageDir  图片存贮的目录
     * @param imageName 存贮名称
     * @return 保存后图片的url地址
     */
    private String saveFile(MultipartFile image, File imageDir, String imageName) {
        try {
            File productImage = new File(imageDir, imageName);
            image.transferTo(productImage);
            return SystemUtils.getSchema() + "/product/image/" + imageName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
