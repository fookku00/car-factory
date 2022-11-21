package th.prior.training.car.showroom.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CarTypeModel {
    private Integer typeId ;
    private String typeGroup;
    private String typePart;
    private Integer type_amount;
    private LocalDateTime type_create_date;
    private String is_active;
}
