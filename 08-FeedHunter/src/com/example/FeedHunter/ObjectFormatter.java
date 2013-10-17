package com.example.FeedHunter;

import java.io.*;

public class ObjectFormatter {
    public static byte[] Serialize(Object o)
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(o);
            out.close();

            // Get the bytes of the serialized object
            byte[] buf = bos.toByteArray();
            return buf;
        } catch(IOException ioe)
        {
            return ioe.getMessage().getBytes();
        }
    }

    public static Object Deserialize(byte[] b)
    {
        try {
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(b));
            Object object = in.readObject();
            in.close();
            return object;
        }
        catch(ClassNotFoundException cnfe) {
            return null;
        }
        catch(IOException ioe) {
            return null;
        }
    }
}
