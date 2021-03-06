package create;

/**
 * @Description:
 * @author: liuxiaozheng
 * @Created: 2020-10-27-00:09
 */
public class CaseInsensitiveString {
    private String s;

<<<<<<< HEAD
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
=======
    public CaseInsensitiveString(String s) {
        this.s = s;
    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (o instanceof CaseInsensitiveString) {
//            return s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
//        }
//
//        if (o instanceof String) {
//            return s.equalsIgnoreCase((String) o);
//        }
//
//        return false;
//    }
>>>>>>> 41a60dd00cd477b7ede64c9688fc4faf9d0720c0

    public static void main(String[] args) {
        CaseInsensitiveString cis = new CaseInsensitiveString("Polish");

        String str = "Polish";

        System.out.println(cis.equals(str));
        System.out.println(str.equals(cis));

<<<<<<< HEAD
=======
//        Arrays.sort();

//        c.clone();


    }

    @Override
    public boolean equals(Object o) {
        return o instanceof CaseInsensitiveString && s.equalsIgnoreCase(((CaseInsensitiveString)o).s);
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
>>>>>>> 41a60dd00cd477b7ede64c9688fc4faf9d0720c0
    }
}
