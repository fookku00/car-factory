package th.prior.training.car.showroom.model;

import lombok.Data;

@Data
public class ResponseModel<T> {
    private Integer statusCode;
    private String description;
    private T data;
}
