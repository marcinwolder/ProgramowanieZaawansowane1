import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AsyncMeanTest {

    @BeforeAll
    static void beforeAll() {
        AsyncMean.initArray(100_000_000);
    }

    @Test
    void asyncMeanTest() {
        try {
            int n = 4;
            AsyncMean.asyncMeanV1(n);
            AsyncMean.asyncMeanV2(n);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}