package per.itachi.java.features.nio.buffer;

import java.nio.ShortBuffer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShortBufferPractice implements BufferPractice{

    @Override
    public void practice() {
        short[] arrayShort = new short[]{5, 6, 7, 8, 9};
        ShortBuffer buffer = ShortBuffer.wrap(arrayShort);
        log.info("buffer is {}", buffer);
    }
}
