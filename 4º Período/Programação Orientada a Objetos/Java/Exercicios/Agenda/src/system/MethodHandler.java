package system;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
public class MethodHandler {
  public MethodHandler() {
  }
public static Method[] findSetMethods(Class c) {
   // Obtém os métodos Set da classe filha.
   Method[] meth, smeth;
   meth = c.getMethods();
   smeth = new Method[meth.length];


   // Analisa apenas os métodos que:
   // 1. Foram declarados nesta classe (não herdados).
   // 2. Têm nome que começa com o prefixo dos métodos de obtenção.
   // 3. Não têm nome que termina com "s" (indicativos de coleções).
   // 4. Não tem parâmetros.
   int j=0;
   for (int i = 0; i < meth.length; i++) {
      // Os métodos que interessam passam no teste.
      if (meth[i].getName().startsWith("set")){
      //    !meth[i].getName().endsWith("s") &
      // &     meth[i].getParameterTypes().length == 0)

           smeth[j]=meth[i];
           System.out.println(j + ": " + smeth[j].getName());
           j++;
      }
    }
    return smeth;
 }
 public static void execSetMethod(Object o, String field, Object value){
   String method;
   if(field==null){
     return;
   }
   if(field.length()==0){
     return;
   }
   System.out.println("deu pau neste field: " + field);
   method = "set" +  field.substring(0,1).toUpperCase() + field.substring(1);
   Method m = findMethod(method,o.getClass().getMethods());
  try {
    m.invoke(o, new Object[] {value});
  }
  catch (InvocationTargetException ex) {
  }
  catch (IllegalArgumentException ex) {
  }
  catch (IllegalAccessException ex) {
  }
 }
 public static  Method[] findGetMethods(Class c) {
   // Obtém os métodos Set da classe filha.
   Method[] meth, gmeth;
   meth = c.getMethods();
   gmeth = new Method[meth.length];


   // Analisa apenas os métodos que:
   // 1. Foram declarados nesta classe (não herdados).
   // 2. Têm nome que começa com o prefixo dos métodos de obtenção.
   // 3. Não têm nome que termina com "s" (indicativos de coleções).
   // 4. Não tem parâmetros.
   int j=0;
   for (int i = 0; i < meth.length; i++) {
      // Os métodos que interessam passam no teste.
      if(meth[i]!=null){
        if (meth[i].getName().startsWith("get")) {
          //    !meth[i].getName().endsWith("s") &
          // &     meth[i].getParameterTypes().length == 0)

          gmeth[j] = meth[i];
          System.out.println(j + ": " + gmeth[j].getName());
          j++;
        }
      }
    }
    return gmeth;
 }

    /**
    @param method
    @return java.lang.reflect.Method
    @roseuid 449552430242
     */
public static   Method findMethod(String method, Method meth[])
    {
   int i=0;
   while ((i<meth.length) && (meth[i]!=null) ) {
     if (method.equals(meth[i].getName().toString())) {
        return meth[i];
     }
     i++;
   }
   return null;
    }

}
