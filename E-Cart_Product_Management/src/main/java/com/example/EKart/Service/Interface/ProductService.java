package com.example.EKart.Service.Interface;

import com.example.EKart.Dto.Request.ProductRequest;
import com.example.EKart.Dto.Response.AllResponse;
import com.example.EKart.Dto.Response.DeleteResponse;
import com.example.EKart.Dto.Response.UpdateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public ResponseEntity<?> addProduct(ProductRequest productRequest);

    public ResponseEntity<DeleteResponse> deleteProduct(Integer id);

    public ResponseEntity<List<AllResponse>> getAllProduct();

    public ResponseEntity<?> getProductById(Integer id);


    public ResponseEntity<UpdateResponse> updateProduct(ProductRequest productRequest, Integer id);

    public ResponseEntity<List<AllResponse>> searchProducts(String query);

}
