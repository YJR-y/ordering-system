package com.yjr.ordering_system.controller;

import com.yjr.ordering_system.VO.ProductInfoVO;
import com.yjr.ordering_system.VO.ProductVO;
import com.yjr.ordering_system.VO.ResultVO;
import com.yjr.ordering_system.entity.ProductCategory;
import com.yjr.ordering_system.entity.ProductInfo;
import com.yjr.ordering_system.repository.ProductInfoRepository;
import com.yjr.ordering_system.service.CategoryService;
import com.yjr.ordering_system.service.ProductService;
import com.yjr.ordering_system.utils.ResultVOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * 买家商品
 *
 * @author yjr
 * @since 2020/11/02 15:32
 */
@RestController
@RequestMapping(value = "/buyer/product")
@CrossOrigin(origins = "*")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @GetMapping("/list")
    public ResultVO<List<ProductVO>> list(HttpServletRequest request) {

        //1.查询所有上架的商品
        List<ProductInfo> upAll = productService.findUpAll();

        //2.查询类目(一次性查询)
        ArrayList<Integer> categoryTypeList = new ArrayList<>();
        for (ProductInfo productInfo : upAll) {
            categoryTypeList.add(productInfo.getCategoryType());
        }
        // 3.查询商品的所有类目
        List<ProductCategory> CategoryTypeInList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3.数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : CategoryTypeInList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());
            // productVO.setProductInfoVOList();

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();

            for (ProductInfo productInfo : upAll) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                     BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtils.success(productVOList);
    }

}
