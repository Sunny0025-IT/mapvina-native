package com.mapvina.mlt.vector.flat;

import java.nio.DoubleBuffer;
import com.mapvina.mlt.vector.BitVector;
import com.mapvina.mlt.vector.Vector;

public class DoubleFlatVector extends Vector<DoubleBuffer, Double> {
  public DoubleFlatVector(String name, DoubleBuffer dataBuffer, int size) {
    super(name, dataBuffer, size);
  }

  public DoubleFlatVector(String name, BitVector nullabilityBuffer, DoubleBuffer dataBuffer) {
    super(name, nullabilityBuffer, dataBuffer);
  }

  @Override
  protected Double getValueFromBuffer(int index) {
    return dataBuffer.get(index);
  }
}
