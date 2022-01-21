package save.util;

public class ParseString {
    public static String clearNotChinese(String buff){
        String tmpString =buff.replaceAll("(?i)[^a-zA-Z0-9\u4E00-\u9FA5]", "");//去掉所有中英文符号
        char[] carr = tmpString.toCharArray();

        for(int i = 0; i<tmpString.length();i++){
            if(carr[i] < 0xFF&&!(carr[i]>=48&&carr[i]<=57)){
                carr[i] = ' ' ;//过滤掉非汉字和数字内容
            }

        }
        String s = new String(carr);
        String s1 = s.replaceAll(" ", "");
        return s1;
    }
}
