package com.mapvina.mlt.vector.flat;

import java.nio.IntBuffer;
import com.mapvina.mlt.vector.BitVector;
import com.mapvina.mlt.vector.Vector;

public class IntFlatVector extends Vector<IntBuffer, Integer> {
  public IntFlatVector(String name, IntBuffer dataBuffer, int size) {
    super(name, dataBuffer, size);
  }

  public IntFlatVector(String name, BitVector nullabilityBuffer, IntBuffer dataBuffer) {
    super(name, nullabilityBuffer, dataBuffer);
  }

  @Override
  protected Integer getValueFromBuffer(int index) {
    return dataBuffer.get(index);
  }
}
