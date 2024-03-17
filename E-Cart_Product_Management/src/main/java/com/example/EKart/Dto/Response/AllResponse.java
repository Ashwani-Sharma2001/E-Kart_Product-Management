package com.example.EKart.Dto.Response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllResponse {
    @Getter
    private Integer productCode;
    private String productName;
    private String productDescription;
    private String productType;

    public AllResponse(String message, boolean b) {
    }

}
