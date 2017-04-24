package maparse;

import java.util.HashMap;
import java.util.Map;

public class Maparse {

    static Map<String, String> parseOptionals(String[] optionalArgs) {
        Map<String, String> opts = new HashMap<>();

        for (String arg : optionalArgs) {
            int idx = arg.indexOf("=");
            if (idx < 0) {
                opts.put(arg, "true");
            } else {
                String k = arg.substring(0, idx);
                String v = arg.substring(idx + 1);
                opts.put(k, v);
            }
        }

        return opts;
    }

    static Map<String, String> parseArgs(String[] args, String... names) {
        Map<String, String> opts = new HashMap<>();

        if (args.length == names.length) {
            // no optional arguments
        } else if (args.length > names.length) {
            opts = parseOptionals(drop(args, names.length));
        } else {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            opts.put(name, args[i]);
        }

        return opts;
    }

    private static String[] drop(String[] originalArray, int n) {
        int newSize = originalArray.length - n;
        String[] newArray = new String[newSize];
        for (int i = 0; i < newSize; i++) {
            newArray[i] = originalArray[n + i];
        }
        return newArray;
    }

    public static void main(String[] args) {
        Map<String, String> opts = parseArgs(args, "arg1", "arg2");
        System.err.println(opts);
    }

}
