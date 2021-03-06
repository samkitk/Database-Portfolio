package databaseprojectdsa;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Portfolio extends DbObject {
    int temp = 1;
    private String stockName,ownerName, industry;
    private double price;
    private int quantity;
    protected int ownerNameLen = 30, stockNameLen = 30, industryLen = 30, priceLen = 8, quantityLen = 4;
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
                writeString(ownerName, out);
		writeString(stockName, out);
		writeString(industry, out);
                out.writeDouble(price);
                out.writeInt(quantity);
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
        if(temp > 1)
        {
            kb.next();
        }
        ownerName = kb.nextLine();
        for(int i = ownerName.length(); i< ownerNameLen; i++)
        {
            ownerName+=' ';
        }
        System.out.print("Stock name: ");
        stockName = kb.nextLine();
        for(int i = stockName.length(); i< stockNameLen; i++)
        {
            stockName+=' ';
        }
        System.out.print("Industry name: ");
        industry = kb.nextLine();
        for(int i = industry.length(); i< industryLen; i++)
        {
            industry+=' ';
        }
        System.out.print("Stock price: ");
        price = kb.nextDouble();
        System.out.print("Quantity: ");
        quantity = kb.nextInt();
        temp++;
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
