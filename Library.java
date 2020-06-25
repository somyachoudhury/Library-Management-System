import java.util.Scanner;

 class Author {
	private String name;
	private String email;
	private char gender;
	Author(String name,String email,char gender)
	{
		this.name = name;
		this.setEmail(email);
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public char getGender() {
		return gender;
	}
	public String toString()
	{
		String s = "Author[name="+name+",email="+email+",gender="+gender+"]";
		return s;
	}
	

}
 class Book 
{
	private String name;
	private Author author;
	private double price;
	private int qty;
	Book(String name,Author author,double price)
	{
		this.name = name;
		this.author = author;
		this.setPrice(price);
	}
	Book(String name,Author author,double price,int qty)
	{
		this.name = name;
		this.author = author;
		this.setPrice(price);
		this.setQty(qty);
	}
	public String getName() {
		return name;
	}
	public Author getAuthor() {
		return author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String toString()
	{
		String s = "Book[name="+name+","+author.toString()+",price="+price+",qty="+qty;
		return s;
	}

}
 class Date {
	private int dd,mm,yyyy;

	Date(int dd, int mm, int yyyy) {
		this.dd = dd;
		this.mm = mm;
		this.yyyy = yyyy;
	}

	public int getDD() {
		return dd;
	}

	public int getMM() {
		return mm;
	}

	public int getYYYY() {
		return yyyy;
	}
	
	public String toString()
	{
		String s = "["+dd+":"+mm+":"+yyyy+"]"; 
		return s;
	}
	

}

class Student {
	private String name;
	private int roll;
	private Date issueDate;
	private Date returnDate;
	private int noOfBooks ;
	private Book[] bookIssued;
	private int fine;
	Scanner sc = new Scanner (System.in);
	Student(String name, int roll)
	{
		this.name = name;
		this.roll = roll;
		bookIssued = new Book[5];
		noOfBooks = 0;
		fine = 0;
	}
	public String getName() {
		return name;
	}
	public int getRoll() {
		return roll;
	}
	public String toString()
	{
		String s = "Student[name="+name+",	roll="+roll+
				"\nLast Book Return Date="+returnDate+"\nLast Book Issue Date="+issueDate+
				"\nFine = Rs"+fine+"\nNo. of books issued : "+noOfBooks+"]";
		return s;
	}
	public void issueBook(Book[] book)
	{
		if(noOfBooks >= 5)
		{
			System.out.println("no more books will be issued to you");
			return;
		}
		for(int i=0;i<book.length;i++)
			System.out.println((i+1)+". "+book[i]);
		System.out.println();
		System.out.print("enter book no. which you want to issue : ");
		int x = sc.nextInt();
		x--;
		if(book[x].getQty() < 1) {
			System.out.println("book not Available");
			return;
		}
		else
		{				
			System.out.println("Enter today's date dd, mm, yyyy : ");
			int d = sc.nextInt();
			int m = sc.nextInt();
			int y = sc.nextInt();
			Date d1 = new Date(d,m,y);
			issueDate = d1;
			System.out.println("Issue Date : "+issueDate);
			returnDate = getReturnDate(d1);
			book[x].setQty(book[x].getQty()-1);
			bookIssued[noOfBooks] = book[x];
			noOfBooks++;
			System.out.println("Book issued !");
			System.out.println("Return Date : "+returnDate);
//			return book;
		}
		
	}
	public void depositBook(Book books[])
	{
		if(noOfBooks == 0)
			return;
		for(int i=0;i<books.length;i++)
			System.out.println((i+1)+". "+books[i]);
		System.out.println();
		System.out.print("enter book no. which you want to deposit : ");
		int c = sc.nextInt();
		c--;
		Book book = books[c];
		boolean isTrue = false;
		for(int i=0;i<bookIssued.length;i++)
			if(book == bookIssued[i])
				isTrue = true;
		if(!isTrue)
		{
			System.out.println("This book was not issued by you !!");
			return;
		}
		int x=0;
		System.out.println("Enter today's date dd, mm, yyyy : ");
		int d = sc.nextInt();
		int m = sc.nextInt();
		int y = sc.nextInt();
		Date d1 = new Date(d,m,y);
		for(int i=0;i<books.length;i++)
			if(books[i]==book)
			{
				x=i;
				break;
			}
		
		books[x].setQty(book.getQty()+1);
		int diff = dateDiff(d1,returnDate) ;
		noOfBooks--;
		if(diff>0)
			fine +=diff;
		System.out.println("Book Deposited !!");
		System.out.println("Total Fine : Rs"+fine);
		
	}
	
	
	public Date getReturnDate(Date id)
	{
		Date rd;
		final int x=10;
		final int d1 = id.getDD(), m1 = id.getMM(), y1 = id.getYYYY();
		int	d2 = d1,m2 = m1,y2 = y1;

		boolean leap;
		if(y1 % 4 == 0)
			if( y1 % 100 == 0)
                if ( y1 % 400 == 0)
                    leap = true;
                else
                    leap = false;
            else
                leap = true;
        else
            leap = false;
		
		if(m1 == 1 || m1 == 3 || m1 == 5 || m1 == 7 || m1 == 8 || m1 == 10 )
		{
			if((d1+x)>31) {
				m2++;
				d2 = d1+x-31;
			}
			else
			{
				d2 = d1+x;
			}
		}
		else if(m1 == 2)
		{
			if(leap)
				if((d1+x)>29) {
					m2++;
					d2 = d1+x-29;
				}
				else
				{
					d2 = d1+x;
				}
			else
				if((d1+x)>28) {
					m2++;
					d2 = d1+x-28;
				}
				else
				{
					d2 = d1+x;
				}
		}
		else if(m1 == 12)
			if((d1+x)>31) {
				m2 = 1;
				y2 = y1+1;
				d2 = d1+x-31;
			}
			else
			{
				d2 = d1+x;
			}
		else
			if((d1+x)>30) {
				m2++;
				d2 = d1+x-30;
			}
			else
			{
				d2 = d1+x;
			}
		rd = new Date(d2,m2,y2);
		return rd;
	}
	public int dateDiff(Date d1,Date d2)
	{
		if(d1.getDD()<=d2.getDD() && d1.getMM()<=d2.getMM() && d1.getYYYY()<=d2.getYYYY())
			return -1;
		//assume that month has 30 days and year has 365 days
		int x1 = d1.getDD()-d2.getDD(); 
		int m1 = d1.getMM()-d2.getMM();
		int y1 = d1.getYYYY()-d2.getYYYY();
		return (x1+(m1*30)+(y1*365));
	}
	
}




public class Test 
{

	public static void main(String[] args) 
	{
		
				Scanner sc = new Scanner (System.in);
				Book book[] = new Book[10];
				String bname[] = {"Mein kampf","The blue umbrella","Harry potter","Thanos","Ram","Thor","Ganzo Art"};
				String aname[] = {"Adolf Hitler","Ruskin Bond","J.K Rowling","Josh Brolin","Amish Tripathi","Chris Hamsworth","Gaurav hossain "};
				String aemail[] = {"Adhitler@email.com","ruskinbonds@email.com","jkr@email.com","joshile@email.com","TripathiAmish@email.com","MarkRoffelo@email.com","ChrisHamsworth@email.com","gapuhossain@gmail.com"};
				char g = 'm';
				double price = 250.00;
				int qty = 1;
				Author author[] = new Author[7];
				for(int i=0;i<7;i++)
				{
					author[i] = new Author(aname[i],aemail[i],g);
					book[i] = new Book(bname[i],author[i],price,qty);
								
				}
				String sname[] = {"sohan","ayush","roonie","gaurav kumar","vineet v panda","Ayush Kejariwal","Steve Rogers","Tony Stark","Peter Parker","Natasha Romanoff","Dr. Banner","Thor Odenson","Clint Barton","T Challa","Scott Lang"};
				System.out.print("Enter no. of students : ");
				int n = sc.nextInt();
				Student student[] = new Student[n];
				
				for(int i=0;i<n;i++)
				{
					int y = (int)(Math.random()*10);
					student[i] = new Student(sname[y],(i+1));
//					System.out.println(i);
					
				}
				System.out.print("Enter roll no. : ");
				int roll =sc.nextInt();
				int x =-1;
				for(int i=0;i<n;i++) {
					if(student[i].getRoll() == roll) {
						System.out.println();
						x=i;
						System.out.println(student[i]);
					}
				}
				if(x==-1){
					System.out.println("sorry no data found");
					System.exit(0);
				}
				while(true)
				{
					System.out.print("\nenter 1 to issue & 2 to deposit a book\n or press 0 to for another student : ");
					int choice = sc.nextInt();
					if(choice == 1)
						student[x].issueBook(book);
					else if(choice == 2)
						student[x].depositBook(book);
					else if(choice == 0)
					{
						System.out.print("Enter roll no. : ");
						roll =sc.nextInt();
						x =-1;
						for(int i=0;i<10;i++)
							if(student[i].getRoll() == roll) {
								x=i;
								System.out.println(student[i]);
							}
						if(x==-1){
							System.out.println("sorry no data found");
							System.exit(0);
						}
					}
					else
					{
						System.out.println("thank you");
						System.exit(0);
					}
					}
				
			}

		




	}


