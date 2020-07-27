import java.io.*;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

// **************************************************************************************************************************************************

//ADMIN

abstract class AbstractClassForAdmin{
	abstract void addStudent();
	abstract void deleteStudent();
	abstract void updateStudent();
	abstract void displayStudent();
}

class AdminClass extends AbstractClassForAdmin{
	Scanner sc = new Scanner(System.in);
	void addStudent(){
		System.out.println();
		//System.out.println();
		try (FileWriter f = new FileWriter("students.txt", true); 
			BufferedWriter b = new BufferedWriter(f); 
			PrintWriter p = new PrintWriter(b);) { 
		String name, father_name;
		int roll, age;
		long phone;
		System.out.println("Enter Student's name: ");
		name = sc.next();
		System.out.println("Enter Father's name: ");
		father_name = sc.next();
		System.out.println("Enter Student's Age: ");
		age = sc.nextInt();
		System.out.println("Enter Phone number: (Not more than 10 digit)");
		phone = sc.nextLong();
		System.out.println("Enter Roll No. ");
		roll = sc.nextInt();
		int chk=0;

			try{
		        BufferedReader br= new BufferedReader(new FileReader("students.txt"));
		        String line;
		        while ((line = br.readLine()) !=null) {
			        String[] values = line.split(" ");
			        String x=values[0];
			        int n=Integer.parseInt(x);
			        if (n==roll)
			        	{
			        		System.out.println();
			        		System.out.println("Student is already exists with this roll number!! Try Again!!");
			        		System.out.println();
			        		chk=1;
			        		break;
			        	}
			        }
			        br.close();
				}
		    catch(IOException e){}

				if(chk==0){
					p.println(roll + " " + name + " " + father_name + " " + age + " " + phone + " " + "0" + " " + "0");
					System.out.println("			A new record of Student is added !!!");
					System.out.println();
					b.close();
					f.close();
					}
				else
					System.out.println(" Permission denied !!!  You again have to choose this option.");
			}
		catch (IOException i) { 
			i.printStackTrace(); }
	}


	void deleteStudent(){
		System.out.println();
		int r;
		System.out.println("Enter the roll number of a student whose record you want to delete: ");
		r=sc.nextInt();

		int chk=0;
      	try{
		        BufferedReader br= new BufferedReader(new FileReader("students.txt"));
		        String line;
		        while ((line = br.readLine()) !=null) {
			        String[] values = line.split(" ");
			        String x=values[0];
			        int n=Integer.parseInt(x);
			        if (n==r)
			        	{
			        		System.out.println();
			        		System.out.println();
			        		chk=1;
			        		break;
			        	}
			        }
			        br.close();
				}
		    catch(IOException e){}


        try{
        BufferedReader br= new BufferedReader(new FileReader("students.txt"));
      	BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt"));
        String line;
        while ((line = br.readLine()) !=null) {
	        String[] values = line.split(" ");
	        String x=values[0];
	        int n=Integer.parseInt(x);
	        if (values.length >= 3 && n!=r)
	              bw.write(values[0] + ' ' + values[1] + ' ' + values[2] + ' '+ values[3] +' '+ values[4] +' '+ values[5] +' '+ values[6] + '\n');
	        }
	        br.close();
	        bw.close();
	        
    	File obj=new File("students.txt");
		File obj1 =new File("data.txt");
		boolean z,q;
		//System.out.println("deleteStudent= "+obj.delete());
		q= obj.delete(); // firstly delete the student file after that it can be rename
    	if(!obj.exists())
			z=obj1.renameTo(obj);//now rename the data file as students.txt
		//System.out.println("exists= "+obj.exists());
    		//System.out.println(""+obj1.renameTo(obj));
		//System.out.println(""+obj1.getName()); this will show the name as a data file becoz only temprory name is changes physical is data.txt only
		}
      	catch(IOException e){}
      	System.out.println();
      	
		if(chk==1)
      	System.out.println("			A record of Student is deleted !!!");
      else
      	System.out.println("Student is not exists with this roll number!! Try Again!!");
      	
    }
    

