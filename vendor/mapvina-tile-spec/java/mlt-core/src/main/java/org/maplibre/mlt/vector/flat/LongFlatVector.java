package com.mapvina.mlt.vector.flat;

import java.nio.LongBuffer;
import com.mapvina.mlt.vector.BitVector;
import com.mapvina.mlt.vector.Vector;

public class LongFlatVector extends Vector<LongBuffer, Long> {
  public LongFlatVector(String name, LongBuffer dataBuffer, int size) {
    super(name, dataBuffer, size);
  }

  public LongFlatVector(String name, BitVector nullabilityBuffer, LongBuffer dataBuffer) {
    super(name, nullabilityBuffer, dataBuffer);
  }

  @Override
  protected Long getValueFromBuffer(int index) {
    return dataBuffer.get(index);
  }
}
