import java.awt.*;
import java.io.*;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
public class FDTest extends Frame {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

TextField tb;

   Label myLabel;
   Button loadButton;
   Button proceed;
   Panel p;
   int percent;
   static String filename;
   static String opfilename;

   FDTest (int i) {
    
        super ("Summarizer");
     if(i==0){
        p = new Panel ();
        p.add (loadButton = new Button ("Load"));
        //p.add (saveButton = new Button ("Save"));
        add ("North", myLabel = new Label ("Welcome...\n Step1: Select your input pdf file"));
        add ("South", p);
        //add ("Center", myTextArea = new TextArea (10, 40));
        Menu m = new Menu ("File");
        m.add (new MenuItem ("Quit"));
        MenuBar mb = new MenuBar();
        mb.add (m);
        setMenuBar (mb);
        pack();
       }
     else if(i==1)
      {
             //super ("File Dialog Tester");
        p = new Panel ();
        p.add(tb=new TextField(4));
        p.add (proceed = new Button ("Proceed"));
        //p.add (saveButton = new Button ("Save"));
        add ("North", myLabel = new Label ("Step2: Specify the amount of compression you want(1-100).. Lower the value hgher compression"));
        add ("South", p);
        //add ("Center", myTextArea = new TextArea (10, 40));
        Menu m = new Menu ("File");
        m.add (new MenuItem ("Quit"));
        MenuBar mb = new MenuBar();
        mb.add (m);
        setMenuBar (mb);
        pack();

           
       }
     else if(i==2)
     {
    	 p = new Panel ();
         
         add ("North", myLabel = new Label ("Processing.. It will take few minutes"));
         //add ("Center", myTextArea = new TextArea (10, 40));
         add("South",p);
         Menu m = new Menu ("File");
         m.add (new MenuItem ("Quit"));
         MenuBar mb = new MenuBar();
         mb.add (m);
         setMenuBar (mb);
         pack(); 
     }
     else if(i==3)
     {
    	 p = new Panel ();
         
         add ("North", myLabel = new Label ("The output will be a pdf file in the same folder as input file with the name summarized+filename"));
        p.add(new Label("If the output file is not generated, there might be some issue with the input"));
         p.add(new Label("Close the dialog box"));
         //add ("Center", myTextArea = new TextArea (10, 40));
         add("South",p);
         Menu m = new Menu ("File");
         m.add (new MenuItem ("Quit"));
         MenuBar mb = new MenuBar();
         mb.add (m);
         setMenuBar (mb);
         pack(); 
     }
    }
    public static void main (String args[]) {
        FDTest f = new FDTest(0);
            f.show();
    }

    public boolean handleEvent (Event e) {
        if (e.id == Event.WINDOW_DESTROY) {
            hide();
            dispose ();
            System.exit(0);
            return true;  // never gets here
        }
        return super.handleEvent (e);
    }

    public boolean action (Event e, Object o) {
        if (e.target instanceof MenuItem) {
            hide();
            dispose ();
            System.exit(0);
            return true;  // never gets here
        } else if (e.target instanceof Button) {
            int state;
            String msg;
            if (e.target == loadButton) {
                state = FileDialog.LOAD;
                msg = "Load File";
            
            FileDialog file = new FileDialog (this, msg, state);
            file.setFile ("*.pdf");  // set initial filename filter
            file.show(); 
            String curFile;
            if ((curFile = file.getFile()) != null) {
                filename = file.getDirectory() + curFile;
                
                opfilename = file.getDirectory() + "summarised "+curFile ;
                // curFile ends in .*.* if file does not exist
               hide();
               dispose();
               FDTest f=new FDTest(1);
               f.show();   
                   
            }        
            return true;
        }
         if (e.target == proceed) {
          try{  
             String value = tb.getText();
             percent=Integer.parseInt(value);
            myLabel.setText("Processing...It will take few minutes");
            p.remove(proceed);
            p.remove(tb);
            int ret=extractText.extractiveSummarizer(filename,percent,opfilename);
            System.out.println(ret);
            hide();
            dispose();
            FDTest f=new FDTest(3);
            f.show();   
            
            }
             catch(Exception e2){
                }
                // curFile ends in .*.* if file does not exist
        
              
              
            
            return true;
        }

     }
        return false;
    
}
}
