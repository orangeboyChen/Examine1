import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Base64.Decoder;
public class Base64Mode {

    public static String Encrypt(String str){
        Encoder encoder=Base64.getEncoder();
        byte[] bytes=encoder.encode(str.getBytes());
        return new String(bytes);
    }

    public static String Decrypt(String str) throws Exception{
        char[] chars=str.toCharArray();
        //Check is valid
        for(int i=0;i<chars.length;i++){
            for(int j=i+1;j<chars.length;j++){
                if(chars[i]==chars[j]){
                    throw new Exception();
                }
            }
        }
        Decoder decoder=Base64.getDecoder();
        byte[] bytes=decoder.decode(str.getBytes());
        return new String(bytes);
    }
}
