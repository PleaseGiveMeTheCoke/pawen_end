import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Qq {
    public static void main(String[] args) {

        String item = "ABCD";
        int max = 0;
        String maxA = "";
        ArrayList<String> arrayList = generateMy();
        double maxx = 0;
        String maxxx = "";
        for (String s1 : arrayList) {
            int count = 0;
            for (int i = 0; i < 10000; i++) {
                String s = generateAnswer();
                count+=getMark(s1,s);
            }
            System.out.println("如果选"+s1+",得分为"+": "+count/10000.0);
            if(count/10000.0>maxx){
                maxx = count/10000.0;
                maxxx = s1;
            }
        }

        System.out.println("最终结果"+maxxx+":"+maxx);
    }
    private static ArrayList<String> generateMy(){
        String item = "ABCD";
        ArrayList<String> my = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 4; l++) {
                        my.add(String.valueOf(item.charAt(i))+String.valueOf(item.charAt(j))+item.charAt(k)+item.charAt(l)+"");
                    }
                }
            }
        }
        return my;
    }
    private static int getMark(String my, String s) {
        int count = 0;
        for (int i = 0; i < my.length(); i++) {
            if(my.charAt(i)==s.charAt(i)){
                count++;
            }
        }
        return count;
    }

    public static String generateAnswer(){
        String[] abcds = permutation("ABCD");
        int length = abcds.length;
        int i = new Random().nextInt(length);
        return abcds[i];
    }
    public static String[] permutation(String s){
        return   permutation2(s);
    }

    public static String[] permutation2(String s) {
        Set<String> arrayList = new HashSet<>();
        if(s.length()==1){
            return new String[]{s};
        }
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            String s1 = sb.substring(0,i);
            String s2 = sb.substring(i+1);
            String s3 = s1+s2;
            String s4 = String.valueOf(s.charAt(i));
            String[] s5 = permutation2(s3);
            for (String s6 : s5) {
                arrayList.add(s4+s6);
            }
        }

        String[] res = new String[arrayList.size()];
        int i = 0;
        for (String s1 : arrayList) {

            res[i] = s1;
            i++;
        }
        return res;

    }
}
