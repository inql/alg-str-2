import java.util.Objects;

public class IntPair implements Comparable<IntPair>{

    public Integer value1 = 0;
    public Integer value2 = null;

    public IntPair(Integer value1, Integer value2){
        if(value1!=null)
            this.value1 = value1;
        if(value2!=null)
            this.value2 = value2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntPair intPair = (IntPair) o;
        return Objects.equals(value1, intPair.value1) &&
                Objects.equals(value2, intPair.value2);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value1, value2);
    }

    @Override
    public String toString(){
        if(value2 == null)
            return String.copyValueOf(new char[]{(char) value1.intValue(), '\u0020'});
        return String.copyValueOf(new char[]{(char) value1.intValue(), (char) value2.intValue()});
    }

    @Override
    public int compareTo(IntPair intPair) {
        return Integer.compare(this.hashCode(),intPair.hashCode());
    }
}
