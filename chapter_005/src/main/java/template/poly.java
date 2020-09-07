package template;

public class poly {
    public static void main(String[] args) {
        String s = "А роза упала на лапу азора";
        s = s.replace(" ","").toLowerCase(); 
        StringBuilder sb = new StringBuilder(s).reverse();
        boolean isPoly = s.equals(sb.toString());
        System.out.println(isPoly);
    }

}