	void updateStudent(){
		int r;
		System.out.println();
		System.out.println("Enter the roll number of a student whose record you want to update: ");
		r=sc.nextInt();

		int chk=0;
      	try{
		        BufferedReader br= new BufferedReader(new FileReader("students.txt"));
		        String line;
		        while ((line = br.readLine()) !=null) {
			        String[] values = line.split(" ");
			        String x=values[0];
			        int n=Integer.parseInt(x);
			        if (n==r)
			        	{
			        		System.out.println();
			        		System.out.println();
			        		chk=1;
			        		break;
			        	}
			        }
			        br.close();
				}
		    catch(IOException e){}

		    if(chk==0)
		    	System.out.println("Student with this roll is not exists... Please check the database before proceeding...");

		int f=0;
		try{
        BufferedReader br= new BufferedReader(new FileReader("students.txt"));
      	BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt"));
        String line;
        while ((line = br.readLine()) !=null) {
	        String[] values = line.split(" ");
	        String x=values[0];
	        int n;
	        n=Integer.parseInt(x);
	        int choice;
	        if(values.length >= 3 && n==r){
	        	f=1;
	        	try{
				TimeUnit.SECONDS.sleep(1);
				}
				catch(InterruptedException e){}
	        	System.out.println("What do you want to Update? ");
	        	System.out.println("1. Roll No. 	2. Student Name 	3. Father's Name 	4. Age 		5. Phone Number 	6. Exit");
	        	while(true){
	        		System.out.println();
	       			System.out.println("Want to update  choose from sbove options if not enter 6: ");
		        	choice=sc.nextInt();
		        	if(choice == 1){
		        		int new_roll;
		        		
		        		System.out.println("Enter new roll number for a student ");
		        		new_roll=sc.nextInt();
		        		String s=Integer.toString(new_roll);
		        		System.out.println("new roll "+ s);
		        		values[0]=s;
		        	}
		        	else if(choice == 2){
		        		String new_name;
		        		System.out.println("Enter new name for a student ");
		        		new_name=sc.next();
		        		values[1]=new_name;
		        	}
		        	else if(choice == 3){
		        		String new_father_name;
		        		System.out.println("Enter new Father's name for a student ");
		        		new_father_name=sc.next();
		        		values[2]=new_father_name;
		        	}
		        	if(choice == 4){
		        		int new_age;
		        		System.out.println("Enter new age for a student ");
		        		new_age=sc.nextInt();
		        		String s=Integer.toString(new_age);
		        		values[3]=s;
		        	}
		        	else if(choice ==5){
		        		long new_phone;
		        		System.out.println("Enter new phone number for a student ");
		        		new_phone=sc.nextLong();
		        		String s=Long.toString(new_phone);
		        		values[4]=s;
		        	}
		        	else if(choice == 6)
		        	{
		        		break;
		        	}
		        	else if(choice!=1 && choice!=2 && choice!=3 && choice!=4 && choice!=5 && choice!=6){
					System.out.println("Please enter valid choice");
						}

		        }
		        if(f==0)
		        	System.out.println("NO RECORD FOUND!! Check this student is present in database or not...");
		        System.out.println("");
		        bw.write( values[0] + ' ' + values[1] + ' ' + values[2] + ' '+ values[3] +' '+ values[4] + ' ' + values[5] + ' '+ values[6] + '\n');
		        System.out.println("Updated!!!!");

	        }
	        else if (values.length >= 3 && n!=r){
	            bw.write( values[0] + ' ' + values[1] + ' ' + values[2] + ' '+ values[3] +' '+ values[4] + ' ' + values[5] + ' '+ values[6] + '\n');
	        }
	    }
	        br.close();
	        bw.close();
	        File obj=new File("students.txt");
			File obj1 =new File("data.txt");
			boolean z,q;
			//System.out.println("obj ex: "+obj.exists());
			//System.out.println("obj1 ex: "+obj1.exists());
			q= obj.delete(); // firstly delete the student file after that it can be rename
			//System.out.println(""+obj.delete());
    		if(!obj.exists())
				z=obj1.renameTo(obj);//now rename the data file as students.txt
			//System.out.println("exists= "+obj.exists());
		}
      	catch(IOException e){}
      		
	}
	void displayStudent(){
		int r;
		System.out.println();
		System.out.println("Enter the roll number of a student whose record you want to view: ");
		r=sc.nextInt();
		int f=0;
		try{
	        BufferedReader br= new BufferedReader(new FileReader("students.txt"));
	        String line;
        	while ((line = br.readLine()) !=null) {
	        String[] values = line.split(" ");
	        String x=values[0];
	        int n;
	        n=Integer.parseInt(x);

	        if(values.length >= 3 && n==r){
	        	f=1;
	        	System.out.println("");
	        	System.out.println(values[1] + "'s Information ");
	        	System.out.println("**********************");
	        	System.out.println();
	        	System.out.println("	Name: 				"+values[1]);
	        	System.out.println("	Roll Number: 			"+values[0]);
	        	System.out.println("	Father's Name: 			"+values[2]);
	        	System.out.println("	Age: 				"+values[3]);
	        	System.out.println("	Phone Number: 			"+values[4]);
	        	System.out.println("	Current Marks: 			"+values[5]);
	        	System.out.println("	Attendance: 			"+values[6]);
	    	}
	    	}
	    	br.close();
	    }catch(Exception a){}
	    if(f==0)
	    	System.out.println("NO RECORD FOUND!! Check this student is present in database or not...");
	}
	void database(){
		System.out.println("				STUDENT DATABASE 				");
		int f=0;
		
		try{
			BufferedReader br= new BufferedReader(new FileReader("students.txt"));
			try{
				TimeUnit.SECONDS.sleep(1);
			}
			catch(InterruptedException e){}
			System.out.println("");
			System.out.println("	Student Name 		Roll Number 		Father's Name 		Age 		Current Marks 		Attendance		Phone Number");
			String line;
			
	        while ((line = br.readLine()) !=null) {
	        	f=1;
		        String[] values = line.split(" ");
		        String x=values[0];
		        System.out.println("	"+values[1] +"			"+ values[0] +"			"+ values[2] +"			"+ values[3] +"		"+ values[5] +"			"+ values[6]+"			"+ values[4]);
		    }
		    br.close();
	}catch(Exception e){}
	if(f==0)
		    System.out.println("NO DATA FOUND !! May be your Database is empty...");
	}
}

