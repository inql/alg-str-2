import java.util.Objects;

public class IntTrio implements Comparable<IntTrio>{

    public Integer value1 = null;
    public Integer value2 = null;
    public Integer value3 = null;

    public IntTrio(Integer value1, Integer value2, Integer value3){
        if(value1!=null)
            this.value1 = value1;
        if(value2!=null)
            this.value2 = value2;
        if(value2!=null)
            this.value3 = value3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntTrio intTrio = (IntTrio) o;
        return Objects.equals(value1, intTrio.value1) &&
                Objects.equals(value2, intTrio.value2) &&
                Objects.equals(value3, intTrio.value3);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value1, value2, value3);
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append((char) value1.intValue());
        if(value2 == null)
            result.append('\u0020');
        else
            result.append((char) value2.intValue());
        if(value3 == null)
            result.append('\u0020');
        else
            result.append((char) value3.intValue());
        return result.toString();
    }

    @Override
    public int compareTo(IntTrio intTrio) {
        return Integer.compare(this.hashCode(), intTrio.hashCode());
    }
}
