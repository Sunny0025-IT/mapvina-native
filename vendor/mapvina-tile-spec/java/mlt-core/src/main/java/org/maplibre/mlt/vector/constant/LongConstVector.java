package com.mapvina.mlt.vector.constant;

import java.nio.LongBuffer;
import com.mapvina.mlt.vector.BitVector;

public class LongConstVector extends ConstVector<LongBuffer, Long> {
  public LongConstVector(String name, Long value, int size) {
    super(name, LongBuffer.wrap(new long[] {value}), size);
  }

  public LongConstVector(String name, BitVector nullabilityBuffer, Long value) {
    super(name, nullabilityBuffer, LongBuffer.wrap(new long[] {value}));
  }

  @Override
  protected Long getValueFromBuffer(int index) {
    return dataBuffer.get(0);
  }
}
