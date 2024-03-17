package com.example.EKart.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ResponseBody;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ResponseBody
public class UpdateResponse {
    private String Message;
}
