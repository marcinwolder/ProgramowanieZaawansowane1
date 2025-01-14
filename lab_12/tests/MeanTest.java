import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MeanTest {

    @BeforeAll
    static void beforeAll() {
        Mean.initArray(128000000);
    }

    @Test
    void parallelMeanTest() {
        try {
            int cnt = 16;
            Mean.parallelMeanV1(cnt);
            Mean.parallelMeanV2(cnt);
            Mean.parallelMeanV3(cnt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parallelMeanThreadTest() {
        try {
            for(int cnt:new int[]{1,2,4,8,16,32,64,128}){
                Mean.parallelMeanV3(cnt);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}