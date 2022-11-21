package th.prior.training.car.showroom.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "buyer_order")
@Data
public class BuyerOrderEntity {
    @Id
    @Column(name = "order_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer orderId;
    @Column(name="buyer_name")
    private String buyerName;
    @Column(name="order_status")
    private String orderStatus;
    @Column(name="order_car_type")
    private String orderCarType;
    @Column(name="order_date")
    private LocalDateTime orderDate;
    @Column(name="is_active")
    private String isActive;

}