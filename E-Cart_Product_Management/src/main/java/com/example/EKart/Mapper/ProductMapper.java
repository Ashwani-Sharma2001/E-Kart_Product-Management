package com.example.EKart.Mapper;

import com.example.EKart.Dao.Entity.Product;
import com.example.EKart.Dto.Request.ProductRequest;
import com.example.EKart.Dto.Response.AllResponse;
import com.example.EKart.Dto.Response.ProductResponse;
import com.example.EKart.Exception.BusinessException;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    /**-----------------getAllMapper--------------**/
    public static AllResponse mapToAllResponse(Product product) {
        return AllResponse.builder()
                .productCode(product.getProductCode())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .productType(product.getProductType())
                .build();
    }

    public static List<AllResponse> mapToAllResponseList(List<Product> productList) {
        return productList.stream()
                .map(ProductMapper::mapToAllResponse)
                .collect(Collectors.toList());
    }

    /**-----------Add Mapper-----------**/

    public static Product mapToProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setProductDescription(productRequest.getProductDescription());

        if (productRequest.getProductType().equalsIgnoreCase("frozen") ||
            productRequest.getProductType().equalsIgnoreCase("Non-frozen")) {
        product.setProductType(productRequest.getProductType());
        } else {
        throw new BusinessException("605", "producttype only accept from frozen and non-frozen parameter");
        }

       product.setCreated(new Date());
       product.setModified(new Date());
       product.setIsActive(Boolean.TRUE);

        return product;
    }

    public static ProductResponse mapToProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setMessage("product added successfully.....");
        return productResponse;
    }

     /** ----------------getResponseByIdMapper------------- **/
     public static AllResponse mapToResponse(Product product) {
         return AllResponse.builder()
                 .productCode(product.getProductCode())
                 .productName(product.getProductName())
                 .productDescription(product.getProductDescription())
                 .productType(product.getProductType())
                 .build();
     }

   /** ---------------------SearchProducts--------------- **/

    public static AllResponse mapToSearchResponse(Product product) {
        return AllResponse.builder()
                .productCode(product.getProductCode())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .productType(product.getProductType())
                .build();
    }

    public static List<AllResponse> mapToSearchResponseList(List<Product> productList) {
        return productList.stream()
                .map(ProductMapper::mapToSearchResponse)
                .collect(Collectors.toList());
    }
}

