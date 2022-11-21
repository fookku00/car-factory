package th.prior.training.car.showroom.model;

import lombok.Data;

@Data
public class ProducibleRequestModel {
    private String buyerName;
    private String orderStatus;
    private String orderCarType;
    private String isActive;
}
