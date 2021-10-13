package miu.edu.sa.orderservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long productId;
    private String name;
    private String vendor;
    private Integer quantity;
    private String category;
}
