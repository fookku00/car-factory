package th.prior.training.car.showroom.component;

import org.springframework.stereotype.Component;
import th.prior.training.car.showroom.constant.AppConstant;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class DateTimeUtilsComponent {

    final DateTimeFormatter DD_MM_YYYY_H_M_S = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public LocalDateTime convertDateToLocalDateTime(Date date) {
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime;
    }

    public String convertLocalDateTimeToString (LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern(AppConstant.DD_MM_YYY_H_M_S_FORMAT);
        String dateTimeString =   dateTimeFormatter.format(localDateTime);
        return dateTimeString;
    }

    public LocalDateTime convertStringToLocalDateTime (String datetimeString){

        LocalDateTime localDateTime = LocalDateTime.from(DD_MM_YYYY_H_M_S.parse(datetimeString));
        return localDateTime;
    }

}
