import java.util.Arrays;
import java.util.List;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;

interface CLibrary extends Library {

    public class SampleStruct extends Structure {

        public int val;
        protected List getFieldOrder() {
            return Arrays.asList( new String[] {"val"});
        }
        public static class ByValue extends SampleStruct implements Structure.ByValue {}

    }
    public void setStruct(SampleStruct.ByValue st);
    
}

public class java_structure {

    private static CLibrary clib=null;

    static{

        clib = (CLibrary)Native.loadLibrary("c_structure", CLibrary.class);

    }

    public static void execute(){

        CLibrary.SampleStruct.ByValue obj = new CLibrary.SampleStruct.ByValue();
        obj.val=100;
        clib.setStruct(obj);

    }

    public static void main(String args[]){

        execute();

    }

}
