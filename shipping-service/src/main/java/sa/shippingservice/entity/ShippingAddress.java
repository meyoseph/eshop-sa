package sa.shippingservice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class ShippingAddress {
    @Id
    @GeneratedValue
    private Long shippingAddressId;
    private String address;
    private Long userId;
}
