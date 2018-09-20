import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.print.DocFlavor.INPUT_STREAM;

public class Dojo0_Op3 {
	private static String OUTPUT_NAME = "Dojo0_pg2000_copy.txt";
	private static String INPUT_NAME = "Dojo0_pg2000.txt";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File originalOutput = new File(OUTPUT_NAME);
		if (originalOutput.exists()) {
			originalOutput.delete();
		}

		int initialTime = (int) System.currentTimeMillis();
		try (FileInputStream input = new FileInputStream(INPUT_NAME);
				FileOutputStream output = new FileOutputStream(OUTPUT_NAME)) {

			BufferedInputStream inputBuffer = new BufferedInputStream(input);
			BufferedOutputStream outputBuffer = new BufferedOutputStream(output);

			int read;
			while ((read = inputBuffer.read()) != -1) {
				outputBuffer.write(read);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		int outputSeconds = (int) (System.currentTimeMillis() - initialTime);
		System.out.println(outputSeconds + " ms");
	}

}
