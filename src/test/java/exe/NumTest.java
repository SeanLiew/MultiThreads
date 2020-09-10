package exe;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * desc:
 * author: liuxiaozheng
 * time: 2020/9/10  10:49
 **/
public class NumTest {

    private static final Logger logger = LoggerFactory.getLogger(NumTest.class);


    private static final int COUNT_BITS = Integer.SIZE - 3;


    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));

    private static int ctlOf(int rs, int wc) { return rs | wc; }


    @Test
    public void test(){
        logger.info("RUNNING = {}", RUNNING);
        logger.info("SHUTDOWN = {}", SHUTDOWN);
        logger.info("STOP = {}", STOP);
        logger.info("TIDYING = {}", TIDYING);
        logger.info("TERMINATED = {}", TERMINATED);
        logger.info("RUNNING | 0 = {}", RUNNING | 0);
        logger.info("CAPACITY = {}", CAPACITY);
    }
}
