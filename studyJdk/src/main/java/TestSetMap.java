import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestSetMap {
	/**
	 * java265.com 示例 Collections.newSetFromMap方法示例分享
	 */
	public static void main(String[] args) {
		Map<Integer, Boolean> m = new HashMap<Integer, Boolean>();
		Set<Integer> s = Collections.newSetFromMap(m);

		// 通过使用add()方法是添加
		// 设置对象中的对象
		s.add(11);
		s.add(22);
		s.add(33);
		s.add(44);
		s.add(55);
		s.add(55);

		// 显示set和map的值
		System.out.println("set is: " + s);
		System.out.println("map is: " + m);
	}
}
