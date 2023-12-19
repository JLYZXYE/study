package asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MyClassVisitor extends ClassVisitor {

	public MyClassVisitor(ClassVisitor classVisitor) {
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

			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
			mv.visitVarInsn(Opcodes.LSTORE, 1);
		}

		@Override
		public void visitInsn(int opcode) {

			if ((opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN) || opcode == Opcodes.ATHROW) {

				mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
				mv.visitVarInsn(Opcodes.LSTORE, 3);
				Label label7 = new Label();
				mv.visitLabel(label7);
				mv.visitLineNumber(15, label7);
				mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
				mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
				mv.visitInsn(Opcodes.DUP);
				mv.visitLdcInsn("invoke method total time====");
				mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "(Ljava/lang/String;)V",
						false);
				mv.visitVarInsn(Opcodes.LLOAD, 3);
				mv.visitVarInsn(Opcodes.LLOAD, 1);
				mv.visitInsn(Opcodes.LSUB);
				mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append",
						"(J)Ljava/lang/StringBuilder;", false);
				mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;",
						false);
				mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V",
						false);

			}
			mv.visitInsn(opcode);
		}

	}

}
