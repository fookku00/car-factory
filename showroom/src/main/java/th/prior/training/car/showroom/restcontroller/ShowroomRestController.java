package th.prior.training.car.showroom.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import th.prior.training.car.showroom.model.ProducibleRequestModel;
import th.prior.training.car.showroom.model.ResponseModel;
import th.prior.training.car.showroom.repository.service.ActivateFactoryService;

@RestController
@RequestMapping("/api")
public class ShowroomRestController {

    @Autowired
    private ActivateFactoryService activateFactoryService;

    @PostMapping("/request/car")
    public ResponseModel<Void> requestCarByCustomer(@RequestBody ProducibleRequestModel request) {

        ResponseModel<Void> result = activateFactoryService.callFactoryToCheckAmountParts(request);

        return result;
    }

    @PostMapping("/decide/produce")
    public ResponseModel<String> activateFactoryToProduce(@RequestBody Boolean request) {

        ResponseModel<String> result = activateFactoryService.activeFactoryToProduce(request);

        return result;
    }


}
