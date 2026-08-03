import java.util.Scanner;
class Marks 
{
 String name;
 int m1,m2,m3,max_marks;
 float avg_marks;
 Marks(String n,int mark1,int mark2,int mark3)
 {
    name=n;
    m1=mark1;
    m2=mark2;
    m3=mark3;
 } 
void compute()
{
    if(m1>m2 && m1>m3)
    {
        max_marks=m1;
    }
    else if(m2>m1 && m2>m3)
    {
        max_marks=m2;
    }
    else
    {
        max_marks=m3;
    }
    avg_marks=(m1+m2+m3)/3;
}
void display()
{
    System.out.println("Name: "+name);
    System.out.println("Marks 1: "+m1); 
    System.out.println("Marks 2: "+m2);
    System.out.println("Marks 3: "+m3);
    System.out.println("Maximum Marks: "+max_marks);
    System.out.println("Average Marks: "+avg_marks);
}
}
class Main_marks
{
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter name: ");
        String nm = in.next();
        System.out.print("Enter marks for subject 1: ");
        int ms1 = in.nextInt();
        System.out.print("Enter marks for subject 2: ");
        int ms2 = in.nextInt();
        System.out.print("Enter marks for subject 3: ");
        int ms3 = in.nextInt();

        Marks m = new Marks(nm, ms1, ms2, ms3);
        m.compute();
        m.display();
    }
}