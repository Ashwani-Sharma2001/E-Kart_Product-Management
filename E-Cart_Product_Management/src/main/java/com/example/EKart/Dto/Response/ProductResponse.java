package com.example.EKart.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ResponseBody;


@Data
@ResponseBody
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private String Message;

}
