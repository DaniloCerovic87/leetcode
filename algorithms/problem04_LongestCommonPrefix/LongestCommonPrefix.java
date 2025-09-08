package problem04_LongestCommonPrefix;

public class LongestCommonPrefix {

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
        System.out.println(longestCommonPrefix(new String[]{"helicopter", "high", "hide"}));
    }

    private static String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        char[] firstElementChars = strs[0].toCharArray();
        for (int i = 0; i < firstElementChars.length; i++) {
            for (int j = i + 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != firstElementChars[i]) {
                    return sb.toString();
                }
            }
            sb.append(firstElementChars[i]);
        }
        return sb.toString();
    }

}
