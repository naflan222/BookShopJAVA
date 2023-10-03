import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BookShop {

    private String booName;
    private String booAuthor;
    private String booPrice;
    private String booPublisher;

public static void main(String[] args) throws IOException {
    Scanner strInput = new Scanner(System.in);

    // User login
    boolean loggedIn = false;
    while (!loggedIn) {
        System.out.print("Enter username: ");
        String username = strInput.nextLine();
        System.out.print("Enter password: ");
        String password = strInput.nextLine();

        if (username.equals("admin") && password.equals("admin123")) {
            loggedIn = true;
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    String choice, cont = "y";

    while (cont.equalsIgnoreCase("y")) {
        System.out.println("_____________________________________________________________________________________________________________");
        System.out.println("\n\n\t\t Welcome to City Bookshop\n\n"); // <-- new heading
        System.out.println("_____________________________________________________________________________________________________________\n\n");

        System.out.println("0 ========================> Add users to System ");
        System.out.print("\n\n");
        System.out.println("1 ========================> Add New Book Record ");
        System.out.print("\n\n");
        System.out.println("2 ========================> View All Books Record ");
        System.out.print("\n\n");
        System.out.println("3 ========================> Delete Book Record ");
        System.out.print("\n\n");
        System.out.println("4 ========================> Search Book Record ");
        System.out.print("\n\n");
        System.out.println("5 ========================> Update Book Record ");
        System.out.print("\n\n");
        System.out.println("6 ========================> Logout");

        System.out.print("\n\n");
        System.out.println("Enter your choice: ");
        choice = strInput.nextLine();

        switch (choice) {
            case "0":
                BookShop.addNewUser();
                break;
            case "1":
                BookShop.AddRecord();
                break;
            case "2":
                BookShop.ViewAllRecord();
                break;
            case "3":
                BookShop.DeleteRecordByID();
                break;
            case "4":
                BookShop.SearchRecordbyID();
                break;
            case "5":
                BookShop.updateRecordbyID();
                break;
            case "6":
                loggedIn = false;
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
        if (loggedIn) {
        System.out.println("Do you want to continue? Y/N");
        cont = strInput.nextLine();
	} else {
            System.out.println("Logged out successfully.");
    }
}
}

public static void addNewUser() throws IOException {
    BufferedWriter bw = new BufferedWriter(new FileWriter("user_db.txt", true));
    Scanner strInput = new Scanner(System.in);

    String username, password;

    System.out.print("Enter the username: ");
    username = strInput.nextLine();
    System.out.print("Enter the password: ");
    password = strInput.nextLine();

    bw.write(username + "," + password);
    bw.newLine();
    bw.close();

    System.out.println("User added successfully!");
}


    public static void AddRecord() throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter("book_db.txt", true));
        Scanner strInput = new Scanner(System.in);

        String ID, name, price, author;

        System.out.print("Enter the Book ID: ");
        ID = strInput.nextLine();
        System.out.print("Enter the Book Name: ");
        name = strInput.nextLine();
        System.out.print("Enter the  Book Author: ");
        author = strInput.nextLine();
        System.out.print("Enter the Book Price: ");
        price = strInput.nextLine();

        bw.write(ID + "," + name + "," + author + "," + price);
        bw.flush();
        bw.newLine();
        bw.close();

    }

    public static void ViewAllRecord() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("book_db.txt"));

        String record;

        System.out.println(" ------------------------------------------------------------- ");
        System.out.println("|	ID		Name 				Author			Price 		  |");
        System.out.println(" ------------------------------------------------------------- ");

        while ((record = br.readLine()) != null) {

            StringTokenizer st = new StringTokenizer(record, ",");

            System.out.println("|	" + st.nextToken() + "	" + st.nextToken() + " 		" + st.nextToken() + "			" + st.nextToken() + "      |");

        }

        System.out.println("|	                                            	          |");
        System.out.println(" ------------------------------------------------------------- ");
        br.close();

    }

    public static void DeleteRecordByID() throws IOException {
        Scanner strInput = new Scanner(System.in);
        String ID, record;

        File tempDB = new File("book_db_temp.txt");
        File db = new File("book_db.txt");

        BufferedReader br = new BufferedReader(new FileReader(db));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));

        System.out.println("\t\t Delete Book Record\n");

        System.out.println("Enter the Book ID: ");
        ID = strInput.nextLine();

        while ((record = br.readLine()) != null) {

            if (record.contains(ID)) {
                continue;
            }

            bw.write(record);
            bw.flush();
            bw.newLine();

        }

        br.close();
        bw.close();

        db.delete();

        tempDB.renameTo(db);

    }

    public static void SearchRecordbyID() throws IOException {
        String ID, record;
        Scanner strInput = new Scanner(System.in);

        BufferedReader br = new BufferedReader(new FileReader("book_db.txt"));

        System.out.println("\t\t Search Book Record\n");

        System.out.println("Enter the Book ID: ");
        ID = strInput.nextLine();

        System.out.println(" ------------------------------------------------------------- ");
        System.out.println("|	ID		Name 				Author			Price 		  |");
        System.out.println(" ------------------------------------------------------------- ");

        while ((record = br.readLine()) != null) {

            StringTokenizer st = new StringTokenizer(record, ",");
            if (record.contains(ID)) {
                System.out.println("|	" + st.nextToken() + "	" + st.nextToken() + " 		" + st.nextToken() + "			" + st.nextToken() + "      |");
            }

        }

        System.out.println("|	                                            	          |");
        System.out.println(" ------------------------------------------------------------- ");

        br.close();

    }
    
