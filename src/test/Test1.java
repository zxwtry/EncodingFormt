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
			createNewFileAndWriteString("C:/data/newTest01.txt", "gbk", string_gbk);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getFromTest01 () {
//		readFromFile("C:/data/B.java", "GBK");
//		String string = "给定一个字符串，要求把字符串前面的若干个字符移动到字符串的尾部，"+
//						"如把字符串“abcdef”前面的2个字符'a'和'b'移动到字符串的尾部，"+
//						"使得原字符串变成字符串“cdefab”。请写一个函数完成此功能，"+
//						"要求对长度为n的字符串操作的时间复杂度为 O(n)，空间复杂度为 O(1)。";
//		System.out.println(convertStringToTargetCharset(string, "UTF-8", "GBK"));
		String middleString = readStringFromFileByDefaultCharset("C:/data/B.java");
		System.out.println(convertStringToTargetCharset(middleString, "GBK", "UTF-8"));
	}
	
	String readStringFromFileByDefaultCharset(String fileURI) {
		StringBuffer stringBuffer = new StringBuffer();
		int count = 0;
		byte[] buff = new byte[16];
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(new File(fileURI));
			while ( (count = fileInputStream.read(buff)) != -1 ) {
				stringBuffer.append(new String(buff, 0, count));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != fileInputStream) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return stringBuffer.toString();
	}
	
	String convertStringToTargetCharset(String string, String thisCharset, String targetCharset) {
		String returnString = null;
		try {
			returnString = new String(string.getBytes(thisCharset),targetCharset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return returnString;
	}
	
	String readFromFile(String fileURI, String charset) {
		StringBuffer stringBuffer = new StringBuffer();
		File file = new File(fileURI);
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			byte[] buff = new byte[4096];
			int count = fileInputStream.read(buff);
			String string_read = new String(buff, 0, count, charset);
			System.out.println(string_read);
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuffer.toString();
	}
	
	void createNewFileAndWriteString(String fileURI, String charset, String toWrite) {
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
