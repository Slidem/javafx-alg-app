package algorithm.urlify;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author slidem
 *
 */
public class URLifyTest {
	
	@ParameterizedTest
	@MethodSource(value = { "urlifySuccessValues" })
	public void testUrlilify(String str, String expected, int realStrLength) {
		//given
		//when
		String result = URLify.from(str, realStrLength).urlify();
		//expect
		assertEquals(expected, result);
	}
	
	
	@SuppressWarnings("unused")
	private static Stream<Arguments> urlifySuccessValues(){
		return Stream.of(
				Arguments.of("This is a test.      ", "This%20is%20a%20test.", 15),
				Arguments.of("test", "test", 4),
				Arguments.of("test test  ", "test%20test", 9),
				Arguments.of("test test   test        ", "test%20test%20%20%20test", 16)
				);
	}
	

}
