/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.lang.Object.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.*;
import java.math.*;
import java.text.DecimalFormat;

/**
 *
 * @author praveen
 */
public class Project2 {

    /**
     * @param args the command line arguments
     */
    public void  mle() // Calculation of MLE starts from here
    {
    File tl;     // file object to open a file
    File t2;
    FileInputStream fis1;  // to read a file
    FileInputStream fis2; 
    ArrayList<Integer> al1;  // array list to store train.label data
    ArrayList<String> al2;// array list to store newsgrouplabel.txt
    double i=0;
    double ng=0;
    double[] arr = new double[21]; // array to store the total mle for each class
    BufferedReader reader1 = null;
    BufferedReader reader2 = null;
    DataInputStream dis = null;
    String tlabel = "C:\\Users\\praveen\\Desktop\\2nd sem\\intro to machine\\2nd project\\data\\train.label"; // path for train.label
    String ngdata  = "C:\\Users\\praveen\\Desktop\\2nd sem\\intro to machine\\2nd project\\data\\newsgrouplabels.txt"; // path for newgrouplabels.txt
        try { 
           tl = new File(tlabel);      //  total code from line 51-60 is to  load the files train.label and newsgrouplabel.txt,creating arraylists to load those file contents
           t2 = new File(ngdata);
           fis1 = new FileInputStream(tl); 
           fis2 = new FileInputStream(t2);
           al1=new ArrayList<Integer>();
           al2=new ArrayList<String>();
           reader1 = new BufferedReader(new InputStreamReader(fis1));
           reader2 = new BufferedReader(new InputStreamReader(fis2));
           String line1;
           String line2;
           while ((line1 = reader1.readLine()) != null) // checking the condition for loading  train.label deatials by checking the condition in while loop
            {
               
                al1.add(Integer.parseInt(line1)); // store the file contents to arraylist al1 by changing the contents to integer type
                i++;
            }
           while ((line2 = reader2.readLine()) != null) // checking the condition for loading newsgrouplabels.txt details by checking the conditon
            {
              al2.add(line2);    // store the file contents to arraylist al2
              ng++;
            }
           int k=1;
           for(int j=1;j<=ng;j++) // this loop is for newsgrouplabel.txt
           {
               double count=0;    // count is to count the no of dpoucments in certain newsgrouplabel
                
               while(j== al1.get(k)) // checking wheather newsgrouplabel is in document or not
                   {
                     
                       count++; // if yes increment count
                       k++;
                       if(k==i)  // checking condition to exit from loop
                       {
                           break;
                       }
                       
                   }
               
               arr[j]=(count/i); //probability of each class in the given document is strore in array i.e mle
               //System.out.println("newsgroup" +j+ "probability is" +arr[j] );
           }
           map(al1,arr); // calling map function to calculate map value by sending parameters al1 and arr.
        }
        catch (Exception e) { // catch if there are any exceptions 
            System.out.println("error message  " +e.getMessage());
            e.printStackTrace();
             }
}
    public void map(ArrayList<Integer>al1,double arr[])
    {
       double [][] prob = new double [21][61188]; // array to store probabilities
       double[] twords = new double [21];         // array to store total no of words
       double[][] arr1 = new double[21][61188];   // array to store count of certain word in each class
       double [] maxwords=new double[61188];
       double [] dupmaxwords=new double[61188];
       File t3; // 104-117 lines are to load the the files and store them in array list
       File t6;
       FileInputStream fis3;
       FileInputStream fis6;
       ArrayList<String> al3;
       ArrayList<String> al6;
       BufferedReader reader3 = null;
       BufferedReader reader6 = null;
       DataInputStream dis = null;
       String tdata = "C:\\Users\\praveen\\Desktop\\2nd sem\\intro to machine\\2nd project\\data\\train.data";
       String vocab=   "C:\\Users\\praveen\\Desktop\\2nd sem\\intro to machine\\2nd project\\data\\vocabulary.txt";
       try
       {
           t3 = new File(tdata);
           t6 = new File(vocab);
           fis3 = new FileInputStream(t3);
           fis6 = new FileInputStream(t6);
           al3=new ArrayList<String>();
           al6=new ArrayList<String>();
           reader3 = new BufferedReader(new InputStreamReader(fis3));
           reader6 = new BufferedReader(new InputStreamReader(fis6));
           String line3;
           String line6;
           while ((line3 = reader3.readLine()) != null) // checking the condition for loading train.data details by checking the conditon
            {
                 
                al3.add(line3);                  // adding contents to al3 array list
                String [] tokens = line3.split("\\s+");
                int docid = Integer.parseInt(tokens[0]);   // parsing the contents and storing them in different variables according to their type
                int wid= Integer.parseInt(tokens[1]);
                int wc= Integer.parseInt(tokens[2]);
                int i= al1.get(docid-1);
                int j= wid;
                
                arr1[i][j]= arr1[i][j] + wc;            // storing count of certain word in each class
               
                //System.out.println("praveen");
            }
          
         for(int k=1;k<=20;k++)       // this loop is for classes
         {
             double cwords=0;
             for(int m=1;m<61188;m++)  // for words
             {
                 cwords = cwords + arr1[k][m];  // finding total words in each class
                 
             }
             twords[k]=cwords; // storing them in an array
             //System.out.println("word count" +cwords);
         }
         //double beta=(1/61188);
         for(int x=1;x<21;x++)  // for each class
         {
             for(int y=1;y<61188;y++) // for each word in the class
             {
                 //System.out.println("sdfasdfa" +twords[0]);
                 prob[x][y]= ((arr1[x][y]+0.000016343)/(twords[x]+1)); // probaility each word in certain class and storing them in an array
                 //System.out.println("probability of word" +y+" in class " +x +"is" +prob[x][y]);
             }
         }
         for(int g=1;g<61188;g++)
         {
             for(int h=1;h<21;h++)
             {
                 maxwords[g]=maxwords[g]+prob[h][g];
                 dupmaxwords[g]=maxwords[g];
             }
         }
         while ((line6 = reader6.readLine()) != null) // checking the condition for loading train.data details by checking the conditon
            {
             al6.add(line6);
         }
         double xchange=0;
         for ( int g=1;g<=61188;g++) //implementing bubble sort 
         {  
             for (int h=1;h<=61188-g-1;h++) 
                {
                   if (maxwords[h+1] > maxwords[h]) // checking for  the greater num
                      {
                         xchange  = maxwords[h];      // swapping if the condition is met
                         maxwords[h]   = maxwords[h+1];
                         maxwords[h+1] = xchange;
                      }
                }
         }
         
         for (int g=1;g<=100;g++)// printing the first 100 words with highest measure
         {
             for (int h=1;h<61188;h++)  // finding the exact location of the word
             {
                 if(maxwords[g]==dupmaxwords[h]) // if condition satisfies then print words
                 {
                     String q=al6.get(h);
                     System.out.print(q +"\t");
                 }
             }
         }
       }
       catch(Exception e1) // to catch an exception
       {
           //System.out.println(e1.getMessage());
           e1.printStackTrace();
       }
       classify(prob,arr); //calling classify function with parameters prob and arr
    }
    public void classify(double prob[][], double arr[])
    {
       int [] maxplace=new int[7506];        // array to store max value of probability of certain word in the document
       int [][] confmatrix= new int[21][21];  //array to print confusion matrix
       int fcounter=0;                         
       File t4;                               // 172-89 is to load file contents and storing them array list
       File t5;
       FileInputStream fis4;
       FileInputStream fis5;
       ArrayList<String> al4;
       ArrayList<Integer> al5;
       BufferedReader reader4 = null;
       BufferedReader reader5 = null;
       DataInputStream dis = null;
       String testdata = "C:\\Users\\praveen\\Desktop\\2nd sem\\intro to machine\\2nd project\\data\\test.data"; // path of test.data
       String testlabel = "C:\\Users\\praveen\\Desktop\\2nd sem\\intro to machine\\2nd project\\data\\test.label"; // path of test.label
       try
       {
           t4 = new File(testdata);
           fis4 = new FileInputStream(t4); 
           al4=new ArrayList<String>();
           reader4 = new BufferedReader(new InputStreamReader(fis4));
           String line4;
           int i=0;
           int [][] testarr = new int[7506][61189];  // to store total no of words in certain document of word.
           double [][] newarr = new double[7506][21]; // to store the addition of two matrices
           double [][] logprob = new double[21][61189]; // to store the log values of map probabilities
           double [][] trans = new double[61189][21];    // to store transpose of the matrix
           double [][] denom = new double[75006][21];    
           while ((line4 = reader4.readLine()) != null)// chcking the end of the line condition
            {
                 
                al4.add(line4);  // loading contents to array list 
                String [] tokens = line4.split("\\s+");
                int tdocid = Integer.parseInt(tokens[0]);      //storing the contents into different variables 
                int twid= Integer.parseInt(tokens[1]);
                int twc= Integer.parseInt(tokens[2]);
                testarr[tdocid][twid]= testarr[tdocid][twid]+twc;  // calculating count of each word in certain doument
               //System.out.println(testarr[tdocid][twid]);
            }
           
           
           for (int m=1; m<21;m++) // for classes
           {
               for (int n=1;n<61188;n++) // for each word
               {
                   logprob[m][n]= (Math.log(prob[m][n]))/(Math.log(2.0)); // calculating log of each probabilities for map values
                   trans[n][m] = logprob[m][n];                           // storing that in trans array
                  // System.out.println(logprob[m][n]);
               }
           }
        int p=1,q=1,z=1;
           for(p = 1;p< 7506;p++)  // here we perform matrix multiplaction of testar and trans array
           {
               for(q = 1;q < 21;q++)
               {
                  for(z = 1;z < 61188;z++)
                  {
                     denom[p][q] += testarr[p][z] * trans[z][q];
            //System.out.println(denom[p][q]);
                  }
               }
      
           }
        //System.out.println(denom[2][5]);
           for(int r=1;r<7506;r++)  // for each word in test document
           {
               for(int x=1;x<21;x++)  // for each class
               {
                   newarr[r][x]=arr[x];  // duplicating the values to our convenience
                   denom[r][x]=denom[r][x]+newarr[r][x]; // adding denom and newarr and again storing them in denom array
               }
           } 
           
           for (int a=1;a<7506;a++)  // for each word
           {
              double max=denom[a][1];
              maxplace[a]=1;            // storing the maximum prob place value in this array
               for(int b=1;b<21;b++)  // for each class
               {
                   
                   if(denom[a][b]>max)  // checking wether it is greter than max or not
                   {
                       max=denom[a][b]; // if yes replace
                       maxplace[a]=b;
                   }
               }
               //System.out.println(maxplace);
           }
           t5 = new File(testlabel);         // lines 256- 261 is to load the test.label file
           fis5 = new FileInputStream(t5); 
           al5=new ArrayList<Integer>();
           reader5 = new BufferedReader(new InputStreamReader(fis5));
           String line5;
           
            while ((line5 = reader5.readLine()) != null) // checking for the condition of end of the line
            {
               
              al5.add(Integer.parseInt(line5)); // and storing them in the arraylist al5
              
            }
            
            for(int t=1;t<7506;t++)  //for each word  
            {
                //int s=maxplace[t];
                //int c=al5.get(t-1);
                if(al5.get(t-1)==maxplace[t]) // comparing the values 
                {
                    fcounter++; // if yes increment the fcounter
                    
                }
                confmatrix[al5.get(t-1)][maxplace[t]]++; //calculating the confusion matrix
            }
            
            double perc=(((double)fcounter)/7505)*100; // calculating the acuracy 
            System.out.println("percentage of accuracy is" +perc);
            for(int e=1;e<21;e++)  // printing the confusion matrix
            {
                for(int f=1;f<21;f++)
                {
                    System.out.print(confmatrix[e][f]+ "\t");
                }
                System.out.println();
            }
            
       }
       catch(Exception e2) // catch an exception here
       {
           e2.printStackTrace();
       }
    } 
    
    public static void main(String[] args) { // exextuion starts from here
        // TODO code application logic here
        Project2 p2 = new Project2();
        p2.mle(); // calls the mle function to calculate MLE
        
    }
}