public static void updateRecordbyID() throws IOException {
    		String newName, newPrice, newAuthor, record, ID,record2;
    		
    		File db = new File("book_db.txt");
    		File tempDB = new File("book_db_temp.txt");
    		
    		BufferedReader br = new BufferedReader( new FileReader(db) );
    		BufferedWriter bw = new BufferedWriter( new FileWriter(tempDB) );
    		    		
    		Scanner strInput = new Scanner(System.in);
    		
    		System.out.println("\t\t Update Book Record\n\n");   
		/****/		
			System.out.println("Enter the Book ID: ");
	    		ID = strInput.nextLine();	    		
	    		System.out.println(" ------------------------------------------------------------- ");
	    		System.out.println("|	ID		Name 				Author			Price 		  |");
	    		System.out.println(" ------------------------------------------------------------- ");
	    		
	    		while( ( record = br.readLine() ) != null ) {
	    			
	    			StringTokenizer st = new StringTokenizer(record,",");
	    			if( record.contains(ID) ) {
	    				System.out.println("|	"+st.nextToken()+"	"+st.nextToken()+" 		"+st.nextToken()+"			"+st.nextToken()+"      |");
	    			}
	    			
	    		}	    		
	    		System.out.println("|	                                            	          |");
	    		System.out.println(" ------------------------------------------------------------- ");
	    		
	    	br.close();
		/****/    	   
    		System.out.println("Enter the new Name: ");
    		newName = strInput.nextLine();    		
    		System.out.println("Enter the new Price: ");
    		newPrice = strInput.nextLine();  
    		System.out.println("Enter the new Author: ");
    		newAuthor = strInput.nextLine();  
    		
    		BufferedReader br2 = new BufferedReader( new FileReader(db) );
    			
    		while( (record2 = br2.readLine() ) != null ) {    			
    			if(record2.contains(ID)) {
    				bw.write(ID+","+newName+","+newPrice+","+newAuthor);
    			} else {
    			
    				bw.write(record2);	
    			}    			
    			bw.flush();
    			bw.newLine();
    		}
    		
    		bw.close();
    		br2.close();    		
    		db.delete();    		
    		boolean success = tempDB.renameTo(db);    		
    		System.out.println(success);    		
    		
    }

 
    public String getBooName() {
        return booName;
    }

    public void setBooName(String booName) {
        this.booName = booName;
    }

    public String getBooAuthor() {
        return booAuthor;
    }

    public void setBooAuthor(String booAuthor) {
        this.booAuthor = booAuthor;
    }

    public String getBooPrice() {
        return booPrice;
    }
    public void setBooPrice(String booPrice) {
        this.booPrice = booPrice;
    }
    public String getBooPublisher() {
        return booPublisher;
    }

    public void setBooPublisher(String booPublisher) {
        this.booPublisher = booPublisher;
    }
}

