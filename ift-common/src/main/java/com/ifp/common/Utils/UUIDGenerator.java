package com.ifp.common.Utils;

import java.util.UUID;

/**
 * <P>Description: 生成UUID业务类</P>
 * @ClassName: UUIDGenerator
 * @author A   2012-1-13 下午02:24:24
 * @see
 */
public class UUIDGenerator {
	/**
	 * <p>Title: createKey</p>
	 * <p>Description: 生成UUID公用业务方法</p>
	 * @return
	 * @author A   2012-1-13 下午02:25:11
	 */
	public static String createKey() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 
	 * <p>Title: create32Key</p>
	 * <p>Description: 创建32位的UUID</p>
	 * @return
	 * @author 许世选  2012-1-16 下午03:29:32
	 */
	public static String create32Key() {
		return UUID.randomUUID().toString().replaceAll("\\-", "");
	}
	
	/**
	  * 创建指定数量的随机字符串
	  * @param length
	  * @return
	  */
	public static int createRandom(int length) {
		String retStr = "";
		String strTableFirst = "123456789";
		String strTable = "1234567890";

		for (int i = 0; i < length; i++) {
			if (i == 0) {
				double dblR = Math.random() * 9;
				int intR = (int) Math.floor(dblR);
				retStr += strTableFirst.charAt(intR);
			} else {
				double dblR = Math.random() * 10;
				int intR = (int) Math.floor(dblR);
				retStr += strTable.charAt(intR);
			}
		}
		return Integer.parseInt(retStr);
	}

	/**
	 * 生成指定长度的UUID.
	 *
	 * @param length 生成的UUID长度.(1~32)
	 * @return java.lang.String 生成的UUID.

	 * @date 2020/2/2
	 **/
	public static String createLengthKey(int length) {
		// return UUID.randomUUID().toString().replaceAll("\\-", "");
		int beginIndex = 31 - length;
		if (beginIndex < 1 || beginIndex > 31) {
			return create32Key();
		}
		return UUID.randomUUID().toString().replace("-", "").substring(beginIndex);
	}

	public static int[] createArray(int k, int n) {
		int[] numbers = new int[n];
		for (int i = 0; i < n; i++)
			numbers[i] = i + 1;
		int[] result = new int[k];
		for (int i = 0; i < result.length; i++) {
			int r = (int) (Math.random() * n);
			result[i] = numbers[r];
			numbers[r] = numbers[n - 1];
			n--;
		}
		return result;
	}
}
