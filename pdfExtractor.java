import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

/*class to read from pdf file*/
public class pdfExtractor {
	/**
	 * static list having all sentences in doc useful for extraction
	 */
	static ArrayList<String> sentences=new ArrayList<String>();
	/**
	 * module gets input pdf file name and returns List of text in each page and initializes sentences attribute List
	 @param file input filename
	 @return Array List of CharSequences with contents of each page being one node in the List
	 */
  static ArrayList<CharSequence> fileRead(String file){
	  ArrayList<CharSequence> plainPdfText=new ArrayList<CharSequence>();
	  try{
		  
	  PdfReader reader = new PdfReader(file);
	  
      int n = reader.getNumberOfPages();
     
      for(int i=1;i<=n;i++){
          String str=PdfTextExtractor.getTextFromPage(reader, i);
    	 if(str!=null){
          plainPdfText.add(str);
          BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
    	 iterator.setText(str);
    	 int start = iterator.first();
    	 for (int end = iterator.next();end != BreakIterator.DONE;start = end, end = iterator.next()) {
    	   sentences.add(str.substring(start,end));
    	 }
    	 }
      }
      if(plainPdfText.size()!=0){
    	  return plainPdfText;
    	  
      }
      else
    	  System.out.println("empty file");
	  }
	  catch(Exception e){
		  System.out.println("problem opening pdf");
	  }
	  return null;
  }
  
}

