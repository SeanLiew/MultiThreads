package create;

/**
 * @Description:
 * @author: liuxiaozheng
 * @Created: 2020-10-27-00:09
 */
public class CaseInsensitiveString {
    private String s;

    public CaseInsensitiveString(String s){
        this.s = s;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CaseInsensitiveString){
            return s.equalsIgnoreCase(((CaseInsensitiveString)o).s);
        }

        if (o instanceof String){
            return s.equalsIgnoreCase((String)o);
        }

        return false;
    }

    public static void main(String[] args) {
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");

        String str = "Polish";

        System.out.println(cis.equals(str));
        System.out.println(str.equals(cis));

    }
}
