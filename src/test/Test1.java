package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.junit.Test;

public class Test1 {
	public static void main(String[] args) throws IOException {
		String fileURI01 = "C:/data/B.java";
		String fileURI01_Create = "C:/data/B_Create.java";
		File file01 = new File(fileURI01);
		File file01_Create = new File(fileURI01_Create);
		if (file01_Create.exists()) {
			file01_Create.delete();
		}
		file01_Create.createNewFile();
		parse2UTF_8(file01, file01_Create);
		System.out.println(file01.exists());
		System.out.println(Charset.defaultCharset());
		
		
		
		
	}
	public static void parse2UTF_8(File file, File destFile) throws IOException {
		StringBuffer msg = new StringBuffer();
		// 读写对象
		PrintWriter ps = new PrintWriter(new OutputStreamWriter(new FileOutputStream(destFile, false), "utf8"));
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));

		// 读写动作
		String line = br.readLine();
		while (line != null) {
			msg.append(line).append("\r\n");
			line = br.readLine();
		}
		ps.write(msg.toString());
		br.close();
		ps.flush();
		ps.close();
	}
	
	@Test
	public void showEncoding() {
		System.out.println("AAAA");
		try {
			String string_utf8 = "你好你好";
			byte[] string_utf8_bytes = string_utf8.getBytes("utf-8");
			String string_gbk = new String(string_utf8_bytes, "gbk");
			System.out.println(string_gbk);
			createNewFileAndWriteString("C:/data/newTest01.txt", "utf-8", string_gbk);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	private void createNewFileAndWriteString(String fileURI, String charset, String toWrite) {
		File file = new File(fileURI);
		if (file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
			PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
			printWriter.write(toWrite);
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
