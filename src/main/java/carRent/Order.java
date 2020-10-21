package carRent;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long catId;
    private String status;
    private Integer qty;

    @PostUpdate
    public void onPostUpdate(){
        if(this.getStatus().equals("CANCELLED")) {
            OrderCanceled orderCanceled = new OrderCanceled();
            BeanUtils.copyProperties(this, orderCanceled);
            orderCanceled.publishAfterCommit();
        }
    }

    @PrePersist
    public void onPrePersist(){

        try {
            Thread.currentThread().sleep((long) (800 + Math.random() * 220));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Ordered ordered = new Ordered();
        BeanUtils.copyProperties(this, ordered);
        ordered.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        carRent.external.Pay pay = new carRent.external.Pay();
        pay.setOrderId(ordered.getId());
        pay.setQty(ordered.getQty());
        pay.setCarId(ordered.getCarId());
        System.out.println("##### 오더아이디 어디감 : " + ordered.getId());
        pay.setStauts("APPROVED");

        // mappings goes here
        OrderApplication.applicationContext.getBean(carRent.external.PayService.class)
            .payreq(pay);


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }




}
