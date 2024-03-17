package com.example.EKart.Service.Implementation;

import com.example.EKart.Dao.Entity.Product;
import com.example.EKart.Dao.Repository.ProductRepository;
import com.example.EKart.Dto.Request.ProductRequest;
import com.example.EKart.Dto.Response.AllResponse;
import com.example.EKart.Dto.Response.DeleteResponse;
import com.example.EKart.Dto.Response.ProductResponse;
import com.example.EKart.Dto.Response.UpdateResponse;
import com.example.EKart.Exception.BusinessException;
import com.example.EKart.Exception.GlobalExceptionHandler;
import com.example.EKart.Mapper.ProductMapper;
import com.example.EKart.Service.Interface.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    //Add a Product-->Done
    @Override
    public ResponseEntity<?> addProduct(ProductRequest productRequest) {
        try {
            Product product = ProductMapper.mapToProduct(productRequest);
            productRepository.save(product);

            ProductResponse productResponse = ProductMapper.mapToProductResponse(product);
            return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
        } catch (BusinessException e) {
            GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
            globalExceptionHandler.setErrorCode(e.getErrorCode());
            globalExceptionHandler.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(globalExceptionHandler, HttpStatus.BAD_REQUEST);
        }
    }

    //Soft deletion of a product-->Done
    @Override
    public ResponseEntity<DeleteResponse> deleteProduct(Integer id) {
        try {
            DeleteResponse deleteResponse=new DeleteResponse();
            Product product = productRepository.findById(id).get();
            product.setIsActive(false);
            productRepository.save(product);
            deleteResponse.setMessage("Product Deleted Successfully....");
            return new ResponseEntity<>(deleteResponse,HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Get all products as a Response-->Done
    @Override
    public ResponseEntity<List<AllResponse>> getAllProduct() {
        try {
           List<Product> productList = productRepository.findAll();
           List<AllResponse> allResponses = ProductMapper.mapToAllResponseList(productList);
        return ResponseEntity.ok(allResponses);
          } catch (Exception e) {
         return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
          }
    }

    //Get the product as a Response
    @Override
    public ResponseEntity<?> getProductById(Integer id) {
        try {
            Optional<Product> productOptional = productRepository.findById(id);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                AllResponse allResponse = ProductMapper.mapToResponse(product);
                return new ResponseEntity<>(allResponse, HttpStatus.OK);
            } else {
                throw new BusinessException("602", "Records are not present for the given id");
            }
        } catch (BusinessException e) {
            GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
            globalExceptionHandler.setErrorCode(e.getErrorCode());
            globalExceptionHandler.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(globalExceptionHandler, HttpStatus.NOT_FOUND);
        }
    }


    //Update the Product
    @Override
    public ResponseEntity<UpdateResponse> updateProduct(ProductRequest productRequest, Integer id) {
        UpdateResponse updateResponse=new UpdateResponse();
        try {
            Product oldProduct = productRepository.findById(id).get();
            oldProduct.setProductName(productRequest.getProductName());
            oldProduct.setProductDescription(productRequest.getProductDescription());
            if(productRequest.getProductType().equalsIgnoreCase("frozen")|| productRequest.getProductType().equalsIgnoreCase("Non-frozen")){
                oldProduct.setProductType(productRequest.getProductType());
            }else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            productRepository.save(oldProduct);
            updateResponse.setMessage("Product updated Successfully...");
            return new ResponseEntity<>(updateResponse,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    //implementing Search Functionality
   @Override
    public ResponseEntity<List<AllResponse>> searchProducts(String query) {
         try {
            List<Product> products = productRepository.searchProducts(query);
            List<AllResponse> allResponses = ProductMapper.mapToSearchResponseList(products);
        return ResponseEntity.ok(allResponses);
         } catch (Exception e) {
           throw e;
         }
    }

}
