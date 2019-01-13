package redblack;

import java.util.Hashtable;

public class RoBDD {
    private final Function TRUE;
    private final Function FALSE;
    private Hashtable<Triple, Function> unique;
    private static Hashtable<Integer, String> functionNames;
    private int varCount;
    
    public RoBDD(){
        this.TRUE = new Function(true);
        this.FALSE = new Function(false);
        this.unique = new Hashtable<Triple, Function>();
        this.functionNames = new Hashtable<Integer, String>();
        functionNames.put(2147483647, "1");
        functionNames.put(2147483646, "0");
        this.varCount = 0;
    }

    public Function generateVar(String name){
        Triple entry = new Triple(varCount, generateTrue(), generateFalse());
        Function result = unique.get(entry);
        if(result == null){
            result = new Function(varCount, generateTrue(), generateFalse());
            unique.put(entry, result);
            functionNames.put(varCount++, name);
        }
        return result;
    }

    Function generateTrue(){
        return this.TRUE;
    }

    Function generateFalse(){
        return this.FALSE;
    }

    Function ite(Function i, Function t, Function e){
        if(i.isTrue())
            return t;
        else if(i.isFalse())
            return e;
        else if(t.isTrue() && e.isFalse())
            return i;
        else{
            final int value = Math.min(Math.min(i.getVar(), t.getVar()), e.getVar());
            final Function T = ite(i.getThen(value), t.getThen(value), e.getThen(value));
            final Function E = ite(i.getOtherwise(value), t.getOtherwise(value), e.getOtherwise(value));
            if(T.equals(E))
                return T;
            final Triple entry = new Triple(value, T, E);
            Function result = unique.get(entry);
            if(result == null){
                result = new Function(value, T, E);
                unique.put(entry, result);
            }
            return new Function(value, T, E);
        }
    }

    public Function and(Function a, Function b){
        return ite(a, b, generateFalse());
    }

    public Function or(Function a, Function b){
        return ite(a, generateTrue(), ite(b, generateTrue(), generateFalse()));
    }

    public Function not(Function a){
        return ite(a, generateFalse(), generateTrue());
    }

    public Function implication(Function a, Function b){
        return ite(a, ite(b, generateTrue(), generateFalse()), generateTrue());
    }

    public Function equivalence(Function a, Function b){
        return ite(a, ite(b, generateTrue(), generateFalse()), ite(b, generateFalse(), generateTrue()));
    }

    public final static class Function {
        private static final int TRUE = 0x7fffffff;
        private static final int FALSE = TRUE - 1;
    
        private final int value;
        private final Function then, otherwise;
    
        public Function(boolean b){
            value = b ? TRUE : FALSE;
            then = otherwise = null;
        }
    
        Function(int value, Function then, Function otherwise){
            this.value = value;
            this.then = then;
            this.otherwise = otherwise;
        }

        public String getName(){
            return functionNames.get(value);
        }
    
        public Function getThen(int value){
            return value == this.value ? then : this;
        }
    
        public Function getOtherwise(int value){
            return value == this.value ? otherwise : this;
        }
    
        public int getVar(){
            return this.value;
        }
    
        boolean isTrue(){
            return this.value == TRUE;
        }
    
        boolean isFalse(){
            return this.value == FALSE;
        }
    
        boolean isConstant(){
            return isTrue() || isFalse();
        }
    }

    private static final class Triple {
        private final int value;
        private final Function then;
        private final Function otherwise;

        Triple(int value, Function then, Function otherwise){
            this.value = value;
            this.then = then;
            this.otherwise = otherwise;
        }

        public boolean equals(Object obj){
            if(obj instanceof Triple){
                Triple arg = (Triple) obj;
                return arg.value == value && arg.then == then && arg.otherwise == otherwise;
            }
            return false;
        }

        public int hashCode(){
            return value ^ then.hashCode() ^ otherwise.hashCode();
        }
    }
}