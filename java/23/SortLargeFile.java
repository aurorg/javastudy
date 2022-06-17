/*public class SortLargeFile {
    public static final int MAX_ARRAY_SIZE = 100000;
    public static final int BUFFER_SIZE = 10000;

    public static void main(String[] args) throws Exception{
        sort("largedata.dat","sortedfile");

        displayFile("sortedfile.dat");
    }
    public static void sort(String sourcefile,String targetfile) throws Exception{
        int numberOfSegments =
                initializeSements(MAX_ARRAY_SIZE,sourcefile,"f1.dat");
        merge(numberOfSegments,MAX_ARRAY_SIZE,"f1.dat","f2.dat","f3.dat",targetfile);
    }
    private static int initializeSements(int sementsSize,String originalFile,String f1) throws Exception{

    }
    private static void merge(int numberOfSements,int sementSize,String f1,String f2,String f3,String targetfile) throw Exception{
        if(numberOfSegments > 1){
            mergeOneStep(numberOfSegements,segemntSize,f1,f2,f3);
            merge((numberOfSegments +1)/2,sementSize*2,f3,f1,f2,targetfile);
        }

    }
    private static void mergeOneStep(int numberOfSegments,int segemntSize,String f1,String f2,String f3){

    }
    private static void copyHalfToF2(int numberOfSegments,int segmentSize,DataInputStream f1,DataOutStream f2) throws Exption{

    }

}

 */
