package carRent;

import carRent.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }
    @Autowired
    OrderRepository orderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaid_Status(@Payload Paid paid){

        if(paid.isMe()){
            System.out.println("##### listener Status : " + paid.toJson());

            Optional<Order> orderOptional = orderRepository.findById(paid.getOrderId());
            Order order = orderOptional.get();
            order.setStatus(paid.getStatus());

            System.out.println("##### payment id : " + paid.getId());

            orderRepository.save(order);
        }
    }

}
