package asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MyClassVisitor2 extends ClassVisitor {

	public MyClassVisitor2(ClassVisitor classVisitor) {
		super(Opcodes.ASM7, classVisitor);
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		cv.visit(version, access, name, signature, superName, interfaces);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
			String[] exceptions) {
		MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);

		if (!"<init>".equals(name) && mv != null) {
			// 为这样的方法增加记录方法执行时间的功能
			mv = new MyMethodVisitor(mv);
		}

		return mv;
	}

	class MyMethodVisitor extends MethodVisitor {

		public MyMethodVisitor(MethodVisitor methodVisitor) {
			super(Opcodes.ASM7, methodVisitor);
		}

		@Override
		public void visitCode() {
			mv.visitCode();

			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "asm/MyTimeLogger", "start", "()V", false);
		}

		@Override
		public void visitInsn(int opcode) {

			if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN) || opcode == Opcodes.ATHROW) {

				mv.visitMethodInsn(Opcodes.INVOKESTATIC, "asm/MyTimeLogger", "end", "()V", false);
			}
			mv.visitInsn(opcode);
		}

	}

}