//*************************************************************************************************************************************************************************************************************
// FACULTY

class Faculty{
	Scanner sc=new Scanner(System.in);
	void upload(){
		
		try{
        BufferedReader br= new BufferedReader(new FileReader("students.txt"));
        String line;
        int f=0;
        System.out.println("");
        System.out.println("Present roll numbers are: ");
        while ((line = br.readLine()) !=null) {
        	String[] values = line.split(" ");
        	System.out.print("   "+ values[0]);
        	f=1;
        }
        System.out.println("");
        if(f==0)
        	System.out.println("NO DATA FOUND !!!  Make sure your data must fill with some records.");
        br.close();
        }
    	catch(IOException e){}

		try{
        BufferedReader br= new BufferedReader(new FileReader("students.txt"));
      	BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt"));
        String line;
       
       	int c=0;
        int r;
		System.out.println();
		System.out.println("Enter the roll number of a student whose marks/attendance you want to upload: ");
		r=sc.nextInt();

        while ((line = br.readLine()) !=null) {
	        String[] values = line.split(" ");
	        String x=values[0];
	        int n;
	        n=Integer.parseInt(x);

	        if(values.length >= 3 && n==r){
	        	System.out.println();
	        	while(true){
	        		System.out.println();
	        		try{
						TimeUnit.SECONDS.sleep(1);
					}
					catch(InterruptedException e){}
	        		System.out.println("What do you want to Upload? ");
	        		System.out.println("1. Marks 		2. Attendance 		3. Exit"); 	
	        		System.out.println();
	        		System.out.println("Enter your choice from the above mentioned: ");
	        	
		        	int choice;
		        	choice=sc.nextInt();
		        	if(choice == 1){
		        		int new_marks;
		        		float marks_float;
		        		System.out.println("Enter marks for a student ");
		        		marks_float=sc.nextFloat();
		        		new_marks=Math.round(marks_float);
		        		String m=values[5];
		        		float cgpa=Integer.parseInt(m);
		        		new_marks+=cgpa;
		        		String s=Integer.toString(new_marks);
		        		values[5]=s;
		        		c=1;
		        		System.out.println("Round Off values of marks will be stored in the database....");
		        	}
		        	else if(choice == 2){
		        		int new_att;
		        		System.out.println("Enter attendance for a student. Attendance ranges from 10% to 100% ");
		        		new_att=sc.nextInt();
		        		String s=Integer.toString(new_att);
		        		values[6]=s;
		        		c=1;
		        	}
		        	else if(choice == 3)
		        	{
		        		c=1;
		        		break;
		        	}
		        	else{
		        		c=1;
		        		System.out.println("Please enter valid choice");
		        	}
		        }
		        		        bw.write( values[0] + ' ' + values[1] + ' ' + values[2] + ' '+ values[3] +' '+ values[4] + ' ' + values[5] + ' '+ values[6] + '\n');
		        System.out.println("Uploaded!!!!    This will add to the existing marks/attendance of a student!!  ");
	        }
	        else if (values.length >= 3 && n!=r){		        
	            bw.write( values[0] + ' ' + values[1] + ' ' + values[2] + ' '+ values[3] +' '+ values[4] + ' ' + values[5] + ' '+ values[6] + '\n');
	        	        }
	    }
	    if(c==0)
	        	System.out.println(" NO RECORD FOUND !!! Kindly enter that roll number which is present in this database..");
	
	        br.close();
	        bw.close();
	        File obj=new File("students.txt");
			File obj1 =new File("data.txt");
			boolean z,q;
			//System.out.println("obj ex: "+obj.exists());
			//System.out.println("obj ex: "+obj1.exists());
			q= obj.delete(); // firstly delete the student file after that it can be rename
    		if(!obj.exists())
				z=obj1.renameTo(obj);//now rename the data file as students.txt
			//System.out.println("exists= "+obj.exists());
		}
      	catch(IOException e){
      		
      	}
	}


