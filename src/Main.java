import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int ins=0;
        String input;
        Scanner scanner=new Scanner(System.in);;
        JSONMode jsonMode;

        try {
            System.out.println("请输入功能：\n1 Base64加解密\n2 JSON及字典反转\n3 二维码制作\n请输入：");
            ins = scanner.nextInt();
        }catch (Exception e){
            System.exit(-1);
        }

        switch (ins){
            case 1:
                //Encrypt
                System.out.println("输入要加密的字符串：");
                input=scanner.next();
                System.out.println("加密后："+Base64Mode.Encrypt(input));

                //Decrypt
                try {
                    System.out.println("输入要解密的字符串：");
                    input = scanner.next();
                    System.out.println("解密后：" + Base64Mode.Decrypt(input));
                }catch (Exception e){
                    System.out.println("字符串无效！");
                }
                break;
            case 2:
                jsonMode=new JSONMode();

                //Add item;
                int i=0;
                do{
                    System.out.println("请输入键值：（输入OUT结束）");
                    input = scanner.next();
                    if(input.equals("OUT"))break;
                    jsonMode.addItem(input,i);
                    i++;
                }while(true);

                //To Json
                System.out.println("JSON转化后："+jsonMode.mapToJson(jsonMode.getMap()));

                //Convert map and to Json
                jsonMode.convertMap();
                System.out.println("反转后："+jsonMode.mapToJson(jsonMode.getConvertMap()));
                break;
            case 3:
                System.out.println("输入in.txt所在的文件夹：");
                input=scanner.next();
                try {
                    QRCodeMode.getQRCode(QRCodeMode.getStrFromTXT(input+"/in.txt"),input+"/in.png");
                    System.out.println("获取二维码成功!在"+input+"/in.png");
                } catch (FileNotFoundException e){
                    System.out.println("系统找不到文件！");
                }
                catch (Exception e){
                    e.printStackTrace();
                    System.out.println("获取二维码失败");
                }
                break;
            default:
                System.out.println("你输的啥玩意？");
                break;
        }
    }

}
