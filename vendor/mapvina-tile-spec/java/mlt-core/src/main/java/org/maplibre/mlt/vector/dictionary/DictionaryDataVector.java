package com.mapvina.mlt.vector.dictionary;

import java.nio.IntBuffer;
import com.mapvina.mlt.vector.BitVector;

public record DictionaryDataVector(
    String name, BitVector nullabilityBuffer, IntBuffer offsetBuffer) {}