	void viewStudent(){
		System.out.println();
		System.out.println(" NOTE:  Faculty can only access basic information of the Students. To get access full access you have to take the premission of concerned admin of this Database!! ");
		int r;
		System.out.println();
		
		System.out.println("Enter the roll number of a student whose details you want to view: ");
		r=sc.nextInt();
		int f=0;
		try{
	        BufferedReader br= new BufferedReader(new FileReader("students.txt"));
	        String line;
        	while ((line = br.readLine()) !=null) {
	        String[] values = line.split(" ");
	        String x=values[0];
	        int n;
	        n=Integer.parseInt(x);

	        if(values.length >= 3 && n==r){
	        	f=1;
	        	try{
				TimeUnit.SECONDS.sleep(1);
				}
				catch(InterruptedException e){}
				System.out.println("");
	        	System.out.println(values[1] + "'s Information ");
	        	System.out.println("**********************");
	        	System.out.println();
	        	System.out.println("	Name: 				"+values[1]);
	        	System.out.println("	Roll Number: 			"+values[0]);
	        	//System.out.println("	Father's Name: 			"+values[2]);
	        	//System.out.println("	Age: 				"+values[3]);
	        	//System.out.println("	Phone Number: 			"+values[4]);
	        	System.out.println("	Current Marks: 			"+values[5]);
	        	System.out.println("	Attendance: 			"+values[6]);
	        	
	    	}

	    	}
	    		if(f==0)
	    		System.out.println("NO RECORD FOUND!! Check this student is present in database or not...");
	    	br.close();
	    }catch(Exception a){}
	}

