package respostas.hash;

import java.io.*;

/**
 * Created by cristian on 23/04/17.
 */

class Serialization{

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

public class HashEngineDefault extends HashEngine{

    public int calcCodeHash(Object key){
        byte[] bytes = {};
        double f = 0;
        int base = 27;

        try {
            bytes = Serialization.convertToBytes(key);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(bytes.length);
        for(int i = 0; i < bytes.length; i++){
            f = bytes[i] * Math.pow(base, i);
        }
        return (int)f;
    }

    @Override
    public int hashCode(Object key) {
        byte[] bytes = {};
        double f = 0;
        int base = 2;

        try {
            bytes = Serialization.convertToBytes(key);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < bytes.length; i++){
            f = (bytes[i] * Math.pow(base, i));
        }
        System.out.println((int)f);
        return Math.abs((int)f);
    }
}
