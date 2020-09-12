package databaseprojectdsa;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Tree extends DbObject {
	
	private String name;
	protected int nameLen = 30;
	
	private String latinName;
	protected int latinNameLen = 30;
	
	private int maxheight;
	protected int maxheightLen=4;
	
	private String habitat;
	protected int habitatLen=30;
	
	private String uses;
	protected int usesLen=30;
	
	protected int size = 2*nameLen+2*latinNameLen+maxheightLen + 2*habitatLen+2*usesLen;
	//the length of string is counted twice because of writing into file as array
	
	public Tree() {	
	}

	public Tree(String s1, String s2, int i, String s3, String s4) {
		name=s1;
		latinName=s2;
		maxheight=i;
		habitat=s3;
		uses=s4;
	}
	
	public boolean equals(Object d) {
		
		return name.trim().equals(((Tree) d).name.trim());
	}
        
	@Override
	public void writeToFile(RandomAccessFile out) throws IOException {
		writeString(name, out);
		writeString(latinName, out);
		out.writeInt(maxheight);
		writeString(habitat,out);
		writeString(uses,out);
		
	}

	@Override
	public void readFromFile(RandomAccessFile in) throws IOException {
		name = readString(nameLen, in);
		latinName = readString(latinNameLen, in);
		maxheight = in.readInt();
		habitat=readString(habitatLen, in);
		uses = readString(usesLen, in);

	}

	@Override
	public void readFromConsole() {
		
		System.out.print("Enter name: ");
		name=kb.nextLine();
		
		for(int i = name.length(); i< nameLen; i++)
			name+=' ';

		System.out.print("Latin name: ");
		latinName= kb.nextLine();
		for(int i = latinName.length(); i< latinNameLen; i++)
			latinName+=' ';
		
		System.out.print("Max height: ");
		maxheight = kb.nextInt();
		
		//System.out.print("Here is error: " + kb.next());
		
		System.out.print("Habitat: ");
		habitat=kb.nextLine();

		for(int i = habitat.length(); i< habitatLen; i++)
			habitat+=' ';
		
		System.out.print("Uses: ");
		uses=kb.nextLine();

		for(int i = uses.length(); i< usesLen; i++)
			uses+=' ';
		
		
		}
		
	

	@Override
	public void writeLegibly() {
		System.out.print("The tree name is  " + name.trim() + "the latin name is " + latinName.trim() + " it can reach " + maxheight + " meters, it lives in " + habitat.trim() + " it is used for " + uses.trim() + ". ");
	}

	@Override
	public void readKey() {
		System.out.println("Enter name: ");
		kb.useDelimiter("\r\n");
		name= kb.nextLine();
System.out.println("Comparing name: " + name);
	}

	@Override
	public void copy(DbObject[] db) {
		db[0] = new Tree(name, latinName, maxheight, habitat, uses);

	}

	@Override
	public int size() {
		
		return size;
	}

}
