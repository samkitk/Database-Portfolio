package databaseprojectdsa;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;


public class Portfolio extends DbObject{
    Scanner kb = new Scanner(System.in);
    
    public Portfolio() {
        //Surpriseddd!!!. Its empty.
    }
    
    public Portfolio(String on, String sn, String in, String price, String quantity) {
        ownerName=on;
        stockName=sn;
        industry=in;
        this.price=price;
        this.quantity=quantity;
    }
    private String stockName,ownerName, industry;
    private String price;
    private String quantity;
    protected int ownerNameLen = 30, stockNameLen = 30, industryLen = 30, priceLen = 8, quantityLen = 4;
    protected int size = 2*ownerNameLen+2*stockNameLen+priceLen + quantityLen + 2*industryLen;
    
    @Override
    public void writeToFile(RandomAccessFile out) throws IOException {
                writeString(ownerName + ' ', out);
		writeString(stockName + ' ', out);
		writeString(industry + ' ', out);
                writeString(price + ' ',out);
		writeString(quantity + '\n', out);
    }
    
    @Override
    public void readFromFile(RandomAccessFile in) throws IOException {
		ownerName = readString(ownerNameLen, in);
		stockName = readString(stockNameLen, in);
		industry = readString(industryLen, in);
                price = readString(priceLen, in);
                quantity = readString(quantityLen, in);
//		price = in.readDouble();
//              quantity = in.readInt();
    }
    
    @Override
    public void readFromConsole() {
        System.out.println("Owner name: ");
        ownerName = kb.nextLine();
        System.out.println("Stock name: ");
        stockName = kb.nextLine();
        System.out.println("Industry name: ");
        industry = kb.nextLine();
        System.out.println("Stock price");
        price = kb.nextLine();
        System.out.println("Quantity: ");
        quantity = kb.nextLine();
    }
    
    @Override
    public void writeLegibly() {
        System.out.println("Owner Name is: "+ownerName);
        System.out.println("Stock Name is: "+ownerName);
        System.out.println("Industry Name is: "+ownerName);
        System.out.println("Price is: "+ownerName);
        System.out.println("Quantity is: "+ownerName);
    }
    
    @Override
    public void readKey() {
        System.out.println("Enter Owner Name: ");
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
