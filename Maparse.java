package maparse;

import java.util.HashMap;
import java.util.Map;

public class Maparse {

    static Map<String, String> parseOptionals(String optsArg) {
        String delim = optsArg.substring(0, 1);
        String body = optsArg.substring(1);
        String[] kvs = body.split(delim);
        Map<String, String> opts = new HashMap<>();

        for (String kv : kvs) {
            int idx = kv.indexOf("=");
            String k = kv.substring(0, idx);
            String v = kv.substring(idx + 1);
            opts.put(k, v);
        }

        return opts;
    }

    static Map<String, String> parseArgs(String[] args, String... names) {
        Map<String, String> opts = new HashMap<>();

        if (args.length == names.length) {
            // no optional arguments
        } else if (args.length == names.length + 1) {
            opts = parseOptionals(args[names.length]);
        } else {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            opts.put(name, args[i]);
        }

        return opts;
    }

    public static void main(String[] args) {
        Map<String, String> opts = parseArgs(args, "arg1", "arg2");
        System.err.println(opts);
    }

}
