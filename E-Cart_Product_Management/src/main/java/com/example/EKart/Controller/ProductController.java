package com.example.EKart.Controller;
import com.example.EKart.Dto.Request.ProductRequest;
import com.example.EKart.Dto.Response.AllResponse;
import com.example.EKart.Dto.Response.DeleteResponse;

import com.example.EKart.Dto.Response.UpdateResponse;
import com.example.EKart.Service.Interface.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
public class  ProductController {

    private final ProductService productService;

   /**
    *Api Request to Add Product in the ProductsList
    *@Param --ProductRequest contains Product Data
    *
    *@return Displaying response with status and String Message Successsful completion
    **/
    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductRequest productRequest){
        return productService.addProduct(productRequest);
    }

    /**
     *Api Request to Delete Product in the ProductsList
     *@Param --ProductId to find product
     *
     *@return Displaying response with status and String Message Successsful completion
     **/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DeleteResponse> deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }

    /**
     *Api Request to  Dispaly all Product from ProductsList
     *@Param --No parameter Required
     *
     *@return Displaying response with status and String Message Successsful completion
     **/
    @GetMapping("/products")
    public ResponseEntity<List<AllResponse>> getallProduct(){
        return productService.getAllProduct();
    }

    /**
     *Api Request to get Product on basis of Specific Id in the ProductsList
     *@Param --ProductId
     *
     *@return Displaying response with status and String Message Successsful completion
     **/
    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProductByid(@PathVariable Integer id) {
             return productService.getProductById(id);
    }

    /**
     *Api Request to Update Product on Basis Of ProductId in the ProductsList
     *@Param --ProductId
     *
     *@return Displaying response with status and String Message Successsful completion
     **/
    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateResponse> updateProduct(@RequestBody ProductRequest productRequest, @PathVariable Integer id){
        return productService.updateProduct(productRequest,id);
    }

    /**
     *Api Request to Search Products on Search Conditions in the ProductsList
     *@Param --Contains parameter to search on basis of ProductId,ProductName and ProductType
     *
     *@return Displaying response with status and String Message Successsful completion
     *  use search?query=
     **/
    @GetMapping("/search")
    public ResponseEntity<List<AllResponse>> searchProducts(@RequestParam("query") String query){
        return  productService.searchProducts(query);
    }

}
