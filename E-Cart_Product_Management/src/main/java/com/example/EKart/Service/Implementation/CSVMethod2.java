package com.example.EKart.Service.Implementation;

import com.example.EKart.Dao.Entity.Product;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class CSVMethod2 {
    private static final String CSV_HEADER = "SEQUENCE_IN,PRODUCT-NAME,PRODUCT-DESCRIPTION,PRODUCT-TYPE\n";

    public String generateCsv(List<Product> productList) {
        StringBuilder csvContent = new StringBuilder();
        csvContent.append(CSV_HEADER);
        for (Product product : productList) {
            csvContent.append(product.getProductCode()).append(",")
                    .append(product.getProductName()).append(",")
                            .append(product.getProductDescription()).append(",")
                    .append(product.getProductType()).append("\n");
        }
        return csvContent.toString();
    }
}
