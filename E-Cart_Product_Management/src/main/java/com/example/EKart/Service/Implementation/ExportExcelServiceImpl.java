package com.example.EKart.Service.Implementation;

import com.example.EKart.Dao.Entity.Product;
import com.example.EKart.Dao.Repository.ProductRepository;

import com.example.EKart.Dto.Response.AllResponse;
import com.example.EKart.Service.Interface.ExportExcelService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ExportExcelServiceImpl implements ExportExcelService {

    private final ProductRepository productRepository;

    @Override
    public void getExcelFormat(HttpServletResponse response) throws IOException {
        List<AllResponse> allResponses=new ArrayList<>();
        List<Product> productList=productRepository.findAll();
        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet sheet=workbook.createSheet("ProductExcel");
        HSSFRow row=sheet.createRow(0);

        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("PRODUCT-NAME");
        row.createCell(2).setCellValue("PRODUCT-DESCRIPTION");
        row.createCell(3).setCellValue("PRODUCT-TYPE");

        int Custom=1;
        for(Product product:productList){
            row= sheet.createRow(Custom);
            row.createCell(0).setCellValue(product.getProductCode());
            row.createCell(1).setCellValue(product.getProductName());
            row.createCell(2).setCellValue(product.getProductDescription());
            row.createCell(3).setCellValue(product.getProductType());
            Custom++;
        }
        ServletOutputStream manjubhai= response.getOutputStream();
        workbook.write(manjubhai);
        workbook.close();
        manjubhai.close();
    }

    @Override
    public void getCsvFile(HttpServletResponse response) throws IOException {

        List<Product> productList = productRepository.findAll();
          ICsvBeanWriter csvBeanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

          String[] csvHeader = {"SEQUENCE_IN", "PRODUCT-NAME", "PRODUCT-DESCRIPTION", "PRODUCT-TYPE"};
          String[] nameMapping = { "ProductCode","ProductName", "ProductDescription", "ProductType"};
          csvBeanWriter.writeHeader(csvHeader);

          for (Product product : productList) {
              csvBeanWriter.write(product, nameMapping);
          }
         csvBeanWriter.close();
    }

}
