package provider;

import javax.validation.ClockProvider;
import java.time.Clock;

/**
 * 提供一个Clock，给@Past、@Future等阅读判断提供参考
 */
public class DefaultClockProvider implements ClockProvider {

 public static final DefaultClockProvider INSTANCE = new DefaultClockProvider();

 private DefaultClockProvider() {
 }

 // 默认是系统时钟
 @Override
 public Clock getClock() {
  return Clock.systemDefaultZone();
 }

}