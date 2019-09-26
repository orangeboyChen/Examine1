import com.google.zxing.WriterException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int ins=0;
        int[] insArray={1,2,3,4};
        int[] ins2Array={1,2};
        String input;
        Scanner scanner=new Scanner(System.in);;
        JSONMode jsonMode;


        while(true) {
            First:
            while (true) {
                try {
                    System.out.println("请输入功能：\n1 Base64\n2 JSON和字典\n3 二维码\n4 退出\n请输入：");
                    ins = scanner.nextInt();

                    //Check if valid ins(loop)
                    for (int i : insArray) {
                        if (ins == i) {
                            break First;
                        }
                    }
                    throw new Exception();
                } catch (Exception e) {
                    //Catch the invalid input
                    System.out.println("输入格式有误，请重新输入！\n\n");
                    scanner.nextLine();
                }
            }

            switch (ins) {
                case 1:
                    int ins2;
                    //Ask for instructions
                    Second:
                    while (true) {
                        try {
                            System.out.println("请输入功能：\n1 加密\n2 解密\n请输入：");
                            ins2 = scanner.nextInt();

                            //Check if valid ins2(loop)
                            for (int i : ins2Array) {
                                if (i == ins2) {
                                    break Second;
                                }
                            }
                            throw new Exception();
                        } catch (Exception e) {
                            System.out.println("输入格式有误，请重新输入！\n\n");
                            scanner.nextLine();
                        }
                    }
                    switch (ins2) {
                        case 1:
                            //Encrypt
                            System.out.println("输入要加密的字符串：");
                            input = scanner.next();
                            System.out.println("加密后：" + Base64Mode.Encrypt(input));
                            break;
                        case 2:
                            //Decrypt
                            try {
                                System.out.println("输入要解密的字符串：");
                                input = scanner.next();
                                System.out.println("解密后：" + Base64Mode.Decrypt(input));
                            } catch (Exception e) {
                                System.out.println("字符串无效！");
                            }
                            break;
                    }
                    break;
                case 2:
                    jsonMode = new JSONMode();
                    String input2;

                    //Add item;
                    do {
                        //Get KEY
                        System.out.println("请输入KEY：（输入OUT结束）");
                        input = scanner.next();
                        if (input.equals("OUT")) break;

                        //Get VALUE
                        System.out.println("请输入VALUE：");
                        input2 = scanner.next();

                        jsonMode.addItem(input, input2);
                    } while (true);

                    //Print type
                    System.out.println("字典类型：" + jsonMode.getMap().getClass());
                    System.out.println("JSON类型：" + jsonMode.mapToJson(jsonMode.getConvertMap()).getClass());

                    //To Json
                    System.out.println("JSON转化后：" + jsonMode.mapToJson(jsonMode.getMap()));

                    //Convert map and to Json
                    jsonMode.convertMap();
                    System.out.println("字典反转后：" + jsonMode.mapToJson(jsonMode.getConvertMap()));
                    break;
                case 3:
                    //Get path
                    System.out.println("输入in.txt所在的文件夹：");
                    input = scanner.next();

                    //Convert to PNG
                    try {
                        QRCodeMode.getQRCode(QRCodeMode.getStrFromTXT(input + "in.txt"), input + "in.png");
                        System.out.println("获取二维码成功!在" + input + "in.png");
                    } catch (FileNotFoundException e) {
                        System.out.println("系统找不到文件！");
                    } catch (IOException e) {
                        System.out.println("读取文件失败！");
                    } catch (WriterException e) {
                        System.out.println("写入失败！");
                    } catch (Exception e) {
                        System.out.println("获取二维码失败！");
                    }
                    break;
                case 4:
                    System.exit(0);
            }
            scanner.nextLine();
            System.out.println("输入ENTER继续");
            scanner.nextLine();
        }
    }

}
