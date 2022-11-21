package th.prior.training.car.showroom.component.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import th.prior.training.car.showroom.entity.BuyerOrderEntity;
import th.prior.training.car.showroom.model.ProducibleRequestModel;

@Component
public class TransformProducibleReqComponent implements BaseTransformComponent<ProducibleRequestModel, BuyerOrderEntity> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ProducibleRequestModel transFormEntityToModel(BuyerOrderEntity buyerOrderEntity) {
        return null;
    }

    @Override
    public BuyerOrderEntity transFormModelToEntity(ProducibleRequestModel requestModel) {
        return null;
    }

    @Override
    public void updateEntityByModel(BuyerOrderEntity buyerOrderEntity, ProducibleRequestModel requestModel) {

    }

    @Override
    public String modelToJson(ProducibleRequestModel requestModel) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(requestModel);
    }

    @Override
    public ProducibleRequestModel jsonToModel(String json) {
        return null;
    }
}