	void AllStudents(){
		System.out.println("				STUDENT DATABASE 				");
		int f=0;
		try{
			BufferedReader br= new BufferedReader(new FileReader("students.txt"));
			try{
				TimeUnit.SECONDS.sleep(1);
				}
				catch(InterruptedException e){}
				System.out.println("");
			System.out.println("Student Name 		Roll Number 		Current Marks 		Attendance");
			String line;
			
	        while ((line = br.readLine()) !=null) {
	        	f=1;
		        String[] values = line.split(" ");
		        String x=values[0];
		        System.out.println(values[1] +"			"+ values[0] +"			"+ values[5] +"			"+ values[6]);
		    }
		    
		    br.close();
	}catch(Exception e){}
	if(f==0)
		System.out.println("NO DATA FOUND!! May be your database is empty...");
	}
}

// *****************************************************************************************************************************************************************

// HEAD OF SCHOOL

class Hos extends Faculty{
	Scanner sc=new Scanner(System.in);
	void viewFullDetails(){
		int r;
		System.out.println();
		int f=0;
		System.out.println("Enter the roll number of a student whose record you want to view: ");
		r=sc.nextInt();
		try{
	        BufferedReader br= new BufferedReader(new FileReader("students.txt"));
	        String line;
        	while ((line = br.readLine()) !=null) {
	        String[] values = line.split(" ");
	        String x=values[0];
	        int n;
	        n=Integer.parseInt(x);
	       // System.out.println();
	        if(values.length >= 3 && n==r){
	        	f=1;
	        	try{
				TimeUnit.SECONDS.sleep(1);
				}
				catch(InterruptedException e){}
				//System.out.println("");
	        	System.out.println(values[1] + "'s Information ");
	        	System.out.println("**********************");
	        	System.out.println();
	        	System.out.println("	Name: 				"+values[1]);
	        	System.out.println("	Roll Number: 			"+values[0]);
	        	System.out.println("	Father's Name: 			"+values[2]);
	        	System.out.println("	Age: 				"+values[3]);
	        	System.out.println("	Phone Number: 			"+values[4]);
	        	System.out.println("	Current Marks: 			"+values[5]);
	        	System.out.println("	Attendance: 			"+values[6]);
	    	}
	    	}
	    	if(f==0)
	    		System.out.println("NO RECORD FOUND!! Check this student is present in database or not...");
	    	br.close();
	    }catch(Exception a){}		
	}

	void viewAllStudent(){
		System.out.println("				STUDENT DATABASE 				");
		int f=0;
		try{
			BufferedReader br= new BufferedReader(new FileReader("students.txt"));
			try{
				TimeUnit.SECONDS.sleep(1);
				}
				catch(InterruptedException e){}
				System.out.println("");
			System.out.println("	Student Name 		Roll Number 		Father's Name 		Age 		Current Marks 		Attendance		Phone Number");
			String line;
			
	        while ((line = br.readLine()) !=null) {
	        	f=1;
		        String[] values = line.split(" ");
		        String x=values[0];
		     System.out.println("	"+values[1] +"			"+ values[0] +"			"+ values[2] +"			"+ values[3] +"		"+ values[5] +"			"+ values[6]+"			"+ values[4]);
		    }
		    
		    br.close();
	}catch(Exception e){}
	if(f==0)
		    	System.out.println("NO DATA FOUND!! May be your database is empty...");
	}
}

//**************************************************************************************************************************************************************************

// STUDENT
class Student{

	Scanner sc=new Scanner(System.in);
	void view(){
		int r;
		System.out.println("");
		System.out.println("Enter your current roll number: ");
		r=sc.nextInt();
		int f=0;
		try{
	        BufferedReader br= new BufferedReader(new FileReader("students.txt"));
	        String line;
        	while ((line = br.readLine()) !=null) {
	        String[] values = line.split(" ");
	        String x=values[0];
	        int n;
	        n=Integer.parseInt(x);

	       
	        if(values.length >= 3 && n==r){
	        	f=1;
	        	try{
				TimeUnit.SECONDS.sleep(1);
				}
				catch(InterruptedException e){}
				System.out.println("");
	        	System.out.println(values[1] + "'s Information ");
	        	System.out.println("**********************");
	        	System.out.println();
	        	System.out.println("	Name: 				"+values[1]);
	        	System.out.println("	Roll Number: 			"+values[0]);
	        	System.out.println("	Father's Name: 			"+values[2]);
	        	System.out.println("	Age: 				"+values[3]);
	        	System.out.println("	Phone Number: 			"+values[4]);
	        	System.out.println("	Current Marks: 			"+values[5]);
	        	System.out.println("	Attendance: 			"+values[6]);
	    		}
	    	}
	    		if(f==0)
	    		System.out.println("NO RECORD FOUND!! Check this student is present in database or not...");
	    	br.close();
	    }catch(Exception a){}
	}
}

