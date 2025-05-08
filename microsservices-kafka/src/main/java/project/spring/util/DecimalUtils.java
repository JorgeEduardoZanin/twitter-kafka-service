package project.spring.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;

public class DecimalUtils {

	private DecimalUtils() {
    }
	
	public static BigDecimal toBigDecimal(ByteBuffer buffer, int scale) {
		ByteBuffer dup = buffer.duplicate();
		dup.rewind();
		
		byte[] bytes = new byte[dup.remaining()];
		dup.get(bytes);
		
		BigInteger unscaled = new BigInteger(bytes);
		return new BigDecimal(unscaled, scale);
	}
}
