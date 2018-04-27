import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.*;  
import java.awt.event.*;  
import java.awt.datatransfer.*;
import java.awt.Toolkit;
class password_generator
{
    String p_word="";
    public static void main(String args[])throws IOException, FileNotFoundException
    {
        int file_value;
        dpassword_generator obj=new dpassword_generator();
        try{
            /*
               file_value is number of times the this program is used.
               It is used in the formula which creates password therefore it makes sure that the password is random and
               there is no chance of duplication even if there are two machines trying to generate the password at the same 
               time.
               */
            file_value=obj.file();
            System.out.println("File value is "+file_value);            
            String password=obj.lambd(file_value);
            //creating frame
            Frame f=new Frame("ActionListener Example");  
            final TextField tf=new TextField();  
            tf.setBounds(50,50, 150,20);  
            Button b1=new Button("Show");  
            b1.setBounds(50,100,60,30); 
            Button b2=new Button("Copy");
            b2.setBounds(120,100,60,30);

            b1.addActionListener(new ActionListener()
            {  
                    public void actionPerformed(ActionEvent e)
                    {  
                        //String s="Hafnuw42rejefnunf";
                        tf.setText(password);             
                    }  
            }); 
            b2.addActionListener(new ActionListener()
            {
                    public void actionPerformed(ActionEvent ae)
                    {
                        StringSelection stringSelection = new StringSelection(password);
                        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                        clpbrd.setContents(stringSelection, null);
                    }
            });
            f.addWindowListener(new WindowAdapter() 
            {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
            f.add(b1);f.add(tf);  
            f.setSize(400,400);  
            f.setLayout(null);  
            f.setVisible(true);
            f.add(b2);                   
                        
        }
        catch(Exception e){
            System.out.println("Sorry!\n Something has happened,please try restarting the application."+e);
        }
    }
    //creates a file,if not present on your system which will simply store an integer,which is further used in our formula
    int file()throws Exception
    {   
        int L,n,value;
        FileReader fr=null;
        try{
            fr=new FileReader("abc");  
        }
        catch(FileNotFoundException e){
            //System.out.println("No such  file is present ");
            FileWriter fw=new FileWriter("abc");
            //System.out.println("New file is being created");
            n=1;
            fw.write(n); 
            fw.close();
            fr=new FileReader("abc");
        }
        L=fr.read();
        fr.close();        
        value=L+1;
        FileWriter fw=new FileWriter("abc");
        fw.write(value);
        fw.close();
        return L;
    }
    //this is the part where our formula is applied
    String lambd(int file_value)
    {
        String s,din;
        int tareek,month,year;
        int hour,minute,second,m_sec;   
        int temp1,temp2,sum=0;
        int password_length;
        int i,rem_length;
        char ctemp,ch0,ch1,ch2,ch3,ch4,ch5,ch6;
        ch0=65;
        ch1=34;
        ch2=97;
        ch3=91;
        ch4=48;
        ch5=58;
        ch6=123;

        DateFormat df1 = new SimpleDateFormat("dd");
        Date obj1 = new Date();
        s=df1.format(obj1);
        tareek=Integer.parseInt(s);
        //Month
        DateFormat df2 = new SimpleDateFormat("MM");
        Date obj2 = new Date();
        s=df2.format(obj2);
        month= Integer.parseInt(s);
        //Year
        DateFormat df3 = new SimpleDateFormat("yyyy");
        Date obj3 = new Date();
        s=df3.format(obj3);
        year=Integer.parseInt(s);
        //-----------------------------Time------------------------------------------------------
        //Hour
        DateFormat df4=new SimpleDateFormat("HH");
        Date obj4=new Date();
        s=df4.format(obj4);
        hour=Integer.parseInt(s);
        //Minute
        DateFormat df5=new SimpleDateFormat("MM");
        Date obj5=new Date();
        s=df5.format(obj5);
        minute=Integer.parseInt(s);
        //seconds
        DateFormat df6=new SimpleDateFormat("ss");
        Date obj6=new Date();
        s=df6.format(obj6);
        second=Integer.parseInt(s);
        //miliseconds
        DateFormat df7=new SimpleDateFormat("SS");
        Date obj7=new Date();
        s=df7.format(obj7);
        m_sec=Integer.parseInt(s);
        //Din
        DateFormat df8=new SimpleDateFormat("EEEE");
        Date obj8=new Date();
        din=df8.format(obj8);
        din=din.toLowerCase();
        //----------------------Calculating the length of the password-----------------------------------
        //--------------taking cube of each digit of milisecond and then adding them to the sum----------
        temp1=m_sec;
        while(temp1>0)
        {   
            temp2=temp1%10;
            sum+=Math.pow(temp2,3);
            temp1/=10;
        }
        //-----------------------adding the digit of the previous obtained no----------------------------
        temp1=sum;
        sum=0;
        while(temp1>0)
        {
            temp2=temp1%10;
            sum+=temp2;
            temp1/=10;
        }
        password_length=12+sum;
        //System.out.println("Length of password is "+password_length);        
        rem_length=password_length/2;
        //System.out.println("Remaining Length of password is "+rem_length);
        password_length=password_length-rem_length;
        //System.out.println("Length of first part of password is "+password_length);        
        double square_root=0.0;
        double one_value=(13)*(minute+1)*(Math.pow((m_sec+1),2));
        one_value+=tareek+file_value;
        square_root=Math.sqrt(one_value);
        int saal=year*(hour+tareek);
        double lambda=(saal)/square_root;
        //System.out.println("is value se calculate hoga password "+lambda); 
        //System.out.println("Length of password is "+password_length);
        //password_length=12+sum;
        String s1,s2;
        s1=String.valueOf(lambda);
        double lambda2=lambda/file_value;
        s2=String.valueOf(lambda2);
        //System.out.println("Value of hlambda2 is "+lambda2); 
        //half password
        for(i=1;i<=password_length;i++)
        {
            char t;
            if(i==1)
            {
                t=din.charAt(1);
                t=(char)(t+password_length);
                if(t>122)
                {
                    t=(char)(t%26);
                    t=(char)(97+t);
                    p_word+=t;
                }                
                else
                {
                    p_word+=t;                                                
                }
            }
            else if (i==password_length/2)
            {            
                t=din.charAt(3);
                t=(char)(t+password_length);                
                if(t>9)
                {
                    t=(char)(t%9);
                    t=(char)(47+t);
                    p_word+=t;
                }                
                else
                {
                    p_word+=t;         
                }            
            }

            else
            {
                ctemp=s1.charAt(i);
                int temp=ctemp%7;
                int flag=-1;
                if(ctemp=='.')
                {
                    p_word+=ctemp;
                }            
                else if(temp==0)
                {
                    ctemp=(char)(ch0+0);
                    ch0=(char)(ch0+1);
                    flag=check(ctemp);
                    if(flag==1)
                    {    
                        p_word+=ctemp;
                    }
                    else
                    {    
                        p_word+=(ctemp+i);
                    }
                }
                else if(temp==1)
                {
                    ctemp=(char)(ch1+1);
                    ch1=(char)(ch1+1);
                    flag=check(ctemp);
                    if(flag==1)
                    {
                        p_word+=ctemp;   
                    } 
                    else
                    {
                        p_word+=(ctemp+i);                                                    
                    }                                                                             
                }
                else if(temp==2)
                {
                    ctemp=(char)(ch2+2);
                    ch2=(char)(ch2+1);
                    flag=check(ctemp);
                    if(flag==1)
                    {
                        p_word+=ctemp;
                    }
                    else
                    {
                        p_word+=(ctemp+i);
                    }
                }
                else if(temp==3)
                {
                    ctemp=(char)(ch3+3);
                    ch3=(char)(ch3+1);
                    flag=check(ctemp);
                    if(flag==1)
                    {
                        p_word+=ctemp;
                    }
                    else
                    {
                        p_word+=(ctemp+i);
                    }
                }
                else if(temp==4)
                {
                    ctemp=(char)(ch4+4);
                    ch4=(char)(ch4+1);
                    flag=check(ctemp);
                    if(flag==1)
                    {
                        p_word+=ctemp;
                    }
                    else
                    {
                        p_word+=(ctemp+i);
                    }
                }
                else if(temp==5)
                {
                    ctemp=(char)(ch5+5);
                    ch5=(char)(ch5+1);
                    flag=check(ctemp);
                    if(flag==1)
                    {   p_word+=ctemp;
                    }                                                                             
                    else
                    {    
                        p_word+=(ctemp+i);
                    }                                                                          
                }
                else
                {
                    ctemp=(char)(ch6+6);                    
                    ch6=(char)(ch6+1);
                    flag=check(ctemp);
                    if(flag==1)
                    {
                        p_word+=ctemp;
                    }
                    else
                    {   
                        p_word+=(ctemp-i);
                    }
                }
            }
        } 
        //rest half of the password
        for(i=1;i<rem_length;i++)
        {
            char cc=s2.charAt(i);
            int flag=0;
            cc=(char)(cc+i);
            int j;
            char t;
            if (i==rem_length-1)
            {            
                t=din.charAt(3);
                t=(char)(t+password_length);                
                if(t>90)
                {
                    t=(char)(t%26);
                    t=(char)(65+t);
                    p_word+=t;
                }                
                else
                {
                    p_word+=t;         
                }            
            }
            else
            {

                p_word+=cc;
            }
        }
        return p_word;
    }
    //to make sure that the password doesn't have any space literal
    int check(char ch)
    {
        if(ch==32)
            return -1;
        else
            return 1;
    }    
}
