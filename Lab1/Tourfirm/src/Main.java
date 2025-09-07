class Sale {
    private Integer saleId;
    private Integer clientId;
    private Integer tourId;
    private String saleDate;
    private double basePrice;
    private double discount;
    private double finalPrice;

    public Sale() {
    }
    public Integer getSaleId(){
        return saleId;
    }
    public Integer getClientId(){
        return clientId;
    }
    public Integer getTourId(){
        return tourId;
    }
    public String getSaleDate (){
        return saleDate;
    }
    public double getBasePrice() {
        return basePrice;
    }

    public double getDiscount() {
        return discount;
    }

    public double getFinalPrice() {
        return finalPrice;
    }



    public void setSaleId(Integer saleId)
    {
        this.saleId = saleId;
    }
    public void setClientId(Integer clientId)
    {
        if (clientId == null || clientId <= 0) {
            throw new IllegalArgumentException("ID клиента должно быть положительным");}
        this.clientId = clientId;
    }
    public void setTourId(Integer tourId) {
        if (tourId == null || tourId <= 0) {
            throw new IllegalArgumentException("ID тура должно быть положительным числом");
        }
        this.tourId = tourId;
    }

    public void setSaleDate(String saleDate) {
        if (saleDate == null || saleDate.isEmpty()) {
            throw new IllegalArgumentException("Дата продажи не может быть пустой");
        }
        this.saleDate = saleDate;
    }
    public void setBasePrice(double basePrice) {
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Стоимость должна быть положительным числом");
        }
        this.basePrice = basePrice;
        calculateFinalPrice();
    }
    public void setDiscount(double discount) {
        if (discount <= 0) {
            throw new IllegalArgumentException("Стоимость должна быть положительным числом");
        }
        this.discount = discount;
        calculateFinalPrice();
    }

    private void calculateFinalPrice() {
        double result = getBasePrice() - getDiscount();
        if (result < 0) {
            result = 0.0;
        }
        this.finalPrice = result;
    }
}
public class Main {
    public static void main(String[] args) {
        Sale sale = new Sale();
        sale.setClientId(123);
        sale.setTourId(456);
        sale.setSaleDate("2025-12-12");
        sale.setBasePrice(1000.00);
        sale.setDiscount(100.00);

        System.out.println("Итоговая цена: " + sale.getFinalPrice());
    }
}

