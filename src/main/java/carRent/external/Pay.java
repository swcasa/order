package carRent.external;

public class Pay {

    private Long id;
    private Long carId;
    private Long orderId;
    private String stauts;
    private Integer qty;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCarId() {
        return carId;
    }
    public void setCarId(Long carId) {
        this.carId = carId;
    }
    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getStauts() {
        return stauts;
    }
    public void setStauts(String stauts) {
        this.stauts = stauts;
    }
    public Integer getQty() {
        return qty;
    }
    public void setQty(Integer qty) {
        this.qty = qty;
    }

}
