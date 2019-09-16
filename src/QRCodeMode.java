import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Hashtable;

public class QRCodeMode {

    public static String getStrFromTXT(String path) throws FileNotFoundException,IOException{
        File file=new File(path);
        InputStreamReader inputStreamReader=new InputStreamReader(new FileInputStream(file));
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        String str=bufferedReader.readLine();
        return str;
    }

    public static void getQRCode(String str, String paths) throws WriterException,IOException {
        QRCodeWriter qrCodeWriter=new QRCodeWriter();
        BitMatrix bitMatrix=qrCodeWriter.encode(str, BarcodeFormat.QR_CODE,350,350);
        Path path= FileSystems.getDefault().getPath(paths);
        MatrixToImageWriter.writeToFile(bitMatrix,"png",new File(paths));
        }


    /**
     * 二维码的生成需要借助MatrixToImageWriter类，该类是由Google提供的，可以将该类直接拷贝到源码中使用
     */
    public static class MatrixToImageWriter {
        private static final int BLACK = 0xFF000000;
        private static final int WHITE = 0xFFFFFFFF;

        private MatrixToImageWriter() {
        }

        public static BufferedImage toBufferedImage(BitMatrix matrix) {
            int width = matrix.getWidth();
            int height = matrix.getHeight();
            BufferedImage image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
                }
            }
            return image;
        }

        public static void writeToFile(BitMatrix matrix, String format, File file)
                throws IOException {
            BufferedImage image = toBufferedImage(matrix);
            if (!ImageIO.write(image, format, file)) {
                throw new IOException("Could not write an image of format "
                        + format + " to " + file);
            }
        }

        public static void writeToStream(BitMatrix matrix, String format,
                                         OutputStream stream) throws IOException {
            BufferedImage image = toBufferedImage(matrix);
            if (!ImageIO.write(image, format, stream)) {
                throw new IOException("Could not write an image of format " + format);
            }
        }
    }
}