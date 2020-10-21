
package carRent.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="payment", url="http://payment:8080")
public interface PayService {

    @RequestMapping(method= RequestMethod.POST, path="/pays")
    public void payreq(@RequestBody Pay pay);

}