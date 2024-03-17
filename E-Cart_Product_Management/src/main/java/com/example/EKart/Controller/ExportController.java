package com.example.EKart.Controller;

import com.example.EKart.Dao.Entity.Product;
import com.example.EKart.Dao.Repository.ProductRepository;
import com.example.EKart.Service.Implementation.CSVMethod2;
import com.example.EKart.Service.Interface.ExportExcelService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/Report")
@AllArgsConstructor
public class ExportController {

    private final ExportExcelService exportExcelService;
    private final ProductRepository productRepository;

  /**-----------------------------------------------------------------------//
                  //--using Servlet Response--//
  **/

    @GetMapping("/Excel")
    public void getExcelFormat(HttpServletResponse response) throws IOException {
        response.setContentType("APPLICATION_OCTET_STREAM");
        //String filename="Product.xlsx";
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Product"+ ".xlsx";
        response.setHeader(headerKey, headerValue);
        exportExcelService.getExcelFormat(response);

    }
    @GetMapping("/CsvFile")
    public void getCsvFile(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        String filename="Product.csv";
        String headerKey="content-Disposition";
        String headerValue="attachment;filename"+filename;
        response.setHeader(headerKey,headerValue);
        exportExcelService.getCsvFile(response);
    }

  /**---------------------------------------------------------------------------//
                           //--using Content type--//
  **/
    @Autowired
  private CSVMethod2 csvGeneratorUtil;
    @GetMapping("/csv")
    public ResponseEntity<byte[]> generateCsvFile() {
        List<Product> productList =productRepository.findAll();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "ProductCsv.csv");

        byte[] csvBytes = csvGeneratorUtil.generateCsv(productList).getBytes();

        return new ResponseEntity<>(csvBytes, headers, HttpStatus.OK);
    }
}
