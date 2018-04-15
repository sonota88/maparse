package main

import (
	"errors"
	"fmt"
	"os"
	"strings"
)

func parseOptionals(optionalArgs []string) map[string]string {
	var opts map[string]string = map[string]string{}

	for i := 0; i < len(optionalArgs); i++ {
		arg := optionalArgs[i]
		idx := strings.Index(arg, "=")
		if idx >= 1 {
			k := arg[:idx]
			v := arg[(idx + 1):]
			if len(v) == 0 {
				opts[k] = ""
			} else {
				opts[k] = v
			}
		} else {
			opts[arg] = "true"
		}
	}

	return opts
}

func parseArgs(args []string, names []string) (map[string]string, error) {
	var opts map[string]string = map[string]string{}

	if len(args) == len(names) {
		// no optional arguments
	} else if len(args) > len(names) {
		opts = parseOptionals(args[len(names):])
	} else {
		return nil, errors.New("invalid arguments size")
	}

	for i := 0; i < len(names); i++ {
		opts[names[i]] = args[i]
	}

	return opts, nil
}

func main() {
	var opts map[string]string
	names := []string{"arg1", "arg2"}
	opts, err := parseArgs(os.Args[1:], names)
	if err != nil {
		os.Exit(1)
	}
	fmt.Println(opts)
}
