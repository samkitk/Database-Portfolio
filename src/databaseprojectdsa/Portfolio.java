package databaseprojectdsa;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Portfolio extends DbObject{
    private String stockName,ownerName, industry;
    private double price;
    private int quantity;
    protected int ownerNameLen = 30, stockNameLen = 30, industryLen = 30, priceLen = 30, quantityLen = 30;
    protected int size = 2*ownerNameLen+2*stockNameLen+priceLen + quantityLen + 2*industryLen;
    
    public Portfolio() {
    }
    
    public Portfolio(String on, String sn, String in, double price, int quantity) {
        ownerName=on;
        stockName=sn;
        industry=in;
        this.price=price;
        this.quantity=quantity;
    }
    
    public boolean equals(Object d) {
	return ownerName.trim().equals(((Portfolio) d).ownerName.trim());
    }
    
    @Override
    public void writeToFile(RandomAccessFile out) throws IOException {
                writeString(ownerName + '\t', out);
		writeString(stockName + '\t', out);
		writeString(industry + '\t', out);
                writeString(Double.toString(price) + '\t',out);
		writeString(Integer.toString(quantity) + '\n', out);
    }
    
    @Override
    public void readFromFile(RandomAccessFile in) throws IOException {
		ownerName = readString(ownerNameLen, in);
		stockName = readString(stockNameLen, in);
		industry = readString(industryLen, in);
                //price = Double.parseDouble(readString(priceLen, in));
                //quantity = Integer.parseInt(readString(quantityLen, in));
		price = in.readDouble();
                quantity = in.readInt();
    }
    
    @Override
    public void readFromConsole() {
        System.out.print("Owner name: ");
        ownerName = kb.nextLine();
        System.out.print("Stock name: ");
        stockName = kb.nextLine();
        System.out.print("Industry name: ");
        industry = kb.nextLine();
        System.out.print("Stock price: ");
        price = kb.nextDouble();
        System.out.print("Quantity: ");
        quantity = kb.nextInt();
    }
    
    @Override
    public void writeLegibly() {
        System.out.println("Owner Name is: "+ownerName.trim());
        System.out.println("Stock Name is: "+stockName.trim());
        System.out.println("Industry Name is: "+industry.trim());
        System.out.println("Price is: "+price);
        System.out.println("Quantity is: "+quantity);
    }
    
    @Override
    public void readKey() {
        System.out.print("Enter Owner Name: ");
        ownerName = kb.nextLine();
        System.out.println("Comparing name: " + ownerName);
    }
    
    @Override
    public void copy(DbObject[] db) {
            db[0] = new Portfolio(ownerName, stockName, industry, price, quantity);
    }
    
    @Override
    public int size() {
        return size;
    }
}
