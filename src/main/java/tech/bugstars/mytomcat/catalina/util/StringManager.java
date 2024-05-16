package tech.bugstars.mytomcat.catalina.util;

import java.util.Hashtable;

public class StringManager {

    private static Hashtable<String,StringManager> managers = new Hashtable<>();

    private StringManager(){}

    private StringManager(String packageName){

    }

    public synchronized static StringManager getManager(String packageName){
        StringManager mgr = managers.get(packageName);
        if(mgr == null){
            mgr = new StringManager(packageName);
            managers.put(packageName,mgr);
        }
        return mgr;
    }

    public String getString(String key){
        return null;
    }
}
