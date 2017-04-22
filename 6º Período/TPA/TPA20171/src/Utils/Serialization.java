package Utils;

import java.io.*;

/**
 * Created by 20142bsi0054 on 20/04/2017.
 */
public class Serialization {


    public static byte[] convertToBytes(Object object) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try{
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(object);
        }catch (IOException e){
            e.printStackTrace();
        }
        return bos.toByteArray();
    }


    public static Object convertFromBytes(byte[] bytes) throws IOException, ClassNotFoundException {

        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInput in = new ObjectInputStream(bis)) {
            return in.readObject();
        }
    }

}
