import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Base64.Decoder;
import java.util.regex.Pattern;

public class Base64Mode {

    public static String Encrypt(String str){
        Encoder encoder=Base64.getEncoder();
        byte[] bytes=encoder.encode(str.getBytes());
        return new String(bytes);
    }

    public static String Decrypt(String str) throws Exception{
        //Check is valid
        if(!isBase64(str))throw new Exception();
        Decoder decoder=Base64.getDecoder();
        byte[] bytes=decoder.decode(str.getBytes());
        return new String(bytes);
    }

    public static boolean isBase64(String str){
        String regex="^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
        return Pattern.matches(regex,str);
    }
}
