package system;

public class NameHandler {
  public NameHandler() {
  }


public static   String GetClassShortName(Object c) {
    String t = c.getClass().getName();
    int i = t.length()-1;
    while (t.charAt(i)!='.'){
      i--;
    }
    return  t.substring(i+1,t.length());
  }
  public static   String GetClassShortName(String fullName) {
    String t = fullName;
    int i = t.length()-1;
    while (t.charAt(i)!='.'){
      i--;
    }
    return  t.substring(i+1,t.length());
  }

public static String getToken(String line, char sep ) {
      int i = 0;

      while ((line.charAt(i)!=sep) && ( i< (line.length()-1) )) {
        i++;
      }
      if (i>=line.length()-1) i++;
      return  line.substring(0,i);

    }
public static String lineRemainder(String line, char sep){
      int i = 0;
      while ((line.charAt(i)!=sep) && (i<line.length()-1)) {
        i++;
      }
      if (i>0) i++;
      return  line.substring(i,line.length());

}

public static void main(String[] args){
  String linha =  "nome sino1 sin2 sin3";
  String s;
  char sep = ' ';
  s =getToken(linha,sep);
  System.out.println(s+"|");
  linha = lineRemainder(linha,sep);
  System.out.println(linha);
  s =getToken(linha,sep);
  System.out.println(s+"|");
  linha = lineRemainder(linha,sep);
  System.out.println(linha);
  s =getToken(linha,sep);
  System.out.println(s+"|");
  linha = lineRemainder(linha,sep);
  System.out.println(linha);
  s = getToken(linha,sep);
  System.out.println(s+"|");
  linha = lineRemainder(linha,sep);
  if (linha==null){
      System.out.println("ok");
  }
  }
}
