package algorithm.oneaway;


import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestOneAway {
	
	@ParameterizedTest
	@MethodSource(value = { "testOneAwayValidArguments" })
	public void testOneAwayValid(String str1, String str2) {
		assertTrue(new OneAway(str1, str2).isOneWay());
	}
	
	@ParameterizedTest
	@MethodSource(value = { "testOneAwayInvalidArguments" })
	public void testOneAwayInvalid(String str1, String str2) {
		assertFalse(new OneAway(str1, str2).isOneWay());
	}
	
	private static Stream<Arguments> testOneAwayValidArguments(){
		return Stream.of(Arguments.of("asd", "asd"),
				Arguments.of("asd", "asda"),
				Arguments.of("asda", "asd"),
				Arguments.of("asd", "asb"),
				Arguments.of("af", "ad"),
				Arguments.of("lll", "ll"),
				Arguments.of("xopxop", "xopxp")
				);
	}
	
	private static Stream<Arguments> testOneAwayInvalidArguments(){
		return Stream.of(
				Arguments.of("asd", "asasdd"),
				Arguments.of("aadd", "aa"),
				Arguments.of("asdf", "zzdf"),
				Arguments.of("tsdbc", "asdbm"),
				Arguments.of("a", "aaa"),
				Arguments.of("aa", "bb"),
				Arguments.of("ab", "cd"),
				Arguments.of("avdc", "avx")
				);
	}

}
