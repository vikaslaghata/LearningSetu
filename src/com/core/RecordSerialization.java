package com.core;

import java.io.Serializable;

public class RecordSerialization {

    //A record class is made serializable in the same way as a normal class, by implementing Serializable.
    //there is no need to add a serialVersionUID field, since the serialVersionUID of a record class is 0L unless explicitly declared,
    //and the requirement for matching the serialVersionUID value is waived for record classes.
    public record RangeRecord(int lo, int hi) implements Serializable {

    }
}
