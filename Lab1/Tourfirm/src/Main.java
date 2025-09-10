import org.json.JSONObject;
import java.util.Objects;
class ShortSaleClass
{
    private Integer saleId;
    private Integer clientId;
    private Integer tourId;
    private double finalPrice;


    public ShortSaleClass(Integer saleId, Integer clientId, Integer tourId, double finalPrice)
    {
        this.saleId = saleId;
        this.clientId = clientId;
        this.tourId = tourId;
        this.finalPrice = finalPrice;
    }


    public Integer getSaleId()
    {
        return saleId;
    }
    public  Integer getClientId()
    {
        return clientId;
    }
    public Integer getTourId()
    {
        return tourId;
    }
    public Double getFinalPrice()
    {
        return finalPrice;
    }
    public String getShortInfo() {
        return "Номер продажи: " + saleId +
                ", клиент: " + clientId +
                ", тур: " + tourId +
                ", итого: " + finalPrice + " руб.";
    }


}
class Sale {
    private Integer saleId;
    private Integer clientId;
    private Integer tourId;
    private String saleDate;
    private double basePrice;
    private double discount;
    private double finalPrice;



    public static boolean isValidId(Integer id)
    {
        return id != null && id>0;
    }

    public static boolean isValidDate(String date)
    {
        return date!=null && !date.isEmpty();
    }

    public static boolean isValidPrice(double price)
    {
        return price>0;
    }

    public static boolean isValidDiscount(double discount)
    {
        return discount>=0;
    }




    
    public Sale() {
    }
    public Sale(Integer clientId, Integer tourId, String saleDate, double basePrice,double discount)
    {
        setClientId(clientId);
        setTourId(tourId);
        setSaleDate(saleDate);
        setBasePrice(basePrice);
        setDiscount(discount);
    }


    public Sale(String data) {
        if (data.trim().startsWith("{")) {
            JSONObject json = new JSONObject(data);
            setClientId(json.getInt("clientId"));
            setTourId(json.getInt("tourId"));
            setSaleDate(json.getString("saleDate"));
            setBasePrice(json.getDouble("basePrice"));
            setDiscount(json.getDouble("discount"));
        } else {
            String[] parts = data.split(",");
            setClientId(Integer.parseInt(parts[0].trim()));
            setTourId(Integer.parseInt(parts[1].trim()));
            setSaleDate(parts[2].trim());
            setBasePrice(Double.parseDouble(parts[3].trim()));
            setDiscount(Double.parseDouble(parts[4].trim()));
        }
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
        if (!isValidId(clientId)) {
            throw new IllegalArgumentException("ID клиента должно быть положительным");}
        this.clientId = clientId;
    }
    public void setTourId(Integer tourId) {
        if (!isValidId(tourId)) {
            throw new IllegalArgumentException("ID тура должно быть положительным числом");
        }
        this.tourId = tourId;
    }

    public void setSaleDate(String saleDate) {
        if (!isValidDate(saleDate)) {
            throw new IllegalArgumentException("Дата продажи не может быть пустой");
        }
        this.saleDate = saleDate;
    }
    public void setBasePrice(double basePrice) {
        if (!isValidPrice(basePrice)) {
            throw new IllegalArgumentException("Стоимость должна быть положительным числом");
        }
        this.basePrice = basePrice;
        calculateFinalPrice();
    }
    public void setDiscount(double discount) {
        if (!isValidDiscount(discount)) {
            throw new IllegalArgumentException("Скидка должна быть положительным числом");
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



     public String getLongInfo() {
        return "Номер продажи: " + saleId +
                ", клиент: " + clientId +
                ", тур: " + tourId +
                ", дата: " + saleDate + '\'' +
                ", базовая цена: " + basePrice +
                ", скидка: " + discount +
                ", конечная цена: " + finalPrice +
                '}';
    }
    public String getShortInfo() {
        return "Номер продажи: " + saleId +
                ", клиент: " + clientId +
                ", тур: " + tourId +
                ", итого: " + finalPrice + " руб.";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Sale)) return false;
        Sale objSale = (Sale) obj;
        return Objects.equals(this.clientId, objSale.clientId) &&
                Objects.equals(this.tourId, objSale.tourId) &&
                Objects.equals(this.saleDate, objSale.saleDate) &&
                this.basePrice == objSale.basePrice &&
                this.discount == objSale.discount;
    }

    public ShortSaleClass ShortInfo()
    {
        return new ShortSaleClass(saleId,clientId,tourId,finalPrice);
    }
    
}
public class Main {
     public static void main(String[] args) {
        Sale sale1 = new Sale(123, 456, "2024-12-12", 1000, 100);
        Sale sale2 = new Sale("123,456,2024-12-12,1000,100");
        String json = "{"
                + "\"clientId\": 123,"
                + "\"tourId\": 456,"
                + "\"saleDate\": \"2024-12-12\","
                + "\"basePrice\": 1500.0,"
                + "\"discount\": 100.0"
                + "}";
        Sale sale3 = new Sale(json);
        System.out.println("Итоговая цена: " + sale1.getFinalPrice());
        System.out.println("Итоговая цена: " + sale2.getFinalPrice());
        System.out.println("Итоговая цена: " + sale3.getFinalPrice());

        System.out.println("Полная версия объекта:");
        System.out.println(sale1.getLongInfo());
        System.out.println("Краткая версия объекта");
        System.out.println(sale1.getShortInfo());
        System.out.println(sale1.equals(sale2));
        ShortSaleClass ShortSale = sale3.ShortInfo();
        System.out.println("Краткая версия объекта вторая\n" + ShortSale.getShortInfo());
    }
}

