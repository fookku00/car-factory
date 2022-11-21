package th.prior.training.car.showroom.repository.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import th.prior.training.car.showroom.component.utils.TransformProducibleReqComponent;
import th.prior.training.car.showroom.entity.BuyerOrderEntity;
import th.prior.training.car.showroom.kafka.component.KafkaProducerComponent;
import th.prior.training.car.showroom.model.ProducibleRequestModel;
import th.prior.training.car.showroom.model.ResponseModel;
import th.prior.training.car.showroom.repository.BuyerOrderRepository;

import java.time.LocalDateTime;

@Service
@Slf4j
public class ActivateFactoryService {

    private BuyerOrderRepository buyerOrderRepository;

    private KafkaProducerComponent kafkaProducerComponent;

    private TransformProducibleReqComponent transformProducibleReqComponent;

    @Value("${app.kafka.topics.is-ready}")
    private String isReadyTopic;

    public ActivateFactoryService(BuyerOrderRepository buyerOrderRepository, KafkaProducerComponent kafkaProducerComponent, TransformProducibleReqComponent transformProducibleReqComponent) {
        this.buyerOrderRepository = buyerOrderRepository;
        this.kafkaProducerComponent = kafkaProducerComponent;
        this.transformProducibleReqComponent = transformProducibleReqComponent;
    }

    public ResponseModel<Void> callFactoryToCheckAmountParts(ProducibleRequestModel request){
        log.info("checkAmountParts() request : {} ",request);

        ResponseModel<Void> result = new ResponseModel<>();
        result.setStatusCode(500);
        LocalDateTime localDate = LocalDateTime.now();
        try {
            //first insert into buyer_order for create order
            BuyerOrderEntity buyerOrderEntity = new BuyerOrderEntity();
            buyerOrderEntity.setBuyerName(request.getBuyerName());
            buyerOrderEntity.setOrderStatus(request.getOrderStatus());
            buyerOrderEntity.setOrderCarType(request.getOrderCarType());
            buyerOrderEntity.setOrderDate(localDate);
            buyerOrderEntity.setIsActive("Y");
            log.info("mapping entity for save to repository : {}", buyerOrderEntity);

            this.buyerOrderRepository.save(buyerOrderEntity);

            //call another API for checkAmountParts
            this.callFactoryWithKafka(request);
            result.setStatusCode(200);
            result.setDescription("send request to factory success");
            log.info("save to repository success : {}", buyerOrderEntity);

        }catch (Exception e) {
            result.setDescription("error in callFactoryToCheckAmountParts is : " + e.getMessage());
            result.setStatusCode(404);
            log.error("callFactoryToCheckAmountParts error is : {}", e.getMessage());
        }
        return result;
    }
    public ResponseModel<String> activeFactoryToProduce(Boolean request){
        log.info("activateFactoryToProduce() request : {} ",request);

        ResponseModel<String> responseModel = new ResponseModel<>();

        return responseModel;
    }

    private void callFactoryWithKafka(ProducibleRequestModel request) throws JsonProcessingException {
        try {
            this.kafkaProducerComponent
                    .send(this.transformProducibleReqComponent.modelToJson(request)
                            , this.isReadyTopic);
        }catch (Exception e){
            log.info("error at callFactoryWithKafka BuyerName : {} and error is :{}", request.getBuyerName(), e.getMessage());
        }
    }


}
