package asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;

public class Generator {
	public static void main(String[] args) throws Exception {
		ClassReader cr = new ClassReader("asm/CC");
		
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		
		ClassVisitor cv = new MyClassVisitor2(cw);
		
		cr.accept(cv, ClassReader.SKIP_DEBUG);

		byte[] data = cw.toByteArray();
		
		//输出
		File f = new File("target/classes/asm/CC.class");
		
		FileOutputStream fos = new FileOutputStream(f);
		fos.write(data);
		fos.close();
		
		System.out.println("Generator CC class  success!!!");
	}
}