//*********************************************************************************************************************************************************************************

// MAIN

class StudentMgt{
	public static void main(String args[])   {
			System.out.println("");
			System.out.println("");
			System.out.println("			***********************************************");
			System.out.println("			Name :  				Pallavi			");
			System.out.println("			Section  :				K18AP		");
			System.out.println("			Roll Number  :				11		");
			System.out.println("			Registration NUmber :			11801629	");
			System.out.println("			************************************************");
			System.out.println("");
			System.out.println("		Java Concepts Used are :  1). Abstract Class 		 2). Inheritance		 3).  I/O Fundamental 		4).  Exceptional Handling");
			System.out.println("");
			System.out.println("");
			try{
			TimeUnit.SECONDS.sleep(1);
			}
			catch(InterruptedException e){}
			
			Scanner sc=new Scanner(System.in);
			System.out.println("******************************************************************************************************************************************************************************************************************");
			System.out.println("										 STUDENT MANAGEMENT SYSTEM 																						");
			System.out.println("******************************************************************************************************************************************************************************************************************");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			
		while(true){	
		System.out.println("");
		try{
			TimeUnit.SECONDS.sleep(1);
		}
		catch(InterruptedException e){}
		
		System.out.println("								Use this database as a:				");
		System.out.println("								1.	Admin");
		System.out.println("								2.	Faculty");
		System.out.println("								3.	Head of School");
		System.out.println("								4.	Student");
		System.out.println("								5.	Quit");

		
		int choice;
		System.out.println("Enter your choice 1/2/3/4/5: ");
		choice=sc.nextInt();
		
		if(choice == 1){
			System.out.println("	Please Sign up for the current session");
			String adminName;
			System.out.println("	Enter your Registered Admin Name: ");
			adminName=sc.next();
			System.out.println("");
			System.out.println("				Successfully Sign Up!!!               ");
			System.out.println("");
			/*try{
				TimeUnit.SECONDS.sleep(1);
			}*/
			//catch(InterruptedException e){}
			System.out.println("						:)  WELCOME ADMIN 	:  "+ adminName +"	:)				 ");
			System.out.println("");
			
			AdminClass obj1 = new AdminClass();
			int choice2;
			while(true){
				System.out.println("");
				try{
				TimeUnit.SECONDS.sleep(1);
				}
				catch(InterruptedException e){}
				System.out.println("Select any one :");
				
				System.out.println("1. Add a new Student ");
				System.out.println("2. Delete record of any Student ");
				System.out.println("3. Update record of any Student ");
				System.out.println("4. View record of any particular Student ");
				System.out.println("5. View Full Database of Students ");
				System.out.println("6. Exit ");
				System.out.println("");
				System.out.println("Enter your choice : ");
				choice2=sc.nextInt();
				if(choice2 == 1)
				{
					//add
					obj1.addStudent();
				}
				else if(choice2 == 2)
				{
					//delete
					obj1.deleteStudent();
				}
				else if(choice2 == 3)
				{
					//Update
					obj1.updateStudent();
				}
				else if(choice2 == 4)
				{
					//display
					obj1.displayStudent();
				}
				else if(choice2 ==5)
				{
					//display full database
					obj1.database();
				}
				else if(choice2 == 6)
					break;
				else{
					System.out.println();
					System.out.println("Please enter valid choice");
				}
			}
		}
		else if(choice == 2){
			System.out.println("	Please Sign up for the current session");
			String facName;
			System.out.println("	Enter your Registered Faculty Name: ");
			facName=sc.next();
			System.out.println("");
			System.out.println("				Successfully Sign Up!!!               ");
			System.out.println("");

			
			System.out.println("					:)				 WELCOME FACULTY 	:  "+ facName +"					 :)");
			System.out.println("");
			System.out.println();
			
			System.out.println("");
			Hos obj=new Hos();
			int choice2;
			while(true){
				System.out.println("");
				
				try{
				TimeUnit.SECONDS.sleep(1);
				}
				catch(InterruptedException e){}
				System.out.println("Select any one");
				System.out.println("1. Upload Marks/Attendance ");
				System.out.println("2. View details of a Student ");
				System.out.println("3. View details of all Student ");
				System.out.println("4. Exit ");
				System.out.println("");
				System.out.println("Enter your choice: 1/2/3/4");
				choice2=sc.nextInt();
				if(choice2 == 1)
				{
					// Upload marks or attendance
					obj.upload();
				}
				else if(choice2 == 2)
				{
					// view any p'cular student
					obj.viewStudent();
				}
				else if(choice2==3)
				{
					obj.AllStudents();
				}
				else if(choice2 == 4)
					//System.exit(0);
					break;
				else{
					System.out.println();
					System.out.println("Please enter valid choice");
				}
			}
		}
		else if(choice ==3){
			System.out.println("	Please Sign up for the current session");
			String hosName;
			System.out.println("	Enter your Registered HOS Name: ");
			hosName=sc.next();
			System.out.println("");
			System.out.println("				Successfully Sign Up!!!               ");
			System.out.println("");
			
			System.out.println("					:)	 WELCOME HOS 	:  "+ hosName +" :)				 ");
			System.out.println("");
			System.out.println();
			System.out.println(" NOTE: HOS can only see the full details of a student");
			
			System.out.println("Recommended : If you don't know that how many students of which roll number present then firstly go to step 2:");
			Hos obj=new Hos();
			int choice2;
			while(true){
				System.out.println("");
				try{
				TimeUnit.SECONDS.sleep(1);
				}
				catch(InterruptedException e){}
				System.out.println("Select anyone");
				System.out.println("1. View details of a particular Student ");
				System.out.println("2. View details of all Student.");
				System.out.println("3. Exit ");
				System.out.println("");
				System.out.println("Enter your choice 1/2/3");
				choice2=sc.nextInt();
				if(choice2 == 1)
				{
					// view any p'cular student
					obj.viewFullDetails();
				}
				else if(choice2 == 2)
				{
					// view any all student
					obj.viewAllStudent();
				}
				else if(choice2 == 3)
					//System.exit(0);
					break;
				else{
					System.out.println("Please enter valid choice");
				}
			}
		}
		else if(choice==4){
			System.out.println("	Please Sign up for the current session");
			String stName;
			System.out.println("	Enter your Registered Student Name: ");
			stName=sc.next();
			System.out.println("");
			System.out.println("				Successfully Sign Up!!!               ");
			System.out.println("");
			try{
				TimeUnit.SECONDS.sleep(1);
			}
			catch(InterruptedException e){}
			System.out.println("					:)	 WELCOME  STUDENT 	:  "+ stName +"		:)			   ");
			System.out.println("");
			System.out.println();
			System.out.println(" NOTE: Student can only see his/her full details but can not modify. For any kind of Updates or queries please ask your concern admin.");
			Student obj=new Student();
			obj.view();

		}
		else if(choice==5){
			System.out.println("");
			try{
				TimeUnit.SECONDS.sleep(1);
			}
			catch(InterruptedException e){}
			System.out.println("			:)   			Thank You  				:)			");
			System.out.println("");
			System.out.println("");
			System.exit(0);
		}
		else{
			System.out.println("");
			System.out.println("			:( 		Please input valid choice2 		 :( ");
			System.out.println("			:(  	TRY AGAIN!! 			:(");
		}
	}
	}
}