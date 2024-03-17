package com.example.EKart.Service.Interface;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public interface ExportExcelService {

    public void getExcelFormat(HttpServletResponse response) throws IOException;

    public void getCsvFile(HttpServletResponse response) throws IOException;
}
